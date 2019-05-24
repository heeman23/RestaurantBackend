package com.restaurantsolution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.restaurantsolution.domain.OrderFoodDetails;

@Transactional
public interface OrderFoodDetailsRepository extends JpaRepository<OrderFoodDetails, Long> {

}
