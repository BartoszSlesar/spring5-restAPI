package com.bard.spring.api.v1.mapper;

import com.bard.spring.api.v1.model.CategoryDTO;
import com.bard.spring.api.v1.model.CustomerDTO;
import com.bard.spring.domain.Category;
import com.bard.spring.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    public static final String FIRST_NAME = "Joe";
    public static final String LAST_NAME = "Doe";
    public static final String CUSTOMER_URL = "/api/v1/customers/1";
    public static final long ID = 1L;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    void customerToCustomerDTO() {
        //given
        Customer customer = new Customer();
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
        customer.setId(ID);

        //when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        //then
        assertAll(
                () -> assertEquals(Long.valueOf(ID), customerDTO.getId()),
                () -> assertEquals(FIRST_NAME, customerDTO.getFirstName()),
                () -> assertEquals(LAST_NAME, customerDTO.getLastName()),
                () -> assertEquals(CUSTOMER_URL, customerDTO.getCustomerUrl())
        );


    }
}