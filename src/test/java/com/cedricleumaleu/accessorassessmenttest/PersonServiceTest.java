package com.cedricleumaleu.accessorassessmenttest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cedricleumaleu.accessorassessmenttest.data.Person;
import com.cedricleumaleu.accessorassessmenttest.service.PersonService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceTest {
	@Autowired
	PersonService ps;
	Person p1;
	Person p2;
	Person p3;
	
	@BeforeEach
	public void init() {
		p1 = new Person(1L, "Max", "Musterman", "980334 Berlin Lagenstraste 2", 2);
		p2 = new Person(2L, "Lemi", "Emmanuel", "980334 Berlin Lagenstraste 2", 3);
		p3 = new Person(3L, "Emac", "Mauti", "980334 Berlin Lagenstraste 2", 1);
	}
	
	@Test
	public void findAll() {
		List<Person> persons = ps.loadPersons();
		assertEquals(3, persons.size());
	}
	
	@Test
	public void savePersonToH2() {
		ps.truncateTable();
		ps.savePerson(p1);
		ps.savePerson(p2);
		ps.savePerson(p3);
		List<Person> persons = ps.loadPersons();
		assertEquals(3, persons.size());
	}
	
	@Test
	public void findByColorFromH2() {
		Person person = ps.findByColorId(3);
		assert(person.getId() == 2);
	}
}



