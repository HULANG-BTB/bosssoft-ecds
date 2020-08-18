package com.bosssoft.ecds.encodeserver.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bosssoft.ecds.encodeserver.service.GetCodeService;
import com.bosssoft.ecds.encodeserver.entity.dto.NumSegDto;
import com.bosssoft.ecds.encodeserver.entity.dto.CreateBillCodeDto;
import com.bosssoft.ecds.encodeserver.entity.dto.GetBillNumDto;
import com.bosssoft.ecds.encodeserver.entity.po.CodePo;
import com.bosssoft.ecds.encodeserver.mapper.CodeMapper;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author 黄杰峰
 * @Date 2020/8/6 0006 11:25
 * @Description 赋码功能
 */
@Service
@Log4j2
@Slf4j
public class GetCodeServiceImpl implements GetCodeService {

    private final CodeMapper codeMapper;
    private final RedissonClient redissonClient;

    /**
     * Redis每次从关系型数据库中取号的数量，从配置文件中获取
     */
    @Value("${redis.getNumEveryTime}")
    private Integer GET_NUM_EVERY_TIME;
    private final String REDIS_START = "start";
    private final String REDIS_END = "end";
    private final String LOCK_KEY = "lock_key";

    @Autowired
    public GetCodeServiceImpl(CodeMapper codeMapper, RedissonClient redissonClient) {
        this.codeMapper = codeMapper;
        this.redissonClient = redissonClient;
    }

