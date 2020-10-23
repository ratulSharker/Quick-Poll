package com.funlab.quickpoll.dto;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomApiResponse<T> {

	private Map<String, Object> meta;
	private T data;
}
