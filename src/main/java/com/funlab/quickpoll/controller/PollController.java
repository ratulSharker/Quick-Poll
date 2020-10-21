package com.funlab.quickpoll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.funlab.quickpoll.domain.Poll;
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
	public ResponseEntity<Poll> createPoll(@RequestBody Poll poll) {
		poll = this.pollRepository.save(poll);
		return new ResponseEntity<Poll>(poll, HttpStatus.CREATED);
	}

}
