package com.nazir.schedule.base;

/**
 * 任务组件
 * 
 * @Type Task
 * @Desc 业务接口
 * @author luogm
 * @date 2016-08-25
 * @Version V1.0
 */
public interface Task {

    /**
     * 任务执行入口主方法
     * 
     * @param jobData
     * @throws Exception
     */
    void execute(JobData jobData) throws Exception;

    /**
     * 任务接口,具体的任务都实现此接口
     * 
     * @param jobData
     * @throws Exception
     */
    void doTask(JobData jobData) throws Exception;
}