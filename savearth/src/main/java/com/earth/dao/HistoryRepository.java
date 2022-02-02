package com.earth.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.earth.entities.History;

public interface HistoryRepository extends JpaRepository<History,Integer>  {

	@Query("select u from History u where u.email = :email")
	public Page<History> findHistoryByUser(@Param("email") String email, Pageable pageable);
	
}
