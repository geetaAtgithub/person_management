package com.personmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long  id;
	
	@Column(name = "personId",nullable=false)
	private long personId;
	
	@Column(length=200)
	private String street;
	
	@Column(nullable=false, length=50)
	private String city;
	
	@Column(nullable=false, length=50)
	private String state;
	
	@Column( name = "postalCode", nullable=false, length=50)
	private String postalCode;		

	public Address() {}
	public Address(long id, long personId, String street, String city, String state, String postalCode) {
		super();
		this.id = id;
		this.personId = personId;
		this.street = street;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public long  getId() {
		return id;
	}

	public void setId(long  id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", city=" + city + ", state=" + state + ", postalCode="
				+ postalCode + "]";
	}

}
