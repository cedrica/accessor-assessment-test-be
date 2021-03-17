package com.cedricleumaleu.accessorassessmenttest.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.cedricleumaleu.accessorassessmenttest.dao.PersonDAO;
import com.cedricleumaleu.accessorassessmenttest.data.Person;
import com.cedricleumaleu.accessorassessmenttest.exceptions.PersonNotFoundException;

@Service
@Transactional
@Primary
public class PersonH2Service implements PersonService {
	
	@Autowired
	private PersonDAO personDAO;
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Person> loadPersons() {
		return personDAO.findAll();
	}

	@Override
	public Person savePerson(Person person) {
		return this.personDAO.saveAndFlush(person);
	}

	@Override
	public Person findByColorId(int colorId) {
		Query query = em.createQuery("select p from Person p where p.colorId = " + colorId);
		List<Person> persons = query.getResultList();
		if(persons == null || persons.isEmpty()) throw new PersonNotFoundException("Person konnte leider nicht gefunden", null);
		return persons.get(0);
	}

	@Override
	public Person findById(Long id) {
		Optional<Person> personOptional = this.personDAO.findById(id);
		if(personOptional == null) throw new PersonNotFoundException("Person konnte leider nicht gefunden", null);
		return personOptional.get();
	}

	public void truncateTable() {
		this.personDAO.deleteAll();
	}
}
