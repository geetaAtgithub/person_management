package com.personmanagement.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personmanagement.entity.Address;
import com.personmanagement.entity.Person;
import com.personmanagement.repository.AddressRepository;
import com.personmanagement.repository.PersonRepository;

@Service
@Transactional
public class PersonServiceImpl implements PersonService{

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public Person savePerson(Person person) {
		 return personRepository.save(person);
	}

	@Override
	public Optional<Person> findByPersonId(long id) {
		return personRepository.findById(id);
	}

	@Override
	public void deletePerson(long id) {
		personRepository.deleteById(id);
		addressRepository.deleteByPersonId(id);
	}

	@Override
	public List<Person> findAllPersons() {
		 return personRepository.findAll();
	}

	@Override
	public Address saveAddress(Address addrses) {
		return addressRepository.save(addrses);
	}

	@Override
	public void deleteAddress(long id) {
		addressRepository.deleteById(id);		
	}

	@Override
	public List<Address> findAllAddresses(long personId) {
		return addressRepository.findByPersonId(personId);
	}

}
