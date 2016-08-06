package com.nazir.schedule.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 定时任务
 * 
 * @Type AbstractTask
 * @Desc 抽象类
 * @author luogm
 * @date 2016-08-25
 * @Version V1.0
 */
public abstract class AbstractTask implements Task {

    public Log logger = LogFactory.getLog(this.getClass());

    private boolean canRun = true;

    /**
     * 自动任务执行
     * 
     * @param jobData 任务上下文信息
     * @throws Exception
     */
    public void execute(JobData jobData) throws Exception {
        String taskName = this.getClass().getName();
        if (!canRun) {
            logger.error(taskName + " 有任务还在处理，当前任务不处理");
            return;
        }
        long begin = System.currentTimeMillis();
        try {
            logger.info(taskName + " 任务开始");
            canRun = false;
            doTask(jobData);
        } catch (Exception e) {
            logger.error(taskName + " 任务异常", e);
        } finally {
            logger.info(taskName + " 任务结束，总共耗时" + (System.currentTimeMillis() - begin) + "ms");
            canRun = true;
        }
    }

    /**
     * 任务执行
     * 
     * @param jobData
     */
    public abstract void doTask(JobData jobData) throws Exception;
}
