package com.funlab.quickpoll.repositoy;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.funlab.quickpoll.domain.Option;


@Repository
public interface OptionRepository extends CrudRepository<Option, Long> {

}
