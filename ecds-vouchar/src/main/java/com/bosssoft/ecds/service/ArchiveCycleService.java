package com.bosssoft.ecds.service;

public interface ArchiveCycleService {
    /**
     * 系统每天 00:00 进行数据归档
     */
    void exec();
}
