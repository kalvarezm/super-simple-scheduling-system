package com.thinknear;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.thinknear.model.ClassScheduling;
import com.thinknear.model.Student;
import com.thinknear.repository.ClassRepository;
import com.thinknear.repository.StudentRepository;

@Component
public class InitialData {

	@Bean
	public CommandLineRunner createClasses(ClassRepository classRepository, StudentRepository studentRepository) {
		return args -> {
			ClassScheduling class1 = new ClassScheduling("Machine learning", "Is a field of computer science");
			ClassScheduling class2 = new ClassScheduling("Project Management",
					"Is the practice of initiating, planning, excuting, controlling the work to achieve specific goals");

			class1 = classRepository.save(class1);
			class2 = classRepository.save(class2);

			Student student1 = new Student("Kevin", "Alvarez");
			student1.addClass(class1);
			student1.addClass(class2);
			Student student2 = new Student("John", "Alvarez");
			student2.addClass(class1);

			studentRepository.save(student1);
			studentRepository.save(student2);

		};
	}

}
