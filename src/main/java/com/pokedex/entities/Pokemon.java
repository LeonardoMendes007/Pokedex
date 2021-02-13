package com.pokedex.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "pokemon")
public class Pokemon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	@Column(unique = true, length = 30, nullable = false)
	@NotBlank
	private String name;
	@NotNull
	private Double height;
	@NotNull
	private Double weight;
	@Column(length = 2)
	@NotBlank
	private String gender;
	@URL
	private String imgUrl;

	@ManyToMany
	@JoinTable(name = "pokemon_type", joinColumns = @JoinColumn(name = "pokemon_id"), inverseJoinColumns = @JoinColumn(name = "type_id"))
	private Set<Type> types = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "pokemon_weaknesses", joinColumns = @JoinColumn(name = "pokemon_id"), inverseJoinColumns = @JoinColumn(name = "weakness_id"))
	private Set<Type> weaknesses = new HashSet<>();

	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "nextEvolution", joinColumns = @JoinColumn(name = "pokemon_id"), inverseJoinColumns = @JoinColumn(name = "next_evoltuion_id"))
	private Set<Pokemon> nextEvolution = new HashSet<>();
	
	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "previusEvolution", joinColumns = @JoinColumn(name = "pokemon_id"), inverseJoinColumns = @JoinColumn(name = "previus_evoltuion_id"))
	private Set<Pokemon> previusEvolution = new HashSet<>();

	public Pokemon(Integer id, String name, Double height, Double weight, String gender, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.height = height;
		this.weight = weight;
		this.gender = gender;
		this.imgUrl = imgUrl;
	}

}
