package com.thinknear.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thinknear.model.ClassScheduling;
import com.thinknear.model.Student;

public interface StudentRepository extends BaseRepository<Student> {

	List<Student> findByClasses_Code(Integer code);
	
	@Query("select c from ClassScheduling c left join c.students s where s.studentId = :studentId "
			+ "and (:#{#class.title} is null OR c.title like %:#{#class.title}%)"
			+ "and (:#{#class.description} is null OR c.description like %:#{#class.description}%)")
	List<ClassScheduling> findClassesByParamsAndStudentId(@Param("studentId") Integer studentId, @Param("class") ClassScheduling classScheduling);

}
