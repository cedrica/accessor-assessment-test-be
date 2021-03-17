package com.cedricleumaleu.accessorassessmenttest.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cedricleumaleu.accessorassessmenttest.data.Person;

public class CsvUtilsTest {
	
	//@Autowired
	private CsvUtils csvUtils;
	
	@BeforeEach
	public void init() {
		csvUtils = new CsvUtils();
	}
	
	@Test
	public void convertCsvToJavaObjects() {
		List<Person> persons = this.csvUtils.convertCsvToJavaObjects("/csv/sample-input.csv");
		assertEquals(9, persons.size());
		System.out.println(persons.get(0).getName());
	} 
	
	@Test
    public void writeToCsvFile() throws IOException {
		List<Person> persons = new ArrayList<Person>();
		Person person = new Person(9L, "Cedric", "Leumaleu", "90489 Nürnberg", 3);
		persons.add(person);
    }
}
