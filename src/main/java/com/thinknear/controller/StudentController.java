package com.thinknear.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thinknear.model.ClassScheduling;
import com.thinknear.model.Student;
import com.thinknear.service.StudentService;
import com.thinknear.service.StudentServiceImpl;

@RestController
@RequestMapping("students")
public class StudentController extends CrudController<StudentServiceImpl, Student> {

	@GetMapping
	public List<Student> findAllByOptionalParams(@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName) {
		Student student = new Student(firstName, lastName);
		return getStudentService().findStudentsByParams(student);
	}

	@GetMapping("{id}/classes")
	public List<ClassScheduling> findAllStudentsByOptionalParamsAndByClassCode(@PathVariable(value = "id") Integer id,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "description", required = false) String description) {
		ClassScheduling classScheduling = new ClassScheduling(title, description);
		return getStudentService().findClassesByParamsAndStudentId(id, classScheduling);
	}

	public StudentService getStudentService() {
		return (StudentService) crudService;
	}

}
