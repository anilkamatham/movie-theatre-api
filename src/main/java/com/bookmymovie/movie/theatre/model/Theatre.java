package com.bookmymovie.movie.theatre.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "THEATRE")
public class Theatre {

	@Id
  	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "theatre-generator")
 	@SequenceGenerator(name = "theatre-generator" , sequenceName = "theatre_generator", initialValue = 1000, allocationSize = 1)
	private int id;
	@Column(name = "THEATRE_NAME", nullable = false, length = 200)
	@NotBlank(message = "{theatre.name.notblank}")
	private String name;
	@ManyToOne(targetEntity = City.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "CITY_PK", referencedColumnName = "id")
	@Valid
	private City city;
	@OneToMany(targetEntity = Screen.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "THEATER_PK", referencedColumnName = "id")
	@Valid
	private List<Screen> screens;
	
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
	public List<Screen> getScreens() {
		return screens;
	}
	public void setScreens(List<Screen> screens) {
		this.screens = screens;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
			
}
