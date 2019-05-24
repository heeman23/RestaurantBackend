package com.restaurantsolution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.restaurantsolution.domain.ItemType;


@Transactional
public interface FoodRepository extends JpaRepository<ItemType, Long> {

	
	
}
