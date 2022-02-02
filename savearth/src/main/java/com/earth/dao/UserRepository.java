package com.earth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.earth.entities.Puser;

public interface UserRepository extends JpaRepository<Puser,Integer>{

	@Query("select u from Puser u where u.email = :email")
	public Puser getUserByUserName(@Param("email") String email);

	

	
	
	
}
