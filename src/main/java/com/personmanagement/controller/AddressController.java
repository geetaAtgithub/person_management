package com.personmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.personmanagement.entity.Address;
import com.personmanagement.entity.Person;
import com.personmanagement.service.PersonService;

@RestController
public class AddressController {
	@Autowired
	PersonService personService;

	@RequestMapping(value = "/getAllAddresses/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Address> getAllAddresses(@PathVariable("id") long personId,Model model) {
		List<Address> addressList = personService.findAllAddresses(personId);
		model.addAttribute("addressList", addressList);
		return addressList;
	}
	
	@RequestMapping(value = "/addAddress", method = RequestMethod.POST, headers = "Accept=application/json")
	public Address addAddress(@RequestBody Address address) {
		return personService.saveAddress(address);

	}	
	
	@RequestMapping(value = "/updateAddress", method = RequestMethod.PUT, headers = "Accept=application/json")
	public Address updateAddress(@RequestBody Address address) {
		return personService.saveAddress(address);

	}	

	@RequestMapping(value = "/deleteAddress/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteAddress(@PathVariable("id") long id) {
		personService.deleteAddress(id);
	}	
}
