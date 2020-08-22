package com.bosssoft.ecds.service;

/**
 * @author liuke
 */
public interface ArchiveCycleService {
    /**
     * 系统每天 01:00 进行数据归档
     */
    void exec();
}
