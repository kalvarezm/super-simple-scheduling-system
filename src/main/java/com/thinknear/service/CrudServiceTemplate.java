package com.thinknear.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.thinknear.repository.BaseRepository;
import com.thinknear.service.exception.CustomNotFoundException;

public abstract class CrudServiceTemplate<T extends BaseRepository<S>, S> {

	@Autowired
	public T repository;

	public List<S> findAll(S entity, ExampleMatcher matcher) {
		Example<S> example = Example.of(entity, matcher);
		return repository.findAll(example);
	}

	public S findById(Integer id) throws CustomNotFoundException {
		Optional<S> optional = repository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new CustomNotFoundException(CustomNotFoundException.DEFAULT_MESSAGE);
		}
	}

	public S save(S entity) {
		return repository.save(entity);
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}

	public abstract void update(Integer id, S entity);

}
