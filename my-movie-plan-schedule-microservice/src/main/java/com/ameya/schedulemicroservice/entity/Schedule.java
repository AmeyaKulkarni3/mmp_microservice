package com.ameya.schedulemicroservice.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="schedules")
@Data
public class Schedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Movie movie;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Theater theater;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Showtime showtime;
	
	@Column
	private LocalDate date;
	
	@OneToMany(mappedBy = "schedule", cascade = CascadeType.PERSIST)
	private List<Seat> seats;

}
