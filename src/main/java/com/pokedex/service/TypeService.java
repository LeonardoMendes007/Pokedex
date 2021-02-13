package com.pokedex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokedex.entities.Type;
import com.pokedex.repositories.TypeRepository;
import com.pokedex.service.exception.ResourceNotFoundException;

@Service
public class TypeService {

	@Autowired
	private TypeRepository repType;
	
	public List<Type> findAllType(){
		return repType.findAll();
	}
	
	public Type findById(Integer id){	
		return repType.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id " + id + " does not exist"));
	}

	
	
}
