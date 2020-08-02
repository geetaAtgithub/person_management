package com.personmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personmanagement.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
		public List<Address> findByPersonId(long personId);
		public void deleteByPersonId(long personId);
}
