package com.pokedex.config;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.pokedex.entities.Pokemon;
import com.pokedex.entities.Type;
import com.pokedex.repositories.PokemonRepository;
import com.pokedex.repositories.TypeRepository;

@Configuration
@Profile("test")
public class ConfigTest implements CommandLineRunner{

	@Autowired
	private PokemonRepository repPokemon;
	
	@Autowired
	private TypeRepository repType;
	

	@Override
	public void run(String... args) throws Exception {
		
		Pokemon p1 = new Pokemon(1, "Bulbasaur" , 0.7, 6.9, "FM","https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png");
		Pokemon p2 = new Pokemon(2, "Ivysaur" , 1.0, 13.0, "FM", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/002.png");
		Pokemon p3 = new Pokemon(3, "Venusaur" , 2.0, 100.0, "FM", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/003.png");
		Pokemon p4 = new Pokemon(4, "Charmander" , 0.6, 8.5, "FM","https://assets.pokemon.com/assets/cms2/img/pokedex/full/004.png");
		Pokemon p5 = new Pokemon(5, "Charmeleon" , 1.1, 19.0, "FM", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/005.png");
	
		
		Type t1 = new Type("Água");
		Type t2 = new Type("Dragão");
		Type t3 = new Type("Elétrico");
		Type t4 = new Type("Fantasma");
		Type t5 = new Type("Fogo");
		Type t6 = new Type("Gelo");
		Type t7 = new Type("Grama");
		Type t8 = new Type("Inseto");
		Type t9 = new Type("Luta");
		Type t10 = new Type("Normal");
		Type t11 = new Type("Pedra");
		Type t12 = new Type("Psíquico");
		Type t13 = new Type("Terrestre");
		Type t14 = new Type("Venenoso");
		Type t15 = new Type("Voador");
		Type t16 = new Type("Aço");
		Type t17 = new Type("Sombrio");
		Type t18 = new Type("Fada");
		Type t19 = new Type("Aço");
		
		repType.saveAll(Arrays.asList(t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19));

		
		p1.getTypes().addAll(Arrays.asList(t7,t14));
		p2.getTypes().addAll(Arrays.asList(t7,t14));
		p3.getTypes().addAll(Arrays.asList(t7,t14));
		p4.getTypes().addAll(Arrays.asList(t5));
		p5.getTypes().addAll(Arrays.asList(t5));
		
		p1.getWeaknesses().addAll(Arrays.asList(t5,t12,t15,t6));
		p2.getWeaknesses().addAll(Arrays.asList(t5,t12,t15,t6));
		p3.getWeaknesses().addAll(Arrays.asList(t5,t12,t15,t6));
		p4.getWeaknesses().addAll(Arrays.asList(t1,t13,t11));
		p5.getWeaknesses().addAll(Arrays.asList(t1,t13,t11));

		repPokemon.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		p1.getNextEvolution().add(p2);
		p2.getNextEvolution().add(p3);
		p2.getPreviusEvolution().add(p1);
		p3.getPreviusEvolution().add(p2);
		
		repPokemon.saveAll(Arrays.asList(p1,p2,p3));
		
	}

}
