package com.restaurantsolution.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class OrderDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="orderId",nullable=false)
	private Long orderId;
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UserId_FK", referencedColumnName = "userId", nullable = false)
	private AppUser appUser;
	
	
	private String orderFlag;
	
	private Boolean isPaymentDone;
	
	private String paymentAmount;
	
	private String tableNumber;
	
	
	public Date getOrderInitiatedAt() {
		return orderInitiatedAt;
	}

	public void setOrderInitiatedAt(Date orderInitiatedAt) {
		this.orderInitiatedAt = orderInitiatedAt;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderInitiatedAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDeliveredAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderProcessedAt;
	
	
	public Date getOrderProcessedAt() {
		return orderProcessedAt;
	}

	public void setOrderProcessedAt(Date orderProcessedAt) {
		this.orderProcessedAt = orderProcessedAt;
	}

	public Date getOrderDeliveredAt() {
		return orderDeliveredAt;
	}

	public void setOrderDeliveredAt(Date orderDeliveredAt) {
		this.orderDeliveredAt = orderDeliveredAt;
	}

	public String getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}

	public String getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public List<OrderFoodDetails> getOrderFoodDetails() {
		return orderFoodDetails;
	}

	public void setOrderFoodDetails(List<OrderFoodDetails> orderFoodDetails) {
		this.orderFoodDetails = orderFoodDetails;
	}

	@JsonManagedReference
	@OneToMany(mappedBy="orderDetails")
	private List<OrderFoodDetails> orderFoodDetails;
	
	private String orderWaitTime;

	public String getOrderWaitTime() {
		return orderWaitTime;
	}

	public void setOrderWaitTime(String orderWaitTime) {
		this.orderWaitTime = orderWaitTime;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public String getOrderFlag() {
		return orderFlag;
	}

	public void setOrderFlag(String orderFlag) {
		this.orderFlag = orderFlag;
	}

	public Boolean getIsPaymentDone() {
		return isPaymentDone;
	}

	public void setIsPaymentDone(Boolean isPaymentDone) {
		this.isPaymentDone = isPaymentDone;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
