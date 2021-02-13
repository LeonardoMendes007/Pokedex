package com.pokedex.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pokedex.entities.Pokemon;
import com.pokedex.repositories.PokemonRepository;
import com.pokedex.service.exception.ResourceNotFoundException;

@Service
public class PokemonService {

	@Autowired
	private PokemonRepository repPokemon;
	
	public List<Pokemon> findAll(){
		return repPokemon.findAll();
	}
	
	public Pokemon findById(Integer id) {
		Optional<Pokemon> obj = repPokemon.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Id " + id + " does not exist"));
	}

	public Page<Pokemon> findAll(Pageable pageable) {
		return repPokemon.findAll(pageable);
	}
	
	public List<Pokemon> findAllByType(Integer id) {
		
		List<Pokemon> pokemons = repPokemon.findAllPokemonByType(id);
		
		if(pokemons.size() == 0) {
			throw new ResourceNotFoundException("Id " + id + " does not exist");
		}else {
			return pokemons;	
		}
		 
	}


}
