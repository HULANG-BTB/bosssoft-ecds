package com.bosssoft.ecds.service.impl;


import com.alibaba.excel.EasyExcelFactory;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.dao.*;
import com.bosssoft.ecds.entity.dto.itemdto.ExportDTO;
import com.bosssoft.ecds.entity.po.*;
import com.bosssoft.ecds.exception.CustomException;
import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.response.ResponseResult;
import com.bosssoft.ecds.entity.dto.itemdto.ItemDTO;
import com.bosssoft.ecds.entity.vo.itemvo.ItemPageVO;
import com.bosssoft.ecds.entity.vo.itemvo.ItemVO;
import com.bosssoft.ecds.enums.ItemResultCode;
import com.bosssoft.ecds.service.ItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.ecds.utils.ExcelUtil;
import com.bosssoft.ecds.utils.MyBeanUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wzh
 * @since 2020-08-09
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemDao, ItemPO> implements ItemService {

    @Autowired
    private ItemDao itemDao;
    @Autowired
    private AgenItemDao agenItemDao;
    @Autowired
    private ItemStdDao itemStdDao;
    @Autowired
    private IncomeSortDao incomeSortDao;
    @Autowired
    private SubjectDao subjectDao;

    private static final String[] titles = {"项目编码", "项目名称", "助记码", "记录生效日期", "记录截止日期"
            , "项目生效日期", "项目截止日期", "是否启用", "收入类别", "资金性质", "预算科目编码"
            , "预算科目名字", "收缴方式", "备注"};
    private static String dateFormat = "yyyy-MM-dd";

    /**
     * 插入项目，输入项目信息
     *
     * @param itemDTO
     * @return ResponseResult
     */
    @Override
    public ResponseResult save(ItemDTO itemDTO) {
        // 将dto转化为po
        ItemPO itemPO = MyBeanUtil.myCopyProperties(itemDTO, ItemPO.class);
        // 执行插入操作
        boolean save = super.save(itemPO);
        // 插入失败返回操作错误
        if (!save) {
            return new ResponseResult(CommonCode.FAIL);
        }
        // 插入成功
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 更新项目信息
     *
     * @param itemDTO
     * @return ResponseResult
     */
    @Override
    public ResponseResult update(ItemDTO itemDTO) {
        // 将dto转化为po
        ItemPO itemPO = MyBeanUtil.myCopyProperties(itemDTO, ItemPO.class);
        // 执行更新操作
        boolean update = super.updateById(itemPO);
        // 更新失败返回操作错误
        if (!update) {
            return new ResponseResult(CommonCode.FAIL);
        }
        // 更新成功
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 删除项目
     *
     * @param itemDTO
     * @return ResponseResult
     */
    @Override
    public ResponseResult delete(ItemDTO itemDTO) {
        // 判断传入id是否存在
        ItemPO itemPO = itemDao.selectById(itemDTO.getId());
        if (itemPO == null) {
            return new ResponseResult(ItemResultCode.ITEM_NOT_EXISTS);
        }
        // 构造条件查询，通过项目编码，查询是否存在项目标准
        QueryWrapper<ItemStdPO> itemStdWrapper = new QueryWrapper<>();
        itemStdWrapper.eq(ItemStdPO.F_ITEM_CODE, itemPO.getItemId());
        ItemStdPO itemStdPO = itemStdDao.selectOne(itemStdWrapper);
        if (itemStdPO != null) {
            // 如果项目标准存在，则删除项目标准
            itemStdDao.deleteById(itemStdPO);
        }
        // 构造条件查询器，通过项目编码，查询是否存在项目单位关系
        QueryWrapper<AgenItemPO> agenItemWrapper = new QueryWrapper<>();
        agenItemWrapper.eq(AgenItemPO.F_ITEM_CODE, itemPO.getItemId());
        List<AgenItemPO> agenItemPOS = agenItemDao.selectList(agenItemWrapper);
        if (!agenItemPOS.isEmpty()) {
            // 如果项目标准存在，则删除项目标准
            agenItemDao.delete(agenItemWrapper);
        }
        // 执行删除操作
        boolean remove = super.removeById(itemPO);
        // 删除失败返回操作错误
        if (!remove) {
            return new ResponseResult(CommonCode.FAIL);
        }
        // 删除成功
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * pageDTO.getKeyword() 无数据输入时实现查询全部数据，有数据输入时进行模糊查询
     * 分页展示查询结果
     *
     * @param itemPageVO
     * @return
     */
    @Override
    public ResponseResult listByPage(ItemPageVO<ItemDTO> itemPageVO) {
        Page<ItemPO> itemDTOPage = new Page<>();
        // 设置分页信息
        itemDTOPage.setCurrent(itemPageVO.getPage());
        itemDTOPage.setSize(itemPageVO.getLimit());
        // 读取分页数据
        QueryWrapper<ItemPO> queryWrapper = new QueryWrapper<>();
        if (!itemPageVO.getSubjectCode().isEmpty()) {
            // 预算科目编码不为空，表示在项目管理界面分页查询
            // 判断审核状态是否存在，如果不存在，通过预算科目查询出全部信息，还可以通过名字模糊查询
            if (itemPageVO.getIsenable() == null) {
                queryWrapper.eq(ItemPO.F_SUBJECT, itemPageVO.getSubjectCode())
                        .and(wrapper -> wrapper.like(ItemPO.F_ITEM_NAME, itemPageVO.getKeyword()));
            } else {
                // 如果审核状态存在，通过预算科目和审核状态查询，还可以通过名字模糊查询
                queryWrapper.eq(ItemPO.F_SUBJECT, itemPageVO.getSubjectCode())
                        // 根据审核状态查询
                        .and(wrapper -> wrapper.eq(ItemPO.F_ISENABLE, itemPageVO.getIsenable()))
                        .and(wrapper -> wrapper.like(ItemPO.F_ITEM_NAME, itemPageVO.getKeyword()));
            }
        } else {
            if (itemPageVO.getIsenable() == null) {
                queryWrapper.like(ItemPO.F_ITEM_NAME, itemPageVO.getKeyword());
            } else {
                // 预算科目编码为空，审核界面的分页查询，先查询审核状态，然后可以通过名字模糊查询
                queryWrapper.eq(ItemPO.F_ISENABLE, itemPageVO.getIsenable())
                        .and(wrapper -> wrapper.like(ItemPO.F_ITEM_NAME, itemPageVO.getKeyword()));
            }
        }
        queryWrapper.orderByAsc(ItemPO.F_CREATE_TIME);
        // 读取分页数据
        Page<ItemPO> itemPOPage = super.page(itemDTOPage, queryWrapper);
        List<ItemPO> records = itemPOPage.getRecords();
        // 转换数据
        List<ItemDTO> itemPOS = MyBeanUtil.copyListProperties(records, ItemDTO::new);
        itemPageVO.setTotal(itemPOPage.getTotal());
        itemPageVO.setItems(itemPOS);
        return new QueryResponseResult<>(CommonCode.SUCCESS, itemPageVO);
    }

    /**
     * 批量删除
     *
     * @param itemDTOS
     * @return
     */
    @Override
    public ResponseResult batchDelete(List<ItemDTO> itemDTOS) {
        // 构建批量删除的idList
        ArrayList<Long> idList = new ArrayList<>();
        for (Iterator<ItemDTO> iterator = itemDTOS.iterator(); iterator.hasNext(); ) {
            idList.add(iterator.next().getId());
        }
        // 执行批量删除
        boolean removeByIds = super.removeByIds(idList);
        // 删除失败返回操作失败
        if (!removeByIds) {
            return new ResponseResult(CommonCode.FAIL);
        }
        // 删除成功返回操作成功
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 批量审核
     * 遍历itemDTOS，每次查询出审核成功的项目信息，修改审核状态，执行更新
     *
     * @param itemDTOS
     * @return
     */
    @Override
    public ResponseResult batchVerify(List<ItemDTO> itemDTOS) {
        for (ItemDTO itemDTO : itemDTOS) {
            // 查询出审核成功的项目信息
            ItemPO itemPO = super.getById(itemDTO.getId());
            // 修改审核状态
            itemPO.setIsenable(1);
            // 更新
            boolean updateById = super.updateById(itemPO);
            // 修改失败返回操作失败
            if (!updateById) {
                return new ResponseResult(CommonCode.FAIL);
            }
        }
        // 修改成功返回操作程序
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 查询所有项目信息
     *
     * @return 项目信息集合
     */
    @Override
    public ResponseResult getItemAll() {
        //查询出所有的项目信息
        List<ItemPO> itemPOS = itemDao.selectList(null);
        if (!itemPOS.isEmpty()) {
            List<ItemVO> itemVOS = MyBeanUtil.copyListProperties(itemPOS, ItemVO::new);
            return new QueryResponseResult<>(CommonCode.SUCCESS, itemVOS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    /**
     * 通过收入类别编码 ，获得收入类别的名字
     *
     * @param code 收入类别编码
     * @return 返回收入类别名字
     */
    @Override
    public ResponseResult getIncomeSortName(String code) {
        // 构造条件查询器，根据收入类别编码查询
        QueryWrapper<IncomeSortPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(IncomeSortPO.F_CODE, code);
        IncomeSortPO incomeSortPO = incomeSortDao.selectOne(queryWrapper);
        return new QueryResponseResult<>(CommonCode.SUCCESS, incomeSortPO);
    }

    /**
     * 通过excel导入项目
     *
     * @param file 导入文件
     * @return 导入结果
     */
    @Override
    public ResponseResult importExcel(MultipartFile file) {
        Sheet sheet = ExcelUtil.importExcelWithSimple(file);
        boolean firstRow = true;
        for (Row row : sheet) {
            // 如果为第一行，比较标题与导入模板是否正确
            if (firstRow) {
                for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                    if (!ExcelUtil.getCellValue(row.getCell(j)).equals(titles[j])) {
                        throw new CustomException(ItemResultCode.MODULE_ERROR, "请使用正确模板导入项目");
                    }
                }
                firstRow = false;
            } else {
                // 校验每行数据，如果校验通过则添加数据，不通过则抛出错误信息
                if (!ExcelUtil.isBlankRow(row)) {
                    itemDao.insert(validItemExcel(sheet, row));
                }
            }
        }
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 从excel导出项目信息
     * 将文件已流的形式放入response，返回给前端
     *
     * @param itemDTOS itemDTOS为空时导出所有的项目信息
     *                 itemDTOS不为空时导出对应id的项目信息
     */
    @Override
    public void exportExcel(List<ItemDTO> itemDTOS, HttpServletResponse response) throws IOException {
        //设置传输类型
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("item", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        List<ItemPO> itemPOS = null;
        // 如果传入数据不为空，通过id生成excel
        if (!itemDTOS.isEmpty()) {
            List<Long> idList = new ArrayList<>();
            for (ItemDTO itemDTO : itemDTOS) {
                idList.add(itemDTO.getId());
            }
            itemPOS = itemDao.selectBatchIds(idList);
        } else {
            // 传入数据为空，将全部数据生成excel
            itemPOS = itemDao.selectList(null);
        }
        EasyExcelFactory.write(response.getOutputStream(), ExportDTO.class).sheet("项目").doWrite(itemPOS);
    }

    // 比较时间早晚的工具
    private boolean before(String start, String end) {

        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Date dateStart = null;
        Date dateEnd = null;
        try {

            dateStart = formatter.parse(start);
            dateEnd = formatter.parse(end);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dateStart == null || dateEnd == null) {
            throw new NullPointerException();
        }
        return dateStart.before(dateEnd);
    }

    // 生成校验错误结果msg
    private String creatMsg(int row, int colNum, String msg) {
        return "校验 :第" + row + "行" + colNum + msg;
    }

    // 校验excel输入数据
    private ItemPO validItemExcel(Sheet sheet, Row row) {
        int colNum = 0;

        ItemPO itemPO = new ItemPO();
        // 项目编码输入与校验
        ExcelUtil.validCellValue(sheet, row, ++colNum, "项目编码");
        QueryWrapper<ItemPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ItemPO.F_ITEM_ID, ExcelUtil.getCellValue(sheet, row, colNum - 1));
        ItemPO one = super.getOne(queryWrapper);
        if (one != null) {
            throw new CustomException(ItemResultCode.ITEM_STD_EXISTS
                    , creatMsg(row.getRowNum() + 1, colNum, "列项目编码已经存在，不能添加"));
        }
        itemPO.setItemId(ExcelUtil.getCellValue(sheet, row, colNum - 1));
        // 项目名称输入与校验
        ExcelUtil.validCellValue(sheet, row, ++colNum, "项目名称");
        itemPO.setItemName(ExcelUtil.getCellValue(sheet, row, colNum - 1));
        // 助记码输入
        ++colNum;
        itemPO.setMnen(ExcelUtil.getCellValue(sheet, row, colNum - 1));
        // 记录生效日期输入与校验
        ++colNum;
        String effDate = ExcelUtil.getCellValue(sheet, row, colNum - 1);
        //记录截止日期输入与校验
        ++colNum;
        String expDate = ExcelUtil.getCellValue(sheet, row, colNum - 1);
        if (!before(effDate, expDate)) {
            throw new CustomException(ItemResultCode.DATE_ERROR
                    , creatMsg(row.getRowNum() + 1, colNum, "记录截止日期早于记录日期"));
        }
        // 项目生效日期输入与校验
        ++colNum;
        String itemEffDate = ExcelUtil.getCellValue(sheet, row, colNum - 1);
        // 项目截止日期输入与校验
        ++colNum;
        String itemExpDate = ExcelUtil.getCellValue(sheet, row, colNum - 1);
        if (!before(itemEffDate, itemExpDate)) {
            throw new CustomException(ItemResultCode.ITEM_DATE_ERROR
                    , creatMsg(row.getRowNum() + 1, colNum, "项目截止日期早于开始日期"));
        }
        try {
            itemPO.setEffdate(new SimpleDateFormat(dateFormat).parse(effDate));
            itemPO.setExpdate(new SimpleDateFormat(dateFormat).parse(expDate));
            itemPO.setItemEffdate(new SimpleDateFormat(dateFormat).parse(itemEffDate));
            itemPO.setItemExpdate(new SimpleDateFormat(dateFormat).parse(itemExpDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 是佛启用状态输入与校验
        ++colNum;
        int isenable = Integer.parseInt(ExcelUtil.getCellValue(sheet, row, colNum - 1));
        if (isenable != 0 && isenable != 1) {
            throw new CustomException(ItemResultCode.ISENABLE_ERROR
                    , creatMsg(row.getRowNum() + 1, colNum, "请输入正确的是否启用状态，1为启用，0为不启用"));
        }
        itemPO.setIsenable(isenable);
        // 收入类别编码输入与校验
        ExcelUtil.validCellValue(sheet, row, ++colNum, "收入类别");
        String inComeSortCode = ExcelUtil.getCellValue(sheet, row, colNum - 1);
        QueryWrapper<IncomeSortPO> incomeSortWrapper = new QueryWrapper<>();
        incomeSortWrapper.eq(IncomeSortPO.F_CODE, inComeSortCode);
        if (incomeSortDao.selectOne(incomeSortWrapper) == null) {
            throw new CustomException(ItemResultCode.INCOMESORT_NOT_EXISTS
                    , creatMsg(row.getRowNum() + 1, colNum, "收入类别不存在"));
        }
        itemPO.setIncomSortCode(inComeSortCode);
        // 资金性质输入
        ++colNum;
        itemPO.setFundsnatureCode(ExcelUtil.getCellValue(sheet, row, colNum - 1));
        // 预算科目编码输入与校验
        ExcelUtil.validCellValue(sheet, row, ++colNum, "预算科目编码");
        String subjectCode = ExcelUtil.getCellValue(sheet, row, colNum - 1);
        QueryWrapper<SubjectPO> subjectWrapper = new QueryWrapper<>();
        subjectWrapper.eq(SubjectPO.F_SUB_CODE, subjectCode).and(wrapper -> wrapper.eq(SubjectPO.F_YEAR, "2020"));
        SubjectPO subjectPO = subjectDao.selectOne(subjectWrapper);
        if (subjectPO == null) {
            throw new CustomException(ItemResultCode.SUBJECT_NOT_EXISTS
                    , creatMsg(row.getRowNum() + 1, colNum, "预算科目不存在"));
        }
        // 预算科目编码名称与校验
        ExcelUtil.validCellValue(sheet, row, ++colNum, "预算科目名称");
        String subjectName = ExcelUtil.getCellValue(sheet, row, colNum - 1);
        if (!subjectPO.getName().equals(subjectName)) {
            throw new CustomException(ItemResultCode.SUBJECT_NAME_NOT_MATCH
                    , creatMsg(row.getRowNum() + 1, colNum, "预算科目编码与名称不对应"));
        }
        itemPO.setSubject(subjectCode);
        itemPO.setSubjectName(subjectName);
        ++colNum;
        itemPO.setPaymode(ExcelUtil.getCellValue(sheet, row, colNum - 1));
        ++colNum;
        itemPO.setNote(ExcelUtil.getCellValue(sheet, row, colNum - 1));
        return itemPO;
    }
}
