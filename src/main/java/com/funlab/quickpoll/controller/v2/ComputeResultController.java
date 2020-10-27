package com.funlab.quickpoll.controller.v2;

import com.funlab.quickpoll.dto.response.VoteResultResponseDTO;
import com.funlab.quickpoll.service.VoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "ComputeResultControllerV2")
@RequestMapping("/v2/")
public class ComputeResultController {

	@Autowired
	private VoteService voteService;

	@RequestMapping(value = "/polls/{pollId}/computeresult", method = RequestMethod.GET)
	public ResponseEntity<VoteResultResponseDTO> computeResult(@PathVariable Long pollId) {

		VoteResultResponseDTO result = voteService.computeResult(pollId);
		return new ResponseEntity<VoteResultResponseDTO>(result, HttpStatus.OK);

	}

}
