package com.ameya.theaterservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
