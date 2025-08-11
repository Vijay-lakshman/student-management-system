package com.example.demo.controller;

import java.lang.foreign.Linker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.service.annotation.DeleteExchange;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

@RestController
public class StudentController {
	@Autowired
	StudentRepository repo;
	@PostMapping("/student")
  public void createStudent(@RequestBody Student  std) {
	  repo.save(std);
	  
  }
	@GetMapping("/student/{rollNo}")
	public Optional<Student> getStudent(@PathVariable Integer rollNo) {
		return repo.findById(rollNo);
	}
	@GetMapping("/student")
	public List<Student> getStudents(){
		return repo.findAll();
	}
	@PutMapping("/student/{rollNo}")
	public void updateStudent(@RequestBody Student std,@PathVariable Integer rollNo) {
		Student student=repo.findById(rollNo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
		student.setName(std.getName());
		student.setMailId(std.getMailId());
		student.setAddress(std.getAddress());
	    student.setPhoneNo(std.getPhoneNo());
	    student.setRollNo(std.getRollNo());
		repo.save(student);
	}
	@DeleteMapping("student/{rollNo}")
	public void removeStudent(@PathVariable Integer rollNo) {
		repo.deleteById(rollNo);
		
	}
}