    /**
     * 批量取票
     * @param getBillNumDto
     * @return
     */
    @Override
    public NumSegDto getBatchCode(GetBillNumDto getBillNumDto) {
        String financeCode = getBillNumDto.financeCode();
        NumSegDto financeCodeSeg = null;

        // 获取可重入锁
        RLock lock = redissonClient.getLock(LOCK_KEY);
        // 获取红锁
        RedissonRedLock redLock = new RedissonRedLock(lock);
        try {
            boolean isLock = redLock.tryLock(100L, 10L, TimeUnit.SECONDS);
            if (isLock) {
                // 红锁上锁，进行赋码
                /*// 判断财政代码是否创建（体现在MySQL中是否有对应数据），若未创建，直接返回null，在控制器处理。会多查询一次MySQL？考虑在Redis操作
                if (!isCodeCreated(getFinanceNumDto)) {
                    return null;
                }*/

                // 财政代码已创建
                // 若票据代码对应redis hash记录未创建，则需要创建，否则直接取即可。
                try {
                    if (!isRedisCreated(financeCode)) {
                        createNewHashInRedis(getBillNumDto);
                    }
                } catch (NullPointerException nullPointerException) {
                    // 释放锁后再返回null
                    redLock.unlock();
                    return null;
                }

                // Redis库存充足or初始化完成，取出库存码
                financeCodeSeg = getCodeFromRedis(getBillNumDto);

                // 取码完成，解锁
                redLock.unlock();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 取出Redis中号码段，返回

        return financeCodeSeg;
    }

    /**
     * 票据代码对应赋码记录是否创建
     * @param financeCode
     * @return
     */
    public boolean isRedisCreated(String financeCode) {
        RMap<Object, Object> map = redissonClient.getMap("code" + financeCode);
        return map.size() > 0;
    }

    /**
     * 已创建的票据代码取票(Redis)
     * @param getBillNumDto
     * @return
     */
    public NumSegDto getCodeFromRedis(GetBillNumDto getBillNumDto) {
        NumSegDto numSegDto = new NumSegDto();
        RMap<Object, Object> map = redissonClient.getMap("code" + getBillNumDto.financeCode(), StringCodec.INSTANCE);

        // 构建更新MySQL的对象
        GetBillNumDto getFinanceNumFromSqlDto = new GetBillNumDto();
        BeanUtil.copyProperties(getBillNumDto, getFinanceNumFromSqlDto);

        // 需考虑剩余数量是否足够，若不够，需要在关系数据库中再领取号码。
        long start = Long.parseLong((String)map.get(REDIS_START));
        long end = Long.parseLong((String)map.get(REDIS_END));

        // 剩余数量不足...
        if (end - start < getFinanceNumFromSqlDto.getCodeNum()) {
            // 根据指定获取数量从MySQL中取票
            getFinanceNumFromSqlDto.setCodeNum(GET_NUM_EVERY_TIME);
            NumSegDto batchCodeFromSql = getBatchCodeFromSql(getFinanceNumFromSqlDto);
            // 更新Redis中库存的票据号码
            map.put(REDIS_END, batchCodeFromSql.getEndCode());
        }

        // 剩余数量足够，或不足但补充库存后
        map.put(REDIS_START, start + getBillNumDto.getCodeNum());
        // 设置返回号码段
        numSegDto.setBeginCode(start);
        numSegDto.setEndCode(start + getBillNumDto.getCodeNum() - 1);
        return numSegDto;
    }

    /**
     * 在redis中新建hash记录
     * @param getBillNumDto
     */
    public void createNewHashInRedis(GetBillNumDto getBillNumDto) throws NullPointerException{
        // 新建redis记录
        RMap<Object, Object> map = redissonClient.getMap("code" + getBillNumDto.financeCode(), StringCodec.INSTANCE);

        GetBillNumDto getBillNumDtoFromSql = new GetBillNumDto();
        BeanUtil.copyProperties(getBillNumDto, getBillNumDtoFromSql);

        // 每次获取数量为yaml配置文件中指定的数量
        getBillNumDtoFromSql.setCodeNum(GET_NUM_EVERY_TIME);

        // 从MySQL中取出号码段
        NumSegDto codeFromSql = getBatchCodeFromSql(getBillNumDtoFromSql);
        // 根据MySQL中取出号码段初始化Redis，完成Hash记录创建
        map.put(REDIS_START, codeFromSql.getBeginCode());
        map.put(REDIS_END, codeFromSql.getEndCode());
    }

    @Override
    public long getSingleCode(GetBillNumDto getBillNumDTO) {
        return 0;
    }

    /**
     * 从MySQL中获取票号段
     * @param getFinanceCodeDTO
     * @return
     */
    public NumSegDto getBatchCodeFromSql(GetBillNumDto getFinanceCodeDTO) throws NullPointerException{
        NumSegDto numSegDto = new NumSegDto();
        // 建立查询映射
        Map<String, Object> selectMap = getSelectMap(getFinanceCodeDTO);

        // 获取数据库中最新对应编码实体
        CodePo oldCodePo = selectOldCodePo(selectMap);
        // 批量获取数量
        Integer codeNum = getFinanceCodeDTO.getCodeNum();

        // 从MySQL中获取号码
        if (getFinanceCode(getFinanceCodeDTO, selectMap) > 0) {
            // 获取成功
            numSegDto.setBeginCode(oldCodePo.getFEndCode() + 1);
            numSegDto.setEndCode(oldCodePo.getFEndCode() + codeNum);
            return numSegDto;
        } else {
            // 从MySQL中获取失败
            return null;
        }
    }

    /**
     * 获取财政编码(不区分单个/批量)
     * @param getBillNumDto
     * @return
     */
    public int getFinanceCode(GetBillNumDto getBillNumDto, Map<String, Object> map) {

        CodePo codePO = new CodePo();
        // 查询当前数据库最新内容
        CodePo oldCodePo = selectOldCodePo(map);

        // 获取查询时version
        long fVersion = oldCodePo.getFVersion();
        // 获取查询时的endCode + 申请码数量，和为赋码后的endCode;
        long fEndCode = oldCodePo.getFEndCode() + getBillNumDto.getCodeNum();
        // 更新endCode
        codePO.setFEndCode(fEndCode);
        // 更新更新时间
        codePO.setFUpdateTime(new Timestamp(System.currentTimeMillis()));

        // 更新数据库内容，因配置了MyBatis Plus 乐观锁插件，自动实现乐观锁更新(version字段自动更新)
        codePO.setFVersion(fVersion);
        return codeMapper.update(codePO, new UpdateWrapper<CodePo>().allEq(map));
    }

    /**
     * 获取数据库当前映射下最新实体CodePo
     * @param map
     * @return
     */
    public CodePo selectOldCodePo(Map<String, Object> map) {
        // 从数据库赋码表中获取对应编号的，当前最新的赋码实体
        return codeMapper.selectOne(new QueryWrapper<CodePo>().allEq(map));
    }

    /**
     * 根据GetFinanceNumDto建立查询映射
     * @param getBillNumDto
     * @return
     */
    public Map<String, Object> getSelectMap(GetBillNumDto getBillNumDto) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("f_regi_id", getBillNumDto.getFRegiId());
        map.put("f_sort_id", getBillNumDto.getFSortId());
        map.put("f_type_id", getBillNumDto.getFTypeId());
        map.put("f_annual_id", getBillNumDto.getFAnnualId());
        return map;
    }

    /**
     * 根据CreateFinanceNumDto建立查询映射
     * @param createBillCodeDto
     * @return
     */
    public Map<String, Object> getSelectMap(CreateBillCodeDto createBillCodeDto) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("f_regi_id", createBillCodeDto.getFRegiId());
        map.put("f_sort_id", createBillCodeDto.getFSortId());
        map.put("f_type_id", createBillCodeDto.getFTypeId());
        map.put("f_annual_id", createBillCodeDto.getFAnnualId());
        return map;
    }
}
