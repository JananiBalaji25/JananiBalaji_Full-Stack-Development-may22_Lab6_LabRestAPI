package com.lab6.util;

import java.util.List;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.lab6.entity.Roles;
import com.lab6.entity.Student;
import com.lab6.entity.User;
import com.lab6.repository.StudentRepository;
import com.lab6.repository.UserRepository;



@Component
public class BootstrapAppdata {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@EventListener(ApplicationReadyEvent.class)
	public void insertStudents(ApplicationReadyEvent event) {
		Student student = new Student();
		student.setFirstName("Janani");
		student.setLastName("Balaji");
		student.setCourse("Python");
		student.setCountry("Singapore");
		
		Student student1 = new Student();
		student1.setFirstName("Vishal");
		student1.setLastName("ramani");
		student1.setCourse("Java");
		student1.setCountry("Singapore");
		
		Student student2 = new Student();
		student2.setFirstName("harini");
		student2.setLastName("ravi");
		student2.setCourse("Angular");
		student2.setCountry("India");
		
		this.studentRepository.save(student);
		this.studentRepository.save(student1);
		this.studentRepository.save(student2);
		
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void addUsers(ApplicationReadyEvent event) {
		
		User Janani = new User();
		Janani.setUsername("Janani");
		Janani.setPassword(this.passwordEncoder.encode("HelloJava"));
		
		User Vishal = new User();
		Vishal.setUsername("Vishal");
		Vishal.setPassword(this.passwordEncoder.encode("HelloJava"));
		
		Roles userRole = new Roles();
		userRole.setName("ROLE_USER");
		
		Roles adminRole = new Roles();
		adminRole.setName("ROLE_ADMIN");
		
		Roles JananiUserRole = new Roles();
		JananiUserRole.setName("ROLE_USER");
		
		List<Roles> VishalRoles = Vishal.getRoles();
		VishalRoles.add(userRole);
		
		List<Roles> JananiRoles = Janani.getRoles();
		JananiRoles.add(adminRole);
		JananiRoles.add(JananiUserRole);
		
		this.userRepository.save(Vishal);
		this.userRepository.save(Janani);
		
	}
	
}

