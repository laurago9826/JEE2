package com.sportsbettings.repository;

import org.springframework.data.repository.CrudRepository;

import com.sportsbettings.domain.Player;

public interface PlayerRepository extends CrudRepository<Player, Integer> {

}
