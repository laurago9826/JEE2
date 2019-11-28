package com.sportsbettings.repository;

import org.springframework.data.repository.CrudRepository;

import com.sportsbettings.domain.Wager;

public interface WagerRepository extends CrudRepository<Wager, Integer> {

}
