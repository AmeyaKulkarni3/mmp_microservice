package com.ameya.usermicroservice.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false)
	private String firstName;
	
	@Column(nullable=false, length = 50)
	private String lastName;
	
	@Column(nullable=false, length = 50)
	private String userId;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false, length = 120, unique = true)
	private String email;
	
	@Column(nullable=false, length = 15)
	private String phone;
	
	@OneToMany(mappedBy = "user")
	private List<Booking> bookings;

}
