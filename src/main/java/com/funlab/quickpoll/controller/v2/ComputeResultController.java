package com.funlab.quickpoll.controller.v2;

import java.util.HashMap;

import com.funlab.quickpoll.dto.OptionCount;
import com.funlab.quickpoll.dto.VoteResult;
import com.funlab.quickpoll.entity.Vote;
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
	public ResponseEntity<VoteResult> computeResult(@PathVariable Long pollId) {

		VoteResult result = new VoteResult();

		Iterable<Vote> votes = voteService.findByPoll(pollId);

		Long totalCount = 0L;
		final HashMap<Long, OptionCount> optionIdWiseOptionCount = new HashMap<>();

		for (Vote vote : votes) {
			totalCount++;
			OptionCount optionCount = optionIdWiseOptionCount.get(vote.getOption().getId());

			if (optionCount == null) {
				optionCount = new OptionCount();

				optionCount.setCount(1L);
				optionCount.setOptionId(vote.getOption().getId());

				optionIdWiseOptionCount.put(vote.getOption().getId(), optionCount);
			}

			optionCount.incrementCount();
		}

		result.setTotalVotes(totalCount);
		result.setResult(optionIdWiseOptionCount.values());

		return new ResponseEntity<VoteResult>(result, HttpStatus.OK);

	}

}
