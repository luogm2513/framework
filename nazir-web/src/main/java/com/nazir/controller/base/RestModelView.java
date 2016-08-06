package com.nazir.controller.base;

import org.springframework.web.servlet.ModelAndView;

public class RestModelView extends ModelAndView {
	
	private static final String	XML_VIEW_NAME	= "XML_VIEW_NAME";

	private static final String	MODEL_NAME		= "MODEL_NAME";

	/**
	 * 设置输入的Model值,避免从model中获取
	 */
	private Object				modelObject;

	/**
	 * 设置模型视图的值
	 * @param modelObject
	 */
	public RestModelView(Object modelObject) {
		super(XML_VIEW_NAME, MODEL_NAME, modelObject);
		this.modelObject = modelObject;
	}

	public Object getModelObject() {
		return modelObject;
	}

	public void setModelObject(Object modelObject) {
		this.modelObject = modelObject;
	}

}
