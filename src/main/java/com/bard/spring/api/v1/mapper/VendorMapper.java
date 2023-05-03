package com.bard.spring.api.v1.mapper;


import com.bard.spring.api.v1.model.VendorDTO;
import com.bard.spring.domain.Vendor;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {
    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    @Mapping(target = "vendorUrl", ignore = true)
    VendorDTO vendorToVendorDto(Vendor vendor);

    Vendor vendorDtoToVendor(VendorDTO vendorDTO);

    @AfterMapping // or @BeforeMapping
    default void setVendorUrl(Vendor vendor, @MappingTarget VendorDTO vendorDTO) {
        vendorDTO.setVendorUrl("/api/v1/vendors/" + vendor.getId());
    }

}
