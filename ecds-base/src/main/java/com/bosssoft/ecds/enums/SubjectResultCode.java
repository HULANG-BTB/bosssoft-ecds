package com.bosssoft.ecds.enums;

import com.bosssoft.ecds.common.response.ResultCode;

/**
 * @author shkstart
 * @create 2020-08-14 14:38
 */
public enum SubjectResultCode implements ResultCode {
    SUBJECT_NOTEXISTS(false,20001,"预算科目信息不存在或已被删除，请刷新后重试！"),
    INCOMESORT_NOTEXISTS(false,20002,"相应的收入类别不存在或添加在了错误的位置！"),
    INSERT_ERROR(false,20003,"只能添加今年的科目！"),
    LEVEL_ERROR(false,20004,"不能在底级科目下添加子科目！"),
    ENABLE_ERROR(false,20005,"该科目已被禁用，请启用后操作！"),
    CODE_ERROR(false,20006,"子科目编码必须以父科目编码为前缀！"),
    PARENT_ERROR(false,20007,"父级预算科目不存在或已被删除，请刷新重试！"),
    UPDATE_ERROR(false,20008,"该科目存在对应的收入类别，不允许直接修改名称！"),
    UPDATE_DATE_ERROR(false,20009,"只允许修改今年的数据！"),

    ;

    boolean success;
    int code;
    String message;

    SubjectResultCode(boolean success,int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }
    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
