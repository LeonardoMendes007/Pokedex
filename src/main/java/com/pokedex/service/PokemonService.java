package com.pokedex.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pokedex.entities.Pokemon;
import com.pokedex.entities.Type;
import com.pokedex.entities.forms.PokemonForm;
import com.pokedex.repositories.PokemonRepository;
import com.pokedex.service.exception.DatabaseConstraintViolationException;
import com.pokedex.service.exception.DatabaseException;
import com.pokedex.service.exception.ResourceNotFoundException;

@Service
public class PokemonService {

	@Autowired
	private PokemonRepository repPokemon;

	public List<Pokemon> findAll() {
		return repPokemon.findAll();
	}

	public Pokemon findById(Integer id) {
		Optional<Pokemon> obj = repPokemon.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("There are no pokemons with the id " + id));
	}

	public Page<Pokemon> findAll(Pageable pageable) {
		return repPokemon.findAll(pageable);
	}

	public Page<Pokemon> findAllByType(Integer id, Pageable pageable) {

		Page<Pokemon> pokemons = repPokemon.findAllPokemonByType(id, pageable);

		if (pokemons.isEmpty()) {
			throw new ResourceNotFoundException("There are no pokemons with type id " + id);
		} else {
			return pokemons;
		}

	}

	public Pokemon save(Pokemon pokemon) {

		if(repPokemon.existsById(pokemon.getId())) {
			
			throw new DatabaseException("There is already a pokemon with the same number. Number = " + pokemon.getId());
			
		}else{
			
			try {
				Pokemon pokemonSave = repPokemon.save(pokemon);
				
				return pokemonSave;
			} catch (DataIntegrityViolationException e) {
				
				throw new DatabaseConstraintViolationException("There is already a pokemon with the same name. Name = " + pokemon.getName());
				
			}
			
		}
		
		
		
	}

	public Pokemon update(Integer id, Pokemon newPokemon) {
		
		Pokemon oldPokemon = repPokemon.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id " + id + " does not exist"));
		oldPokemon.setName(newPokemon.getName());
		oldPokemon.setGender(newPokemon.getGender());
		oldPokemon.setHeight(newPokemon.getHeight());
		oldPokemon.setWeight(newPokemon.getWeight());
		oldPokemon.setImgUrl(newPokemon.getImgUrl());
		oldPokemon.setTypes(newPokemon.getTypes());
		oldPokemon.setWeaknesses(newPokemon.getWeaknesses());
		oldPokemon.setNextEvolution(newPokemon.getNextEvolution());
		oldPokemon.setPreviusEvolution(newPokemon.getPreviusEvolution());
		return repPokemon.save(oldPokemon);
		
	}

	public void delete(Integer id) {
		Pokemon pokemon = repPokemon.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id " + id + " does not exist"));
		try {
			repPokemon.delete(pokemon);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseConstraintViolationException("It is not possible to exclude the type with id = " + id + ".");
		}
		
		
	}

}
