package com.personmanagement;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.personmanagement.entity.Address;
import com.personmanagement.entity.Person;
import com.personmanagement.repository.AddressRepository;
import com.personmanagement.repository.PersonRepository;
import com.personmanagement.service.PersonService;


@RunWith(SpringRunner.class)
@SpringBootTest
class PersonManagementApplicationTests {

	@Autowired
	private PersonService service;
	
	@MockBean
	private PersonRepository personRepository;
	
	@MockBean
	private AddressRepository addressRepository;
	
	@Test
	public void savePersonTest() {
		Person person = new Person(1,"Mitesh","Sharma");
		when(personRepository.save(person)).thenReturn(person);
		assertEquals(person, service.savePerson(person));
	}
	
	@Test
	public void deletePersonTest() {
		Person person = new Person(1,"Mitesh","Sharma");
		service.deletePerson(person.getId());
		verify(personRepository, times(1)).deleteById(person.getId());
		verify(addressRepository, times(1)).deleteByPersonId(person.getId());
	}
	
	@Test
	public void findAllPersonTest() {
		List<Person> personList = new ArrayList<Person>() { { add(new Person(1,"Mitesh","Sharma")); add(new Person(2,"Raj","Sharma")); } }; 
		when(personRepository.findAll()).thenReturn(personList);
		assertEquals(personList.size(), service.findAllPersons().size());
	}
	
	@Test
	public void saveAddressTest() {
		Address address=new Address(1,1,"G.K Road","Hyderabad","Telangana","500340");
		when(addressRepository.save(address)).thenReturn(address);
		assertEquals(address, service.saveAddress(address));
	}
	
	@Test
	public void deleteAddressTest() {
		Address address=new Address(1,1,"G.K Road","Hyderabad","Telangana","500340");
		service.deleteAddress(address.getId());
		verify(addressRepository, times(1)).deleteById(address.getId());
	}

	@Test
	public void findAllAddressesTest() {
		List<Address> addressList = new ArrayList<Address>() { { add(new Address(1,1,"G.K Road","Hyderabad","Telangana","500340")); add(new Address(2,1,"Banjara Hills","Hyderabad","Telangana","500550")); } }; 
		when(addressRepository.findByPersonId(1)).thenReturn(addressList);
		assertEquals(addressList.size(), service.findAllAddresses(1).size());
	}	

}


