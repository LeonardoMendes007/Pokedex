package com.pokedex.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "type")
public class Type {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank
	@Column(length = 20, unique = true)
	private String name;

	@ManyToMany(mappedBy = "types")
	@JsonIgnore
	private Set<Pokemon> pokemonsTypes = new HashSet<>();

	@ManyToMany(mappedBy = "weaknesses")
	@JsonIgnore
	private Set<Pokemon> pokemonsWeaknesses = new HashSet<>();

	public Type(String name) {
		super();
		this.name = name;
	}

	
}
