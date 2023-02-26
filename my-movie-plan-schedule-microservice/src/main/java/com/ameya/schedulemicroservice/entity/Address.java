package com.ameya.schedulemicroservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
