package com.thinknear.service;

import java.util.List;

import com.thinknear.model.ClassScheduling;
import com.thinknear.model.Student;

public interface ClassService{
	
	List<ClassScheduling> findClassesByParams(ClassScheduling classScheduling);
	
	List<Student> findStudentsByParamsAndClassCode(Integer id, Student student);

}
