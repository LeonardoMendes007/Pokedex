package com.pokedex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.pokedex.entities.Pokemon;
import com.pokedex.entities.Type;
import com.pokedex.repositories.TypeRepository;
import com.pokedex.service.exception.DatabaseConstraintViolationException;
import com.pokedex.service.exception.DatabaseException;
import com.pokedex.service.exception.ResourceNotFoundException;

@Service
public class TypeService {

	@Autowired
	private TypeRepository repType;

	public List<Type> findAllType() {
		return repType.findAll();
	}

	public Type findById(Integer id) {
		return repType.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id " + id + " does not exist"));
	}

	public Type save(Type type) {

		try {
			Type typeSave = repType.save(type);

			return typeSave;
		} catch (DataIntegrityViolationException e) {

			throw new DatabaseConstraintViolationException(
					"There is already a pokemon with the same name. Name = " + type.getName());

		}

	}

	public Type update(Integer id, Type newType) {
		Type oldType = repType.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id " + id + " does not exist"));
		oldType.setName(newType.getName());
		return repType.save(oldType);
	}
	
	public void delete(Integer id) {
		Type type = repType.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id " + id + " does not exist"));
		try {
			repType.delete(type);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseConstraintViolationException("It is not possible to exclude the type with id = " + id + ", as the type has one or more related Pokémons.");
		}
	}


}
