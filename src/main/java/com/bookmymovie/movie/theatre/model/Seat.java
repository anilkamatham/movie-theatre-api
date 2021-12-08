package com.bookmymovie.movie.theatre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "SEAT")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Seat {

    @Id
  	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seat-generator")
 	@SequenceGenerator(name = "seat-generator" , sequenceName = "seat_generator", initialValue = 1000, allocationSize = 1)
	private int id;
    @Column(name = "ROW_NO", nullable = false)
    @NotNull(message = "{seat.rowno.notblank}")
	private int rowNo;
    @Column(name = "SEAT_NO", nullable = false)
    @NotNull(message = "{seat.seatno.notblank}")
	private int seatNo;
	@Column(name = "PRICE")
    private int price;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRowNo() {
		return rowNo;
	}
	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}
	public int getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	 	 
}
