package com.funlab.quickpoll.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

public class PollDTO {

	@NotEmpty
	@NotNull
	private String question;

	@Size(min = 2, max = 5)
	private Set<OptionDTO> options;

	
	
	
	
	
	
	
	
	
	
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Set<OptionDTO> getOptions() {
		return options;
	}

	public void setOptions(Set<OptionDTO> options) {
		this.options = options;
	}

}
