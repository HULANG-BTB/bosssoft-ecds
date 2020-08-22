package com.bosssoft.ecds.util;

/**
 * 提交状态和审核状态
 * @author 朱文
 * create on 2020/8/13 16:10
 */
public class ConstantChange {

    //提交状态 0未提交
    public final static int SUBMIT_STATUS_NO = 0;

    //提交状态 1提交
    public final static int SUBMIT_STATUS_YES = 1;

    //审核状态 0未审核
    public final static int CHECK_STATUS_NO = 0;

    //审核状态 1审核通过
    public final static int CHECK_STATUS_YES = 1;

    //审核状态 2审核未通过
    public final static int CHECK_STATUS_FAILED = 2;

    //审核状态 3无需审核
    public final static int CHECK_STATUS_NOCHECK = 3;
}
