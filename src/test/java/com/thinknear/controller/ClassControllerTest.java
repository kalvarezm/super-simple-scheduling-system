package com.thinknear.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.thinknear.controller.ClassController;
import com.thinknear.model.ClassScheduling;
import com.thinknear.model.Student;
import com.thinknear.service.ClassServiceImpl;
import com.thinknear.service.exception.CustomNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class ClassControllerTest {

	@Mock
	private ClassServiceImpl service;
	private ClassController controller;
	private ClassScheduling newClass;
	private ClassScheduling classSaved;
	private Student newStudent;

	@Before
	public void setUp() throws Exception {
		controller = new ClassController();
		controller.setCrudService(service);
		setUpObject();
	}

	private void setUpObject() {
		classSaved = new ClassScheduling();
		classSaved.setCode(1);
		classSaved.setTitle("Math");
		classSaved.setDescription("Numbers");
		newClass = new ClassScheduling();
		newClass.setTitle("Math");
		newClass.setDescription("Numbers");
		newStudent = new Student("Kevin", "Alvarez");
	}

	@Test
	public void shouldReturn201IfClassWasCreated() throws URISyntaxException {
		when(service.save(any(ClassScheduling.class))).thenReturn(classSaved);
		ResponseEntity<ClassScheduling> response = controller.save(mock(ClassScheduling.class));
		HttpStatus status = response.getStatusCode();
		assertThat(status, is(CREATED));
	}

	@Test
	public void shouldReturnTheClassWhenClassWasCreated() throws URISyntaxException {
		when(service.save(any(ClassScheduling.class))).thenReturn(classSaved);
		ResponseEntity<ClassScheduling> response = controller.save(mock(ClassScheduling.class));
		assertThat(response.getBody(), is(classSaved));
	}
	
	@Test
	public void shouldReturn200WhenFindById() throws CustomNotFoundException {
		ResponseEntity<ClassScheduling> response = controller.findById(1);
		assertThat(response.getStatusCode(), is(OK));
	}
	
	@Test
	public void shouldReturnAClassWhenFindById() throws CustomNotFoundException {
		ClassScheduling expectedClass = newClass;
		when(service.findById(any(Integer.class))).thenReturn(expectedClass);
		ResponseEntity<ClassScheduling> response = controller.findById(1);
		assertThat(response.getBody(), is(expectedClass));
	}

	@Test
	public void shouldReturn200WhenFindAll() {
		ResponseEntity<List<ClassScheduling>> response = controller.findAllByOptionalParams("fakeTitle",
				"fakeDescription");
		assertThat(response.getStatusCode(), is(OK));
	}
	
	@Test
	public void shouldReturn204WhenDelete()  {
		ResponseEntity<ClassScheduling> response = controller.delete((1));
		assertThat(response.getStatusCode(), is(NO_CONTENT));
	}
	
	@Test
	public void shouldReturn200WhenUpdate()  {
		ResponseEntity<ClassScheduling> response = controller.update(1, newClass);
		assertThat(response.getStatusCode(), is(OK));
	}

	@Test
	public void shouldReturnAListOfClassWhenFindAll() {
		List<ClassScheduling> expectedClasses = new ArrayList<>();
		expectedClasses.add(newClass);
		when(service.findClassesByParams(any(ClassScheduling.class))).thenReturn(expectedClasses);
		ResponseEntity<List<ClassScheduling>> response = controller.findAllByOptionalParams("title", "description");
		assertThat(response.getBody(), is(expectedClasses));
	}

	@Test
	public void shouldReturnAListOfStudentsWhenFindAllStudentsByClass() {
		List<Student> expectedStudent = new ArrayList<>();
		expectedStudent.add(newStudent);
		when(service.findStudentsByParamsAndClassCode(any(Integer.class), any(Student.class)))
				.thenReturn(expectedStudent);
		ResponseEntity<List<Student>> response = controller.findAllStudentsByOptionalParamsAndById(1, null, null);
		assertThat(response.getBody(), is(expectedStudent));
	}

}
