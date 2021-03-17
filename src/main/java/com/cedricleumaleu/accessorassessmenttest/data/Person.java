package com.cedricleumaleu.accessorassessmenttest.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.cedricleumaleu.accessorassessmenttest.enums.ColorEnums;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Person {
	
	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String name;
	@Column
	private String lastname;
	@Column
	private String zipcodeCity;
	@Column
	private int colorId;
	@JsonIgnore
	@Transient
	private String color;

	public Person(Long id, String name, String lastname, String zipcodeCity, int colorId) {
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.zipcodeCity = zipcodeCity;
		this.colorId = colorId;
		ColorEnums ce = ColorEnums.values()[colorId - 1];
		this.color = ce.getName();
	}
	
	public Person(String name, String lastname, String zipcodeCity, int colorId) {
		this.name = name;
		this.lastname = lastname;
		this.zipcodeCity = zipcodeCity;
		this.colorId = colorId;
		ColorEnums ce = ColorEnums.values()[colorId - 1];
		this.color = ce.getName();
	}

	public Person() {

	}

	public int getColorId() {
		return this.colorId;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public String getZipcodeCity() {
		return zipcodeCity;
	}

	public void setZipcodeCity(String zipcodeCity) {
		this.zipcodeCity = zipcodeCity;
	}

	public String getColor() {
		return color;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return id + " : " + name + ", " + lastname + ", " + zipcodeCity + ", " + colorId ;
	}

	

}
