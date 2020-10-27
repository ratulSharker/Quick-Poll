package com.funlab.quickpoll.dto.response;

import java.util.Collection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteResultResponseDTO {

	private Long totalVotes;
	private Collection<VoteCountResponseDTO> result;

}
