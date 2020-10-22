package com.funlab.quickpoll.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionCount {

	private Long optionId;
	private Long count;

	public void incrementCount() {
		this.count++;
	}

}
