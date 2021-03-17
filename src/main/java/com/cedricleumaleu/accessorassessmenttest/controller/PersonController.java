package com.cedricleumaleu.accessorassessmenttest.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cedricleumaleu.accessorassessmenttest.data.Person;
import com.cedricleumaleu.accessorassessmenttest.enums.ColorEnums;
import com.cedricleumaleu.accessorassessmenttest.service.PersonService;

@RestController
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@GetMapping("/persons")
	public List<Person> findAll(){
		return personService.loadPersons();
	}
	
	@GetMapping("/persons/{id}")
	public Person findOne(@PathVariable("id") Long id){
		return this.personService.findById(id);
	}
	
	@GetMapping("/persons/color/{color}")
	public Person findByColor(@PathVariable("color") String color){
		int colorId = -1;
		for (ColorEnums ce : ColorEnums.values()) {
			if(ce.getName().equals(color)) {
				colorId = ce.getNumber();
				break;
			}
		}
		return this.personService.findByColorId(colorId);
	}

	
	@PostMapping("/persons")
	public ResponseEntity<Object> createUser(@RequestBody Person person) {
		Person personSaved = personService.savePerson(person);
		if(personSaved == null)
			throw new RuntimeException("Person konnte nicht angelegt werden");
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(personSaved.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	

	
}
