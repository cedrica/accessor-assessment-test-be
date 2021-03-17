package com.cedricleumaleu.accessorassessmenttest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cedricleumaleu.accessorassessmenttest.data.Person;

@Service
public interface PersonService {
	public List<Person> loadPersons();
	public Person savePerson(Person person);
	public Person findByColorId(int colorId);
	public Person findById(Long id);
	public void truncateTable();
}
