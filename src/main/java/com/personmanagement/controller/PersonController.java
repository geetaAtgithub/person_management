package com.personmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.personmanagement.entity.Person;
import com.personmanagement.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	PersonService personService;

	@RequestMapping(value = "/getAllPersons", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Person> getAllPersons(Model model) {
		List<Person> personList = personService.findAllPersons();
		model.addAttribute("personList", personList);
		return personList;
	}

	@RequestMapping(value = "/getPerson/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public void getPersonById(@PathVariable int id) {
	}

	@RequestMapping(value = "/addPerson", method = RequestMethod.POST, headers = "Accept=application/json")
	public Person addPerson(@RequestBody Person person) {
		return personService.savePerson(person);
	}	
	
	@RequestMapping(value = "/updatePerson", method = RequestMethod.PUT, headers = "Accept=application/json")
	public Person updatePerson(@RequestBody Person person) {
		return personService.savePerson(person);
	}	

	@RequestMapping(value = "/deletePerson/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deletePerson(@PathVariable("id") long id) {
		personService.deletePerson(id);
	}	
}
