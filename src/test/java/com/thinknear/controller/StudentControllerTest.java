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

import com.thinknear.controller.StudentController;
import com.thinknear.model.ClassScheduling;
import com.thinknear.model.Student;
import com.thinknear.service.StudentServiceImpl;
import com.thinknear.service.exception.CustomNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

	@Mock
	private StudentServiceImpl service;
	private StudentController controller;
	private Student newStudent;
	private Student studentSaved;
	private ClassScheduling newClass;

	@Before
	public void setUp() throws Exception {
		controller = new StudentController();
		controller.setCrudService(service);
		setUpObject();
	}

	private void setUpObject() {
		newStudent = new Student("Kevin", "Alvarez");
		studentSaved = new Student("Kevin", "Alvarez");
		studentSaved.setStudentId(1);
		newClass = new ClassScheduling("Math", "Numbers");
	}

	@Test
	public void shouldReturn201IfStudentWasCreated() throws URISyntaxException {
		when(service.save(any(Student.class))).thenReturn(studentSaved);
		ResponseEntity<Student> response = controller.save(mock(Student.class));
		HttpStatus status = response.getStatusCode();
		assertThat(status, is(CREATED));
	}

	@Test
	public void shouldReturnTheStudentWhenStudentWasCreated() throws URISyntaxException {
		when(service.save(any(Student.class))).thenReturn(studentSaved);
		ResponseEntity<Student> response = controller.save(mock(Student.class));
		assertThat(response.getBody(), is(studentSaved));
	}

	@Test
	public void shouldReturn200WhenFindAll() {
		ResponseEntity<List<Student>> response = controller.findAllByOptionalParams("fakeName", "fakeLastName");
		assertThat(response.getStatusCode(), is(OK));
	}

	@Test
	public void shouldReturn204WhenDelete() {
		ResponseEntity<Student> response = controller.delete((1));
		assertThat(response.getStatusCode(), is(NO_CONTENT));
	}

	@Test
	public void shouldReturn200WhenUpdate() {
		ResponseEntity<Student> response = controller.update(1, newStudent);
		assertThat(response.getStatusCode(), is(OK));
	}

	@Test
	public void shouldReturnAListOfClassWhenFindAll() {
		List<Student> expectedList = new ArrayList<>();
		expectedList.add(newStudent);
		when(service.findStudentsByParams(any(Student.class))).thenReturn(expectedList);
		ResponseEntity<List<Student>> response = controller.findAllByOptionalParams("fakeName", "fakeLastName");
		assertThat(response.getBody(), is(expectedList));
	}

	@Test
	public void shouldReturn200WhenFindById() throws CustomNotFoundException {
		ResponseEntity<Student> response = controller.findById(1);
		assertThat(response.getStatusCode(), is(OK));
	}

	@Test
	public void shouldReturnAStudentWhenFindById() throws CustomNotFoundException {
		Student expectedClass = newStudent;
		when(service.findById(any(Integer.class))).thenReturn(expectedClass);
		ResponseEntity<Student> response = controller.findById(1);
		assertThat(response.getBody(), is(expectedClass));
	}

	@Test
	public void shouldReturnAListOfStudentsWhenFindAllStudentsByClass() {
		List<ClassScheduling> expectedList = new ArrayList<>();
		expectedList.add(newClass);
		when(service.findClassesByParamsAndStudentId((any(Integer.class)), any(ClassScheduling.class)))
				.thenReturn(expectedList);
		ResponseEntity<List<ClassScheduling>> response = controller.findAllClassesByOptionalParamsAndByClassCode(1,
				null, null);
		assertThat(response.getBody(), is(expectedList));
	}

}
