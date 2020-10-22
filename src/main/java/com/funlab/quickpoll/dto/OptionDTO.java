package com.funlab.quickpoll.dto;

import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionDTO {

	@NotNull
	private Long id;

	@NotEmpty
	private String value;

}
