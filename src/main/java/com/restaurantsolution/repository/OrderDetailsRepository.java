package com.restaurantsolution.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.restaurantsolution.domain.OrderDetails;

@Transactional
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long>  {

	@Query("select o from OrderDetails o left outer join fetch o.appUser au where o.tableNumber=:tableNumber and au.contact=:contact")
	public List<OrderDetails> findByTableIdContact(String tableNumber,String contact);
		
		
	
	
	
}
