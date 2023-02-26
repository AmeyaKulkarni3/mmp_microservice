package com.ameya.theaterservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="addresses")
@Data
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String line1;
	
	@Column(nullable = false, length = 100)
	private String line2;
	
	@ManyToOne
	private City city;
	
	@Column(nullable = false)
	private String pincode;
	
	@OneToOne
	private Theater theater;

}
