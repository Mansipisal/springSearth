package com.earth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.earth.entities.Cuser;



public interface consumerRepository extends JpaRepository<Cuser,Integer> {

	@Query("select u from Cuser u where u.email = :email")
	public Cuser getCuserByUserName(@Param("email") String email);
	
}
