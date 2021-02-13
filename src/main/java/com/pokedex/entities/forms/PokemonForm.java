package com.pokedex.entities.forms;

import java.util.Set;

import com.pokedex.entities.Pokemon;
import com.pokedex.entities.Type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PokemonForm {

	private Integer id;
	private String name;
	private Double height;
	private Double weight;
	private String gender;
	private String imgUrl;
	private Set<Type> types;
	private Set<Type> weaknesses;
	private Set<Pokemon> nextEvolution;
	private Set<Pokemon> previusEvolution;

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
	

}
