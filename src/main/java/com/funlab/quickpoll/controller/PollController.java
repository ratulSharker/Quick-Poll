package com.funlab.quickpoll.controller;

import java.net.URI;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

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

import com.funlab.quickpoll.domain.Option;
import com.funlab.quickpoll.domain.Poll;
import com.funlab.quickpoll.dto.OptionDTO;
import com.funlab.quickpoll.dto.PollDTO;
import com.funlab.quickpoll.exception.ResourceNotFoundException;
import com.funlab.quickpoll.repositoy.PollRepository;

@RestController
public class PollController {

	@Autowired
	private PollRepository pollRepository;

	@RequestMapping(value = "/polls", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Poll>> getAllPolls() {
		return new ResponseEntity<Iterable<Poll>>(this.pollRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/polls", method = RequestMethod.POST)
	public ResponseEntity<Poll> createPoll(@Valid @RequestBody PollDTO pollDTO) {
		
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

		return new ResponseEntity<Poll>(poll, responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/polls/{id}", method = RequestMethod.GET)
	public ResponseEntity<Poll> getPoll(@PathVariable("id") Long pollId) {

		return new ResponseEntity<>(verifyAndGetPoll(pollId), HttpStatus.OK);
	}

	@RequestMapping(value = "/polls/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Poll> updatePoll(@PathVariable("id") Long pollId, @RequestBody Poll poll) {
		verifyAndGetPoll(pollId);
		poll.setId(pollId);
		poll = this.pollRepository.save(poll);
		return new ResponseEntity<Poll>(poll, HttpStatus.OK);
	}

	@RequestMapping(value = "/polls/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePoll(@PathVariable("id") Long pollId) {
		verifyAndGetPoll(pollId);
		this.pollRepository.deleteById(pollId);
		return new ResponseEntity(HttpStatus.OK);
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
