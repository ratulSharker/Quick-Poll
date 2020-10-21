package com.funlab.quickpoll.dto;

import java.util.Map;

public class ApiResponse<T> {

	private Map<String, Object> meta;
	private T data;

	public Map<String, Object> getMeta() {
		return meta;
	}

	public void setMeta(Map<String, Object> meta) {
		this.meta = meta;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
