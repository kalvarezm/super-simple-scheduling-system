package com.thinknear.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thinknear.model.ClassScheduling;
import com.thinknear.model.Student;

public interface ClassRepository extends BaseRepository<ClassScheduling> {
	
	List<ClassScheduling> findByStudents_StudentId(Integer studentId);
	
	@Query("select s from Student s left join s.classes c where c.code = :code "
			+ "and (:#{#student.lastName} is null OR s.lastName = :#{#student.lastName})"
			+ "and (:#{#student.firstName} is null OR s.firstName = :#{#student.firstName})")
	List<Student> findStudentsByParamsAndClassCode(@Param("code") Integer code, @Param("student") Student student);

}
