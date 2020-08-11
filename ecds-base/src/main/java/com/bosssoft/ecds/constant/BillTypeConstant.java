package com.bosssoft.ecds.constant;

/**
 * @author :Raiz
 * @date :2020/8/7
 */
public class BillTypeConstant {

    private BillTypeConstant() {
    }

    public static final Integer LOGIC_DELETE = 1;
    public static final Integer NOT_LOGIC_DELETE = 0;
    public static final Integer BILL_SORT = 1;
    public static final Integer BILL_TYPE = 0;

    public static final String CODE_NOT_BLANK = "票据编码不为空";
    public static final String NAME_NOT_BLANK = "票据名称不为空";
    public static final String CHECK_SORT_NOT_NULL = "是否分类不为空";
    public static final String BILL_NATURE_NOT_BLANK = "票据用途不为空";
    public static final String NATURE_CODE_NOT_NULL = "票据性质不为空";
    public static final String MEMORY_CODE_NOT_BLANK = "助记码不为空";
    public static final String EFF_DATE_NOT_NULL = "生效日期不为空";
    public static final String EXP_DATE_NOT_NULL = "失效日期不为空";
    public static final String DATE_FUTURE = "生效日期和失效日期必须为未来的时间";

    public static final String PAGE_NUM_NOT_NULL = "PgaeNum不为空";
    public static final String PAGE_SIZE_NOT_NULL = "PageSize不为空";


    public static final String ID_NOT_NULL = "票据种类id不为空";
}
