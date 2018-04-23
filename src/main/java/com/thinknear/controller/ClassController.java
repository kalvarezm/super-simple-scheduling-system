package com.thinknear.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thinknear.model.ClassScheduling;
import com.thinknear.model.Student;
import com.thinknear.service.ClassService;
import com.thinknear.service.ClassServiceImpl;

@RestController
@RequestMapping("classes")
public class ClassController extends CrudController<ClassServiceImpl, ClassScheduling> {

	@GetMapping
	public List<ClassScheduling> findAllByOptionalParams(@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "description", required = false) String description) {
		ClassScheduling classScheduling = new ClassScheduling(title, description);
		return getService().findClassesByParams(classScheduling);
	}

	@GetMapping("{id}/students")
	public List<Student> findAllStudentsByOptionalParamsAndById(@PathVariable(value = "id") Integer id,
			@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName) {
		Student student = new Student(firstName, lastName);
		return getService().findStudentsByParamsAndClassCode(id, student);
	}

	public ClassService getService() {
		return (ClassService) super.crudService;
	}

}
