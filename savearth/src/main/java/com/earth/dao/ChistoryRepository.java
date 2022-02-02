package com.earth.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.earth.entities.Chistory;



public interface ChistoryRepository extends JpaRepository<Chistory,Integer>{

	@Query("select u from Chistory u where u.email = :email")
	public Page<Chistory> findChistoryByCuser(@Param("email") String email, Pageable pageable);
	
	
	
}
