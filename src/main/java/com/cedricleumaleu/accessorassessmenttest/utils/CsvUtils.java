package com.cedricleumaleu.accessorassessmenttest.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.cedricleumaleu.accessorassessmenttest.data.Person;

@Service
public class CsvUtils {

	public List<Person> convertCsvToJavaObjects(String csv) {
		InputStream resourceAsStream = CsvUtils.class.getResourceAsStream(csv);
		try (
			Reader reader = new BufferedReader(new InputStreamReader(resourceAsStream)); 
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
			) {

            List<Person> persons = new ArrayList<Person>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
    		Long counter = 0L;
            for (CSVRecord csvRecord : csvRecords) {
              Person person = new Person(
            		  counter++,
                    csvRecord.get(0),
                    csvRecord.get(1),
                    csvRecord.get(2),
            		Integer.valueOf(csvRecord.get(3))
                  );
              persons.add(person);
            }
			

            return persons;
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
		return null;
	}

	public Person savePersonToCsv(Person person) {
		
		return null;
	}
	

}
