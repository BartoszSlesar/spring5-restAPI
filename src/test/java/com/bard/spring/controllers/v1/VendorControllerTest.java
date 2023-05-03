package com.bard.spring.controllers.v1;

import com.bard.spring.api.v1.model.VendorDTO;
import com.bard.spring.api.v1.model.VendorDTO;
import com.bard.spring.controllers.RestResponseEntityExceptionHandler;
import com.bard.spring.service.VendorService;
import com.bard.spring.service.ResourceNotFoundException;
import com.bard.spring.service.VendorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.bard.spring.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VendorControllerTest {

    public static final String NAME = "some company";
    public static final long ID = 1L;


    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    VendorDTO vendorDTO;

    VendorDTO returnVendor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
        vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        returnVendor = new VendorDTO();
        returnVendor.setId(ID);
        returnVendor.setName(NAME);
        returnVendor.setVendorUrl("/api/v1/vendors/1");
    }

    @AfterEach
    void destroy() {
        vendorDTO.setId(null);
    }

    @Test
    void getAllVendors() throws Exception {
        vendorDTO.setId(ID);

        VendorDTO vendorDTO1 = new VendorDTO();
        vendorDTO1.setId(2l);
        vendorDTO1.setName("Some second Vendor");

        List<VendorDTO> vendorDTOS = Arrays.asList(vendorDTO, vendorDTO1);

        when(vendorService.getAllVendors()).thenReturn(vendorDTOS);

        mockMvc.perform(get("/api/v1/vendors/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    public void testGetByIdVendor() throws Exception {
        vendorDTO.setId(ID);
        when(vendorService.getVendorById(anyLong())).thenReturn(vendorDTO);

        mockMvc.perform(get("/api/v1/vendors/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    @Test
    public void createNewVendor() throws Exception {

        when(vendorService.createNewVendor(vendorDTO)).thenReturn(returnVendor);
        //when/then
        mockMvc.perform(post("/api/v1/vendors/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendorDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo("/api/v1/vendors/1")));
    }

    @Test
    public void updateExistingVendor() throws Exception {
        //given

        when(vendorService.updateVendor(ID, vendorDTO)).thenReturn(returnVendor);
        //when/then
        mockMvc.perform(put("/api/v1/vendors/" + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)))
                .andExpect(jsonPath("$.vendor_url", equalTo("/api/v1/vendors/1")));
    }

    @Test
    public void testPatchVendor() throws Exception {

        //given
        VendorDTO vendorDTO1 = new VendorDTO();
        vendorDTO1.setName("Random vendor");

        VendorDTO returnDTO1 = new VendorDTO();
        returnDTO1.setName(vendorDTO1.getName());
        returnDTO1.setVendorUrl("/api/v1/vendors/1");

        when(vendorService.patchVendor(anyLong(), any(VendorDTO.class))).thenReturn(returnDTO1);

        mockMvc.perform(patch("/api/v1/vendors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendorDTO1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Random vendor")))
                .andExpect(jsonPath("$.vendor_url", equalTo("/api/v1/vendors/1")));
    }

    @Test
    public void testDeleteVendor() throws Exception {

        mockMvc.perform(delete("/api/v1/vendors/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(vendorService).deleteVendorById(anyLong());
    }

    @Test
    public void testNotFoundException() throws Exception {

        when(vendorService.getVendorById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get("/api/v1/vendors/22222")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }



}