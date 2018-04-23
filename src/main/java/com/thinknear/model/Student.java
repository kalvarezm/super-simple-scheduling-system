package com.thinknear.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Student")
public class Student implements BaseModel{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer studentId;
	private String lastName;
	private String firstName;
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "class_student", joinColumns = @JoinColumn(name = "code"), inverseJoinColumns = @JoinColumn(name = "studentId"))
	@JsonBackReference
	private List<ClassScheduling> classes = new ArrayList<>();

	public Student() {

	}

	public Student(String firstName, String lastName) {
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public List<ClassScheduling> getClasses() {
		return classes;
	}

	public void setClasses(List<ClassScheduling> classes) {
		this.classes = classes;
	}

	public void addClass(ClassScheduling classScheduling) {
		classes.add(classScheduling);
		classScheduling.getStudents().add(this);
	}

	@Override
	@JsonBackReference
	public Integer getId() {
		return studentId;
	}

}
