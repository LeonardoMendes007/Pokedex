package com.pokedex.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pokedex.entities.Pokemon;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {

	Optional<Pokemon> findByName(String name);
	
	@Query("from Pokemon p join p.types t where t.id = :id")
	Page<Pokemon> findAllPokemonByType(@Param("id") Integer id,Pageable pageable);
	
}
