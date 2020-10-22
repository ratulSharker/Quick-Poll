package com.funlab.quickpoll.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PollDTO {

	@NotEmpty
	@NotNull
	private String question;

	@Size(min = 2, max = 5)
	private Set<OptionDTO> options;

}
