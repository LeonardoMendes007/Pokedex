package com.pokedex.entities.forms;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import com.pokedex.entities.Pokemon;
import com.pokedex.entities.Type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeForm {

	private Integer id;
	@NotBlank(message = "The name field cannot is empty.")
	@Column(length = 20, unique = true)
	private String name;

	private Set<Pokemon> pokemonsTypes = new HashSet<>();

	private Set<Pokemon> pokemonsWeaknesses = new HashSet<>();

	public TypeForm(Integer id, String name, Set<Pokemon> pokemonsTypes, Set<Pokemon> pokemonsWeaknesses) {
		super();
		this.id = id;
		this.name = name;
		this.pokemonsTypes = pokemonsTypes;
		this.pokemonsWeaknesses = pokemonsWeaknesses;
	}
	
	public Type novoType() {
		return new Type(id, name, pokemonsTypes, pokemonsWeaknesses);
	}

	

	
}
