package com.thinknear.service;

import java.util.List;

import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.thinknear.model.ClassScheduling;
import com.thinknear.model.Student;
import com.thinknear.repository.BaseRepository;
import com.thinknear.repository.StudentRepository;

@Service
public class StudentServiceImpl extends CrudServiceTemplate<BaseRepository<Student>, Student>
		implements StudentService {

	@Override
	public void update(Integer id, Student entity) {
		Student studentForUpdate = super.repository.getOne(id);
		studentForUpdate.setFirstName(studentForUpdate.getFirstName());
		studentForUpdate.setLastName(studentForUpdate.getLastName());
		super.repository.save(studentForUpdate);
	}

	@Override
	public List<ClassScheduling> findClassesByParamsAndStudentId(Integer id, ClassScheduling classScheduling) {
		StudentRepository studentRepository = (StudentRepository) repository;
		return studentRepository.findClassesByParamsAndStudentId(id, classScheduling);
	}

	@Override
	public List<Student> findStudentsByParams(Student student) {
		ExampleMatcher matcher = ExampleMatcher.matchingAll();
		return super.findAll(student, matcher);
	}

}
