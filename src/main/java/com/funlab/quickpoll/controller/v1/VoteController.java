package com.funlab.quickpoll.controller.v1;

import java.net.URI;
import java.util.ArrayList;

import com.funlab.quickpoll.dto.response.VoteResponseDTO;
import com.funlab.quickpoll.entity.Vote;
import com.funlab.quickpoll.service.VoteService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController(value = "VoteControllerV1")
@RequestMapping("/v1/")
public class VoteController {

	@Autowired
	private VoteService voteService;

	@Autowired
	private ModelMapper modelMapper;

	@RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.POST)
	public ResponseEntity<VoteResponseDTO> createVote(@RequestBody Vote vote, @PathVariable Long pollId) {
		vote = voteService.save(vote);

		VoteResponseDTO voteDto = modelMapper.map(vote, VoteResponseDTO.class);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vote.getId()).toUri();

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity<VoteResponseDTO>(voteDto, headers, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.GET)
	public ResponseEntity<Iterable<VoteResponseDTO>> getVotesForPoll(@PathVariable Long pollId) {

		Iterable<Vote> votes = voteService.findByPoll(pollId);
		ArrayList<VoteResponseDTO> voteDtos = new ArrayList<VoteResponseDTO>();

		votes.forEach(vote -> {
			voteDtos.add(modelMapper.map(vote, VoteResponseDTO.class));
		});

		return new ResponseEntity<Iterable<VoteResponseDTO>>(voteDtos, HttpStatus.OK);
	}

}
