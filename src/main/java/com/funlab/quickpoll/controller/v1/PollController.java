package com.funlab.quickpoll.controller.v1;


import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.funlab.quickpoll.dto.request.OptionRequestDTO;
import com.funlab.quickpoll.dto.request.PollRequestDTO;
import com.funlab.quickpoll.dto.response.CustomApiResponse;
import com.funlab.quickpoll.dto.response.PollResponseDTO;
import com.funlab.quickpoll.entity.Option;
import com.funlab.quickpoll.entity.Poll;
import com.funlab.quickpoll.exception.ResourceNotFoundException;
import com.funlab.quickpoll.service.PollService;

import org.modelmapper.ModelMapper;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController(value = "PollControllerV1")
@RequestMapping("/v1/")
@Api(value = "polls", description = "Poll API")
public class PollController {


	@Autowired
	private PollService pollService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ModelMapper modelMapper;

	@ApiOperation(value = "Get all available polls")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Polls fetched", response = PollResponseDTO.class)
	})
	@RequestMapping(value = "/polls", method = RequestMethod.GET)
	public ResponseEntity<Iterable<PollResponseDTO>> getAllPolls() {

		Iterable<Poll> polls = pollService.findAll();
		ArrayList<PollResponseDTO> pollDtos = new ArrayList<>();

		polls.forEach(poll -> {
			pollDtos.add(modelMapper.map(poll, PollResponseDTO.class));
		});

		return new ResponseEntity<Iterable<PollResponseDTO>>(pollDtos, HttpStatus.OK);
	}

	@ApiOperation(value = "Create a new poll", notes = "It will return the newly created poll and set the response header location to newly created poll", response = PollResponseDTO.class)
	@RequestMapping(value = "/polls", method = RequestMethod.POST)
	public ResponseEntity<CustomApiResponse<PollResponseDTO>> createPoll(@Valid @RequestBody PollRequestDTO pollDTO, HttpServletRequest req) {
		
		Poll poll = new Poll();
		poll.setQuestion(pollDTO.getQuestion());
		
		Set<Option> options = new HashSet<Option>();
		for (OptionRequestDTO optionDTO : pollDTO.getOptions()) {
			Option option = new Option();
			option.setId(optionDTO.getId());
			option.setValue(optionDTO.getValue());
			options.add(option);
		}
		poll.setOptions(options);
		
		poll = pollService.save(poll);

		HttpHeaders responseHeaders = new HttpHeaders();
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(poll.getId()).toUri();
		responseHeaders.setLocation(uri);

		CustomApiResponse<PollResponseDTO> response = new CustomApiResponse<PollResponseDTO>();
		response.setData(modelMapper.map(poll, PollResponseDTO.class));

		Map<String, Object> meta = new HashMap<String, Object>();
		meta.put("timestamp", new Date().getTime());
		meta.put("message", messageSource.getMessage("PollCreatedSuccessfully", null, req.getLocale()));
		response.setMeta(meta);

		return new ResponseEntity<CustomApiResponse<PollResponseDTO> >(response, responseHeaders, HttpStatus.CREATED);
	}

	
	@ApiOperation(value = "Get poll by id", response = PollResponseDTO.class)
	@RequestMapping(value = "/polls/{id}", method = RequestMethod.GET)
	public ResponseEntity<PollResponseDTO> getPoll(@PathVariable("id") Long pollId) {

		Poll poll = verifyAndGetPoll(pollId);
		PollResponseDTO pollDto = modelMapper.map(poll, PollResponseDTO.class);
		return new ResponseEntity<PollResponseDTO>(pollDto, HttpStatus.OK);
	}

	
	@ApiOperation(value = "Update a particular poll", response = PollResponseDTO.class)
	@RequestMapping(value = "/polls/{id}", method = RequestMethod.PUT)
	public ResponseEntity<PollResponseDTO> updatePoll(@PathVariable("id") Long pollId, @RequestBody Poll poll) {
		verifyAndGetPoll(pollId);
		poll.setId(pollId);
		poll = pollService.save(poll);
		PollResponseDTO pollDto = modelMapper.map(poll, PollResponseDTO.class);
		return new ResponseEntity<PollResponseDTO>(pollDto, HttpStatus.OK);
	}


	@ApiOperation(value = "Delete a particular poll")
	@RequestMapping(value = "/polls/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePoll(@PathVariable("id") Long pollId) {
		verifyAndGetPoll(pollId);
		pollService.deleteById(pollId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	private Poll verifyAndGetPoll(Long pollId) throws ResourceNotFoundException {
		try {
			Optional<Poll> nullablePoll = pollService.findById(pollId);
			return nullablePoll.get();
		} catch (NoSuchElementException ex) {
			throw new ResourceNotFoundException(ex.getMessage());
		}
	}

}
