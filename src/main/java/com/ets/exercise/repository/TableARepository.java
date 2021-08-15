package com.ets.exercise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ets.exercise.entity.TableA;

public interface TableARepository extends JpaRepository<TableA, Long> {

	List<TableA> findByUserId(String userId);
	
	TableA findTop1ByUserIdOrderByTransactionDateDesc(String userId);
	
}
