package com.restaurantsolution.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class OrderFoodDetails implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "orderFoodDetailsId",nullable = false)
	private Long orderFoodDetailsId;
	
	public Long getOrderFoodDetailsId() {
		return orderFoodDetailsId;
	}

	public void setOrderFoodDetailsId(Long orderFoodDetailsId) {
		this.orderFoodDetailsId = orderFoodDetailsId;
	}

	public String getOrderFoodType() {
		return orderFoodType;
	}

	public void setOrderFoodType(String orderFoodType) {
		this.orderFoodType = orderFoodType;
	}

	public String getOrderFood() {
		return orderFood;
	}

	public void setOrderFood(String orderFood) {
		this.orderFood = orderFood;
	}

	public OrderDetails getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(OrderDetails orderDetails) {
		this.orderDetails = orderDetails;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String orderFoodType;
	
	private String orderFood;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_orderId", referencedColumnName = "orderId", nullable = false)
	private OrderDetails orderDetails;
	
	
	
}
