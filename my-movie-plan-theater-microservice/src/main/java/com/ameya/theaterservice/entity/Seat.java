package com.ameya.theaterservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="seats")
@Data
public class Seat {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String seatNumber;
	
	@Column
	private boolean isBooked;
	
	@ManyToOne
	private Tier tier;
	
	@ManyToOne
	private Schedule schedule;

}
