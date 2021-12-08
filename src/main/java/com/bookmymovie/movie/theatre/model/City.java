package com.bookmymovie.movie.theatre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "CITY")
public class City {

	@Id
  	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city-generator")
 	@SequenceGenerator(name = "city-generator" , sequenceName = "city_generator", initialValue = 1000, allocationSize = 1)
	private int id;
	@Column(name = "CITY_NAME", nullable = false, length = 200)
	@NotBlank(message = "{city.name.notblank}")
	private String name;
	@Column(name = "CITY_STATE", nullable = false, length = 200)
	@NotBlank(message = "{city.state.notblank}")
	private String state;
	@Column(name = "CITY_ZIPCODE", nullable = false, length = 20)
	@NotBlank(message = "{city.zipcode.notblank}")
	private String zipCode;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
		
}
