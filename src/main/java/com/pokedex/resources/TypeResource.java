package com.pokedex.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pokedex.entities.Type;
import com.pokedex.entities.forms.TypeForm;
import com.pokedex.service.TypeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/types")
public class TypeResource {

	@Autowired
	private TypeService service;
	
	@GetMapping
	public ResponseEntity<List<Type>> getAllTypes(){
		List<Type> types = service.findAllType();
		return ResponseEntity.status(HttpStatus.OK).body(types);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TypeForm> getTypeById(@PathVariable Integer id){
		Type type = service.findById(id);
		
		TypeForm typeForm = new TypeForm(type.getId(), type.getName(), type.getPokemonsTypes(), type.getPokemonsWeaknesses());

		return ResponseEntity.status(HttpStatus.OK).body(typeForm);
	}
	
	@PostMapping
	public ResponseEntity<TypeForm> createType(@Valid @RequestBody TypeForm typeForm ){
		
		Type typeSave = service.save(typeForm.novoType());
		
		TypeForm typeFormSave = new TypeForm(typeSave.getId(), typeSave.getName(), typeSave.getPokemonsTypes(), typeSave.getPokemonsWeaknesses());
		
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(typeFormSave.getId()).toUri();
		
		return ResponseEntity.created(uri).body(typeFormSave);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<TypeForm> updateType(@PathVariable("id") Integer id, @Valid @RequestBody TypeForm typeForm){
		
        Type type = service.update(id, typeForm.novoType());
        
        TypeForm newTypeForm = new TypeForm(type.getId(), type.getName(), type.getPokemonsTypes(), type.getPokemonsWeaknesses());
		
		return ResponseEntity.status(HttpStatus.OK).body(newTypeForm);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteType(@PathVariable("id") Integer id){
		
        service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}
