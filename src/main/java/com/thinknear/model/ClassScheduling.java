package com.thinknear.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Class")
public class ClassScheduling implements BaseModel{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer code;
	private String title;
	private String description;
	@ManyToMany(mappedBy = "classes")
	@JsonBackReference
	private List<Student> students = new ArrayList<>();

	public ClassScheduling() {

	}

	public ClassScheduling(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public void addStudent(Student student) {
		students.add(student);
		student.getClasses().add(this);
	}

	@Override
	@JsonBackReference
	public Integer getId() {
		return this.code;
	}

}
