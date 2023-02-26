package com.ameya.theaterservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private Address address;
	
	@ManyToOne
	private City city;
	
	@OneToMany(mappedBy = "scheduleId")
	private List<Schedule> schedules;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "theaters_tiers", joinColumns = @JoinColumn(name = "theaters_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "tiers_id", referencedColumnName = "id"))
	private List<Tier> tiers;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Partner partner;

}
