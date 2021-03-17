package com.cedricleumaleu.accessorassessmenttest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cedricleumaleu.accessorassessmenttest.consts.ResourcesConst;
import com.cedricleumaleu.accessorassessmenttest.data.Data;
import com.cedricleumaleu.accessorassessmenttest.data.Person;
import com.cedricleumaleu.accessorassessmenttest.exceptions.PersonNotFoundException;
import com.cedricleumaleu.accessorassessmenttest.utils.FileUtils;

@Service
public class PersonCSVService implements PersonService {

	
	@Override
	public List<Person> loadPersons() {
		String csvString = FileUtils.readTxtFileToString(ResourcesConst.CSV_INPUT);
		List<String[]> personStringList = FileUtils.convertCsvStringIntoStringList(csvString);
		List<Person> persons = new ArrayList<Person>();
		Long counter = 0L;
		for(String[] personString : personStringList) {
			int colornum = Integer.parseInt(personString[3].trim());
			Person person = new Person(counter++, personString[0], personString[1], personString[2],
					colornum);
			persons.add(person);
		}
		return persons;
	}

	@Override
	public Person savePerson(Person person) {
		person.setId(Long.valueOf(Data.persons.size()));
		Data.persons.add(person);
        List<String> collect = Data.persons.stream().map(pers -> pers.toString()).collect(Collectors.toList());
        FileUtils.writeStringListToCsvFile(collect, ResourcesConst.CSV_INPUT);
        return person;
	}

	@Override
	public Person findByColorId(int colorId) {
		Predicate<Person> personPredicate = person -> person.getColorId() == colorId;
		List<Person> persons = Data.persons.stream().filter(personPredicate).collect(Collectors.toList());
		if (persons.isEmpty()) throw new PersonNotFoundException("No Person found for this color", null);
		return persons.get(0);
	}

	@Override
	public Person findById(Long id) {
		Predicate<Person> personPredicate = person -> person.getId().longValue() == id.longValue();
		List<Person> persons = Data.persons.stream().filter(personPredicate).collect(Collectors.toList());
		if (persons.isEmpty()) throw new PersonNotFoundException("Diese Person wurde nicht gefunden", null); 
		return persons.get(0);
	}

	public void truncateTable() {
		// TODO Auto-generated method stub
		
	}
}
