package com.pokedex.entities.forms;

import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.URL;

import com.pokedex.entities.Pokemon;
import com.pokedex.entities.Type;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

public class PokemonForm {

	private @Getter Integer id;
	@Column(unique = true, length = 30, nullable = false)
	private @Getter @Setter @NotBlank String name;
	private @Getter @Setter @NotNull Double height;
	private @Getter @Setter @NotNull Double weight;
	private @Getter @Setter @Column(length = 2) @NotBlank String gender;
	private @Getter @Setter @URL String imgUrl;
	private @Getter @Setter Set<Type> types;
	private @Getter @Setter Set<Type> weaknesses;
	private @Getter @Setter Set<Pokemon> nextEvolution;
	private @Getter @Setter Set<Pokemon> previusEvolution;

	public PokemonForm(Integer id, String name, Double height, Double weight, String gender, String imgUrl,
			Set<Type> types, Set<Type> weaknesses, Set<Pokemon> nextEvolution, Set<Pokemon> previusEvolution) {
		this.id = id;
		this.name = name;
		this.height = height;
		this.weight = weight;
		this.gender = gender;
		this.imgUrl = imgUrl;
		this.types = types;
		this.weaknesses = weaknesses;
		this.nextEvolution = nextEvolution;
		this.previusEvolution = previusEvolution;

	}

	public Pokemon novoPokemon() {
		return new Pokemon(id, name, height, weight, gender, imgUrl, types, weaknesses, nextEvolution, previusEvolution);
	}

}
