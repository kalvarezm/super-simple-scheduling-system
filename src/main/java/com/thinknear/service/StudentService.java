package com.thinknear.service;

import java.util.List;

import com.thinknear.model.ClassScheduling;
import com.thinknear.model.Student;

public interface StudentService {
	
	List<Student> findStudentsByParams(Student student);

	List<ClassScheduling> findClassesByParamsAndStudentId(Integer id, ClassScheduling classScheduling);

}
