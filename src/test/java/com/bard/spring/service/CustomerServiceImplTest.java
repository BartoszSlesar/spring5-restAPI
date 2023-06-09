package com.bard.spring.service;

import com.bard.spring.api.v1.mapper.CategoryMapper;
import com.bard.spring.api.v1.mapper.CustomerMapper;
import com.bard.spring.api.v1.model.CategoryDTO;
import com.bard.spring.api.v1.model.CustomerDTO;
import com.bard.spring.domain.Category;
import com.bard.spring.domain.Customer;
import com.bard.spring.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    public static final String FIRST_NAME = "Joe";
    public static final String LAST_NAME = "Doe";
    public static final long ID = 1L;


    @Mock
    CustomerRepository customerRepository;

    CustomerService customerService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    void getAllCustomers() {

        //given
        Customer freddy = new Customer("Freddy", "Newman");
        Customer michael = new Customer("Michael", "Lachappele");
        Customer anne = new Customer("Anne", "Hine");
        List<Customer> customers = Arrays.asList(freddy, michael, anne);

        when(customerRepository.findAll()).thenReturn(customers);
        List<CustomerDTO> customerDTOList = customerService.getAllCustomers();
        verify(customerRepository, times(1)).findAll();
        assertEquals(customers.size(), customerDTOList.size());
    }

    @Test
    void getCustomerById() {
        Customer customer = new Customer(FIRST_NAME, LAST_NAME);
        customer.setId(ID);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        CustomerDTO customerDTO = customerService.getCustomerById(ID);
        verify(customerRepository, times(1)).findById(anyLong());
        assertAll(
                () -> assertEquals(FIRST_NAME, customerDTO.getFirstName()),
                () -> assertEquals(LAST_NAME, customerDTO.getLastName()),
                () -> assertEquals(ID, customerDTO.getId())
        );
    }

    @Test
    public void createNewCustomer() throws Exception {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Jim");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName(customerDTO.getLastName());
        savedCustomer.setId(1l);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

        //then
        assertEquals(customerDTO.getFirstName(), savedDto.getFirstName());
        assertEquals("/api/v1/customer/1", savedDto.getCustomerUrl());
    }

    @Test
    public void updateCustomerTest() throws Exception {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Jim");

        Customer updatedCustomer = new Customer();
        updatedCustomer.setFirstName(customerDTO.getFirstName());
        updatedCustomer.setLastName(customerDTO.getLastName());
        updatedCustomer.setId(1l);

        when(customerRepository.save(any(Customer.class))).thenReturn(updatedCustomer);

        //when
        CustomerDTO updateCustomer = customerService.updateCustomer(1L, customerDTO);

        //then
        assertEquals(customerDTO.getFirstName(), updateCustomer.getFirstName());
        assertEquals("/api/v1/customer/1", updateCustomer.getCustomerUrl());
    }

    @Test
    public void deleteCustomerById() throws Exception {

        Long id = 1L;

        customerService.deleteCustomerById(id);

        verify(customerRepository, times(1)).deleteById(anyLong());
    }
}