package com.ameya.schedulemicroservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
	
	private String bookingId;

}
