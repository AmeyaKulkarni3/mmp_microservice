package com.ameya.theaterservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
