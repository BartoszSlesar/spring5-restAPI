package com.bard.spring.api.v1.mapper;

import com.bard.spring.api.v1.model.CategoryDTO;
import com.bard.spring.api.v1.model.VendorDTO;
import com.bard.spring.domain.Category;
import com.bard.spring.domain.Vendor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VendorMapperTest {

    public static final String NAME = "Some Vendor";
    public static final long ID = 1L;

    VendorMapper vendorMapper = VendorMapper.INSTANCE;


    @Test
    public void vendorToVendorDTO() throws Exception {

        //given
        Vendor vendor = new Vendor();
        vendor.setName(NAME);
        vendor.setId(ID);

        //when
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDto(vendor);

        //then
        assertEquals(ID, vendorDTO.getId());
        assertEquals(NAME, vendorDTO.getName());
    }

}