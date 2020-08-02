package com.personmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personmanagement.entity.Address;
import com.personmanagement.entity.Person;

@Service
public interface PersonService {
	
	public Person savePerson(Person person);
	public void deletePerson(long id);
	public List<Person> findAllPersons();
	public Optional<Person> findByPersonId(long id);
	
	public Address saveAddress(Address address);
	public void deleteAddress(long id);
	public List<Address> findAllAddresses(long personId);	

}
