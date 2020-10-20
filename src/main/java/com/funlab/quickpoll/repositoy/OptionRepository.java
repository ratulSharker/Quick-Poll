package com.funlab.quickpoll.repositoy;

import org.springframework.data.repository.CrudRepository;

import com.funlab.quickpoll.domain.Option;

public interface OptionRepository extends CrudRepository<Option, Long> {

}
