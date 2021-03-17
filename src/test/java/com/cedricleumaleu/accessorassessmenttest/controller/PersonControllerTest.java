package com.cedricleumaleu.accessorassessmenttest.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cedricleumaleu.accessorassessmenttest.data.Person;
import com.cedricleumaleu.accessorassessmenttest.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PersonController.class)
public class PersonControllerTest {

	@Autowired 
	MockMvc mockMvc;
	
	@MockBean
	private PersonService personService;
	
	private List<Person> persons = new ArrayList<>();
	
	
	Person p1;
	Person p2;
	Person p3;
	
	@Before
	public void init() {
		p1 = new Person(1L, "Max", "Musterman", "980334 Berlin Lagenstraste 2", 2);
		p2 = new Person(2L, "Lemi", "Emmanuel", "980334 Berlin Lagenstraste 2", 3);
		p3 = new Person(3L, "Emac", "Mauti", "980334 Berlin Lagenstraste 2", 1);
		persons.add(p1);
	}
	
	
	@Test
	public void findAll() throws Exception{
		Mockito.when(personService.loadPersons()).thenReturn(persons);
		RequestBuilder rb = MockMvcRequestBuilders.get("/persons").accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(rb).andReturn();
		String expected = "[{'id':1, 'name':'Max', 'lastname':'Musterman', 'zipcodeCity':'980334 Berlin Lagenstraste 2','colorId':2}]";
		JSONAssert.assertEquals(expected, mvcResult.getResponse().getContentAsString(), true);
	}
	
	@Test
	public void findById() throws Exception{
		persons.add(p2);
		persons.add(p3);
		Mockito.when(personService.findById(Mockito.anyLong())).thenReturn(p2);
		RequestBuilder rb = MockMvcRequestBuilders.get("/persons/2").accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(rb).andReturn();
		String expected = "{'id':2, 'name':'Lemi', 'lastname':'Emmanuel', 'zipcodeCity':'980334 Berlin Lagenstraste 2','colorId':3}";
		JSONAssert.assertEquals(expected, mvcResult.getResponse().getContentAsString(), true);
	}
	
	
	@Test
	public void createPerson() throws Exception{
		Mockito.when(personService.savePerson(Mockito.any())).thenReturn(p3);
		RequestBuilder rb = MockMvcRequestBuilders
				.post("/persons")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(p3))
				.accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(rb).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		assertEquals("http://localhost/persons/3", response.getHeader(HttpHeaders.LOCATION));
	}
	
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
