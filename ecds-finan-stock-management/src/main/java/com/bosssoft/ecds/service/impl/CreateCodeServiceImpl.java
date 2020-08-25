package com.bosssoft.ecds.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bosssoft.ecds.entity.dto.CreateBatchBillCodeDto;
import com.bosssoft.ecds.entity.dto.CreateBillCodeDto;
import com.bosssoft.ecds.entity.dto.GetBillNumDto;
import com.bosssoft.ecds.entity.po.CodePo;
import com.bosssoft.ecds.exception.CreateBatchBillCodeException;
import com.bosssoft.ecds.mapper.CodeMapper;
import com.bosssoft.ecds.service.CreateCodeService;
import com.bosssoft.ecds.util.FillZeroUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 黄杰峰
 * @Date 2020/8/18 0018 8:58
 * @Description
 */
@Service
@Log4j2
public class CreateCodeServiceImpl implements CreateCodeService {

    private final CodeMapper codeMapper;
    private final DataSourceTransactionManager transactionManager;

    public CreateCodeServiceImpl(CodeMapper codeMapper, DataSourceTransactionManager dataSourceTransactionManager) {
        this.codeMapper = codeMapper;
        this.transactionManager = dataSourceTransactionManager;
    }

    @Override
    public boolean createSingleCode(CreateBillCodeDto createBillCodeDto) {
        log.info("=====创建新的财政编码：" + createBillCodeDto.financeCode());
        log.info("创建者：" + createBillCodeDto.getFOperator() + ", 创建者Id：");

        CodePo codePo = new CodePo();

        // 为了防止对已创建的票据代码进行创建，需要多查询一次MySQL数据库（创建操作并不是频繁发生的，对性能影响不大）
        Map<String, Object> selectMap = getSelectMap(createBillCodeDto);
        CodePo queryCodePo = codeMapper.selectOne(new QueryWrapper<CodePo>().allEq(selectMap));
        if (queryCodePo != null) {
            // 数据库中已存在该财政代码，需避免重复创建
            log.info("-----创建失败，该财政编码已存在");
            return false;
        }

        // 根据CreateFinanceCodeDto对CodePO进行初始化操作并存入数据库
        BeanUtil.copyProperties(createBillCodeDto, codePo);
        codePo.setFCreateTime(new Timestamp(System.currentTimeMillis()));
        codePo.setFUpdateTime(new Timestamp(System.currentTimeMillis()));
        codePo.setFEndCode(0);
        int insertNum = codeMapper.insert(codePo);
        log.info(insertNum > 0 ? "-----创建成功" : "创建失败，请联系管理员");

        return insertNum > 0;
    }

    @Override
    public boolean createBatchCode(CreateBatchBillCodeDto createBatchBillCodeDto) {
        int start = Integer.valueOf(createBatchBillCodeDto.getFTypeStartId());
        int end = Integer.valueOf(createBatchBillCodeDto.getFTypeEndId());

        // 属性复制
        CreateBillCodeDto createBillCodeDto = new CreateBillCodeDto();
        BeanUtil.copyProperties(createBatchBillCodeDto, createBillCodeDto);
        log.info(createBillCodeDto);

        // 批量创建
        // 编程式事务管理，批量创建过程中，若出现问题，则回滚。
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        // explicitly setting the transaction name is something that can be done only programmatically
        def.setName("createBatchTx");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            for (int index = start; index <= end; index++) {
                createBillCodeDto.setFTypeId(String.valueOf(index));
                FillZeroUtil.fillZero(createBillCodeDto);
                log.info(createBillCodeDto);
                boolean createFlag = createSingleCode(createBillCodeDto);
                if (!createFlag) {
                    throw new CreateBatchBillCodeException("批量创建失败");
                }
            }
        } catch (CreateBatchBillCodeException ex) {
            // 事务异常，回滚
            transactionManager.rollback(status);
            ex.printStackTrace();
            return false;
        }
        // 事务正常，提交事务
        transactionManager.commit(status);

        return true;
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
