package com.thinknear.service;

import java.util.List;

import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.thinknear.model.ClassScheduling;
import com.thinknear.model.Student;
import com.thinknear.repository.BaseRepository;
import com.thinknear.repository.ClassRepository;

@Service
public class ClassServiceImpl extends CrudServiceTemplate<BaseRepository<ClassScheduling>, ClassScheduling>
		implements ClassService {

	@Override
	public void update(Integer id, ClassScheduling entity) {
		ClassScheduling classForUpdate = super.repository.getOne(id);
		classForUpdate.setDescription(entity.getDescription());
		classForUpdate.setTitle(entity.getTitle());
		super.repository.save(classForUpdate);
	}

	@Override
	public List<Student> findStudentsByParamsAndClassCode(Integer id, Student student) {
		ClassRepository classRepository = (ClassRepository) repository;
		return classRepository.findStudentsByParamsAndClassCode(id, student);
	}

	@Override
	public List<ClassScheduling> findClassesByParams(ClassScheduling classScheduling) {
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("title", ExampleMatcher.GenericPropertyMatchers.startsWith())
				.withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains());
		return super.findAll(classScheduling, matcher);
	}

}
