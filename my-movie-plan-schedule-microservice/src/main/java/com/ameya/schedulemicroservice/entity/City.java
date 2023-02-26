package com.ameya.schedulemicroservice.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="cities")
@Data
public class City {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 25)
	private String name;
	
	@OneToMany(mappedBy = "city",cascade = CascadeType.PERSIST)
	private List<Address> addresses;
	
	@OneToMany(mappedBy = "city", cascade = CascadeType.PERSIST)
	private List<Theater> theatres;

}
