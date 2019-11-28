package com.sportsbettings.repository;

import org.springframework.data.repository.CrudRepository;

import com.sportsbettings.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
