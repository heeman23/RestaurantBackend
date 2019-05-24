package com.restaurantsolution.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class ItemType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getItemtype() {
		return Itemtype;
	}

	public void setItemtype(String itemtype) {
		Itemtype = itemtype;
	}

	public Long getItemId() {
		return itemTypeId;
	}

	public void setItemId(Long itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	

	public Long getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(Long itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column(name="itemtype")
	private String Itemtype;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "itemTypeId",nullable = false)
	private Long itemTypeId;
	
	@JsonManagedReference
	@OneToMany(mappedBy="itemType")
	private List<Items> items;
	
}
