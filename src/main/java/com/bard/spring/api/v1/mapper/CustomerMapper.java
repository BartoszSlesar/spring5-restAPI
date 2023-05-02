package com.bard.spring.api.v1.mapper;


import com.bard.spring.api.v1.model.CustomerDTO;
import com.bard.spring.domain.Customer;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "customerUrl", ignore = true)
    CustomerDTO customerToCustomerDTO(Customer customer);

    @AfterMapping // or @BeforeMapping
    default void calculateTotal(Customer customer, @MappingTarget CustomerDTO customerDTO) {
        customerDTO.setCustomerUrl("/api/v1/customers/" + customer.getId());
    }

}
