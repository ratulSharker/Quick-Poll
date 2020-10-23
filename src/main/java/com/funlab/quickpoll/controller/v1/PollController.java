package com.funlab.quickpoll.controller.v1;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.funlab.quickpoll.domain.Option;
import com.funlab.quickpoll.domain.Poll;
import com.funlab.quickpoll.dto.CustomApiResponse;
import com.funlab.quickpoll.dto.OptionDTO;
import com.funlab.quickpoll.dto.PollDTO;
import com.funlab.quickpoll.exception.ResourceNotFoundException;
import com.funlab.quickpoll.repositoy.PollRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController(value = "PollControllerV1")
@RequestMapping("/v1/")
@Api(value = "polls", description = "Poll API")
public class PollController {

	@Autowired
	private PollRepository pollRepository;

	@Autowired
	private MessageSource messageSource;

	@ApiOperation(value = "Get all available polls")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Polls fetched", response = Poll.class)
	})
	@RequestMapping(value = "/polls", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Poll>> getAllPolls() {
		return new ResponseEntity<Iterable<Poll>>(this.pollRepository.findAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "Create a new poll", notes = "It will return the newly created poll and set the response header location to newly created poll", response = Poll.class)
	@RequestMapping(value = "/polls", method = RequestMethod.POST)
	public ResponseEntity<CustomApiResponse<Poll>> createPoll(@Valid @RequestBody PollDTO pollDTO, HttpServletRequest req) {
		
		Poll poll = new Poll();
		poll.setQuestion(pollDTO.getQuestion());
		
		Set<Option> options = new HashSet<Option>();
		for (OptionDTO optionDTO : pollDTO.getOptions()) {
			Option option = new Option();
			option.setId(optionDTO.getId());
			option.setValue(optionDTO.getValue());
			options.add(option);
		}
		poll.setOptions(options);
		
		poll = this.pollRepository.save(poll);

		HttpHeaders responseHeaders = new HttpHeaders();
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(poll.getId()).toUri();
		responseHeaders.setLocation(uri);

		CustomApiResponse<Poll> response = new CustomApiResponse<Poll>();
		response.setData(poll);

		Map<String, Object> meta = new HashMap<String, Object>();
		meta.put("timestamp", new Date().getTime());
		meta.put("message", messageSource.getMessage("PollCreatedSuccessfully", null, req.getLocale()));
		response.setMeta(meta);



		return new ResponseEntity<CustomApiResponse<Poll> >(response, responseHeaders, HttpStatus.CREATED);
	}

	
	@ApiOperation(value = "Get poll by id", response = Poll.class)
	@RequestMapping(value = "/polls/{id}", method = RequestMethod.GET)
	public ResponseEntity<Poll> getPoll(@PathVariable("id") Long pollId) {

		return new ResponseEntity<>(verifyAndGetPoll(pollId), HttpStatus.OK);
	}

	
	@ApiOperation(value = "Update a particular poll", response = Poll.class)
	@RequestMapping(value = "/polls/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Poll> updatePoll(@PathVariable("id") Long pollId, @RequestBody Poll poll) {
		verifyAndGetPoll(pollId);
		poll.setId(pollId);
		poll = this.pollRepository.save(poll);
		return new ResponseEntity<Poll>(poll, HttpStatus.OK);
	}


	@ApiOperation(value = "Delete a particular poll")
	@RequestMapping(value = "/polls/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePoll(@PathVariable("id") Long pollId) {
		verifyAndGetPoll(pollId);
		this.pollRepository.deleteById(pollId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	private Poll verifyAndGetPoll(Long pollId) throws ResourceNotFoundException {
		try {
			Optional<Poll> nullablePoll = this.pollRepository.findById(pollId);
			return nullablePoll.get();
		} catch (NoSuchElementException ex) {
			throw new ResourceNotFoundException(ex.getMessage());
		}
	}

}
