package com.bosssoft.ecds.enums;

import com.bosssoft.ecds.response.ResultCode;
import org.springframework.web.context.request.FacesRequestAttributes;

/**
 * @author 吴志鸿
 * @date 2020/8/10
 * @description
 */
public enum  ItemResultCode implements ResultCode {

    ITEM_NOT_EXISTS(false,5000,"项目不存在"),
    ITEM_STD_NOT_EXISTS(false,5001,"项目标准不存在"),
    ITEM_STD_EXISTS(false,5003,"已经存在，无法添加"),
    NOT_EXISTS(false,5002,"删除失败，没有此条记录"),
    MODULE_ERROR(false,100001,"请使用正确模板导入项目"),
    DATE_ERROR(false,100002,"记录截止日期早于记录日期"),
    ITEM_DATE_ERROR(false,100003,"项目截止日期早于开始日期"),
    ISENABLE_ERROR(false,100004,"请输入正确的是否启用状态，1为启用，0为不启用"),
    INCOMESORT_NOT_EXISTS(false,100005,"收入类别不存在"),
    SUBJECT_NOT_EXISTS(false,100006,"预算科目不存在"),
    SUBJECT_NAME_NOT_MATCH(false,100007,"预算科目编码与名称不对应");



    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    ItemResultCode(boolean success,int code, String message){
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
