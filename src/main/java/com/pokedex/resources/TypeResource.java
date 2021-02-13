package com.pokedex.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokedex.entities.Type;
import com.pokedex.service.TypeService;

@RestController
public class TypeResource {

	@Autowired
	private TypeService service;
	
	@GetMapping("/types")
	public ResponseEntity<List<Type>> getAllTypes(){
		List<Type> types = service.findAllType();
		return ResponseEntity.status(HttpStatus.OK).body(types);
	}
}
