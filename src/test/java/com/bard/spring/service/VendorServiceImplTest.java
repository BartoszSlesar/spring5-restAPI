package com.bard.spring.service;

import com.bard.spring.api.v1.mapper.CustomerMapper;
import com.bard.spring.api.v1.mapper.VendorMapper;
import com.bard.spring.api.v1.model.CustomerDTO;
import com.bard.spring.api.v1.model.VendorDTO;
import com.bard.spring.domain.Customer;
import com.bard.spring.domain.Vendor;
import com.bard.spring.repositories.CustomerRepository;
import com.bard.spring.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.print.attribute.standard.MediaSize;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VendorServiceImplTest {

    public static final String NAME = "Some vendor";

    public static final long ID = 1L;


    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }

    @Test
    void getAllVendors() {

        //given
        Vendor vendor1 = new Vendor("Western Tasty Fruits Ltd.");
        Vendor vendor2 = new Vendor("Exotic Fruits Company");
        Vendor vendor3 = new Vendor("Home Fruits");
        List<Vendor> vendors = Arrays.asList(vendor1, vendor2,
                vendor3);

        when(vendorRepository.findAll()).thenReturn(vendors);
        List<VendorDTO> vendorDTOList = vendorService.getAllVendors();
        verify(vendorRepository, times(1)).findAll();
        assertEquals(vendors.size(), vendorDTOList.size());
    }

    @Test
    void getVendorById() {
        Vendor vendor = new Vendor(NAME);
        vendor.setId(ID);
        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));

        VendorDTO vendorDTO = vendorService.getVendorById(ID);
        verify(vendorRepository, times(1)).findById(anyLong());
        assertAll(
                () -> assertEquals(NAME, vendorDTO.getName()),
                () -> assertEquals(ID, vendorDTO.getId())
        );
    }

//    @Test
//    public void createNewCustomer() throws Exception {
//
//        //given
//        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setFirstName("Jim");
//
//        Customer savedCustomer = new Customer();
//        savedCustomer.setFirstName(customerDTO.getFirstName());
//        savedCustomer.setLastName(customerDTO.getLastName());
//        savedCustomer.setId(1l);
//
//        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
//
//        //when
//        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);
//
//        //then
//        assertEquals(customerDTO.getFirstName(), savedDto.getFirstName());
//        assertEquals("/api/v1/customer/1", savedDto.getCustomerUrl());
//    }
//
//    @Test
//    public void updateCustomerTest() throws Exception {
//
//        //given
//        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setFirstName("Jim");
//
//        Customer updatedCustomer = new Customer();
//        updatedCustomer.setFirstName(customerDTO.getFirstName());
//        updatedCustomer.setLastName(customerDTO.getLastName());
//        updatedCustomer.setId(1l);
//
//        when(customerRepository.save(any(Customer.class))).thenReturn(updatedCustomer);
//
//        //when
//        CustomerDTO updateCustomer = customerService.updateCustomer(1L, customerDTO);
//
//        //then
//        assertEquals(customerDTO.getFirstName(), updateCustomer.getFirstName());
//        assertEquals("/api/v1/customer/1", updateCustomer.getCustomerUrl());
//    }
//
//    @Test
//    public void deleteCustomerById() throws Exception {
//
//        Long id = 1L;
//
//        customerService.deleteCustomerById(id);
//
//        verify(customerRepository, times(1)).deleteById(anyLong());
//    }
}