package com.funlab.quickpoll.dto;

import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

public class OptionDTO {

	@NotNull
	private Long id;

	@NotEmpty
	private String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
