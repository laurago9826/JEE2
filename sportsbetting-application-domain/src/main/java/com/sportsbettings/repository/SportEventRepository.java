package com.sportsbettings.repository;

import org.springframework.data.repository.CrudRepository;

import com.sportsbettings.domain.SportEvent;

public interface SportEventRepository extends CrudRepository<SportEvent, Integer> {

}
