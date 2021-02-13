package com.pokedex.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokedex.entities.Pokemon;
import com.pokedex.entities.forms.PokemonForm;
import com.pokedex.service.PokemonService;

@RestController
@RequestMapping("/pokemons")
public class PokemonResource {

	@Autowired
	private PokemonService service;

	@GetMapping
	public ResponseEntity<Page<Pokemon>> getAllPokemons(Pageable pageable) {
		Page<Pokemon> pokemons = service.findAll(pageable);
		return ResponseEntity.status(HttpStatus.OK).body(pokemons);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PokemonForm> getPokemonById(@PathVariable("id") Integer id) {
		Pokemon pokemon = service.findById(id);
		PokemonForm pokemonForm = new PokemonForm(pokemon.getId(),pokemon.getName(),pokemon.getHeight(),pokemon.getWeight(),pokemon.getGender(),pokemon.getImgUrl(),
				pokemon.getTypes(),pokemon.getWeaknesses(),pokemon.getNextEvolution(),pokemon.getPreviusEvolution());
		
		return ResponseEntity.status(HttpStatus.OK).body(pokemonForm);
	}

	@GetMapping("/type")
	public ResponseEntity<List<Pokemon>> getAllPokemons(Integer id) {
		List<Pokemon> pokemons = service.findAllByType(id);
		return ResponseEntity.status(HttpStatus.OK).body(pokemons);
	}
	
	

}
