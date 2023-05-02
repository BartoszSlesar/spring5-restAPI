package com.bard.spring.repositories;


import com.bard.spring.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
