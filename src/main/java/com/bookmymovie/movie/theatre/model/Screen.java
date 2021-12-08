package com.bookmymovie.movie.theatre.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "SCREEN")
public class Screen {

	@Id
  	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "screen-generator")
 	@SequenceGenerator(name = "screen-generator" , sequenceName = "screen_generator", initialValue = 1000, allocationSize = 1)
	private int id;
	@Column(name = "SCREEN_NAME", nullable = false, length = 50)
	@NotBlank(message = "{screen.name.notblank}")
	private String name;	
	@OneToMany(targetEntity = Seat.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "SCREEN_PK", referencedColumnName = "id")
	@Valid
	private List<Seat> seats;
	
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
	public List<Seat> getSeats() {
		return seats;
	}
	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
		
}
