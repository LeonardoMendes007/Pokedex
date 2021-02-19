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

		if (repType.existsById(type.getId())) {

			throw new DatabaseException("There is already a Type with the same number. Number = " + type.getId());

		} else {

			try {
				Type typeSave = repType.save(type);

				return typeSave;
			} catch (DataIntegrityViolationException e) {

				throw new DatabaseConstraintViolationException(
						"There is already a pokemon with the same name. Name = " + type.getName());

			}

		}

	}

}
