package com.pokedex.entities.forms;

import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import com.pokedex.entities.Pokemon;
import com.pokedex.entities.Type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PokemonForm {

	@Min(value= 1, message="{id.min}")
	private Integer id;
	@NotBlank(message = "{name.not.blank}")
	@Length(max = 30, message = "{name.max}")
	private String name;
	@DecimalMin(value = "0.1", message = "{height.decimal.min}")
	private Double height;
	@DecimalMin(value = "0.1", message = "{weight.decimal.min}")
	private Double weight;
	@Column(length = 2)
	@Size(max = 2, min = 1, message = "{gender.size}")
	@NotBlank(message = "{gender.not.blank}")
	private String gender;

	@URL(message = "{img.not.blank}")
	@NotBlank(message = "{img.url}")
	private String imgUrl;

	private Set<Type> types;
	private Set<Type> weaknesses;
	private Set<Pokemon> nextEvolution;
	private Set<Pokemon> previusEvolution;

	public PokemonForm(Integer id,
			@NotBlank(message = "{name.not.blank}") @Max(value = 30, message = "{name.max}") String name,
			@DecimalMin(value = "0.1", message = "{height.decimal.min}") Double height,
			@DecimalMin(value = "0.1", message = "{weight.decimal.min}") Double weight,
			@Size(max = 2, min = 1, message = "{gender.size}") @NotBlank(message = "{gender.not.blank}") String gender,
			@URL(message = "{img.not.blank}") @NotBlank(message = "{img.url}") String imgUrl, Set<Type> types,
			Set<Type> weaknesses, Set<Pokemon> nextEvolution, Set<Pokemon> previusEvolution) {
		super();
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
		return new Pokemon(id, name, height, weight, gender, imgUrl, types, weaknesses, nextEvolution,
				previusEvolution);
	}


}
