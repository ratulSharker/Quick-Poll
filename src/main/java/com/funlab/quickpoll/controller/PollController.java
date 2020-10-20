package com.funlab.quickpoll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.funlab.quickpoll.repositoy.PollRepository;

@RestController
public class PollController {

	@Autowired
	private PollRepository pollRepository;

	
	
	
}
