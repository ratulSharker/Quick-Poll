package com.funlab.quickpoll.dto.request;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PollRequestDTO {

	@NotEmpty
	@NotNull
	private String question;

	@Size(min = 2, max = 5)
	private Set<OptionRequestDTO> options;

}
