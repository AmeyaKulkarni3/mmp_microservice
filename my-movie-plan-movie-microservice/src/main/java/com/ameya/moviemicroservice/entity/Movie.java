package com.ameya.moviemicroservice.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "movies")
@Data
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private LocalDate releaseDate;
	
	@Column
	private String directors;
	
	@Column
	private String cast;
	
	@Column
	private String poster;
	
	@Column
	private String duration;
	
	@Column
	private boolean isActive;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "movies_genres", joinColumns = @JoinColumn(name = "movies_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "genres_id", referencedColumnName = "id"))
	private List<Genre> genres;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "movies_languages", joinColumns = @JoinColumn(name = "movies_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "languages_id", referencedColumnName = "id"))
	private List<Language> languages;

}
