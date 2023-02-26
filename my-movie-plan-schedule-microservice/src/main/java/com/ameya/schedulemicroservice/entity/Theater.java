package com.ameya.schedulemicroservice.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="theaters")
@Data
public class Theater {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable= false, length = 250)
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	@ManyToOne
	private City city;
	
	@OneToMany(mappedBy = "theater")
	private List<Schedule> schedules;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "theaters_tiers", joinColumns = @JoinColumn(name = "theaters_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "tiers_id", referencedColumnName = "id"))
	private List<Tier> tiers;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Partner partner;

}
