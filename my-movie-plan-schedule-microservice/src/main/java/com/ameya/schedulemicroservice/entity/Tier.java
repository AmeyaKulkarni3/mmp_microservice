package com.ameya.schedulemicroservice.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="tiers")
@Data
public class Tier implements Comparable<Tier>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private int noOfSeats;
	
	@Column
	private int seatsBooked;
	
	@Column(nullable = false)
	private double price;
	
	@Column
	private int rows;
	
	@Column
	private int cols;
	
	@ManyToMany(mappedBy = "tiers")
	private List<Theater> theaters;
	
	@Column(nullable = false)
	private int priority;
	
	@OneToMany(mappedBy = "tier", cascade = CascadeType.PERSIST)
	private List<Seat> seats;

	@Override
	public int compareTo(Tier o) {
		if(getPriority() == 0 || o.getPriority() == 0) {
			return 0;
		}
		return getPriority() - o.getPriority();
	}

	

}
