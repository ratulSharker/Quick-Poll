package com.funlab.quickpoll.dto.request;

import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionRequestDTO {

	@NotNull
	private Long id;

	@NotEmpty
	private String value;

}
