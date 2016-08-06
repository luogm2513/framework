package com.nazir.schedule.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 任务执行数据
 * 
 * @Type JobData
 * @Desc 任务上下文
 * @author luogm
 * @date 2016-08-25
 * @Version V1.0
 */
public class JobData implements Serializable {

	private static final long serialVersionUID = -3413999309818868529L;
	private Map<Object, Object> data = new HashMap<Object, Object>();

    public Map<Object, Object> getData() {
        return data;
    }

    public void setData(Map<Object, Object> data) {
        this.data = data;
    }
}