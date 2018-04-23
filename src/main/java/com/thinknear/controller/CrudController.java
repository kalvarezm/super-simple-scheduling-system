package com.thinknear.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thinknear.model.BaseModel;
import com.thinknear.service.CrudServiceTemplate;
import com.thinknear.service.exception.CustomNotFoundException;

public abstract class CrudController<T extends CrudServiceTemplate<?, S>, S extends BaseModel> {

	@Autowired
	public T crudService;

	@GetMapping("/{id}")
	public S findById(@PathVariable(value = "id") Integer id) throws CustomNotFoundException {
		return crudService.findById(id);
	}

	@PostMapping
	public ResponseEntity<S> save(@RequestBody S entity) {
		S saved = crudService.save(entity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<S> delete(@PathVariable(value = "id") Integer id) {
		crudService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public void update(@PathVariable(value = "id") Integer id, @RequestBody S entity) {
		crudService.update(id, entity);
	}

}
