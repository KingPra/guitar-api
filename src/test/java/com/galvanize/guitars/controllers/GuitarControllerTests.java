package com.galvanize.guitars.controllers;

import com.galvanize.guitars.entities.Guitar;
import com.galvanize.guitars.repositories.GuitarRepository;
import com.galvanize.guitars.services.GuitarService;
import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class GuitarControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    GuitarRepository guitarRepository;

    @Autowired
    GuitarService guitarService;

    private String BASE_URI = "/";

    private List<Guitar> guitars = new ArrayList<>();

    @Before
    public void setUp() throws Exception{
        for(int i = 0; i < 10; i++) {
            guitars.add(new Guitar("Brand" + i, "Model" + i, 6));
        }
        guitarRepository.saveAll(guitars);

    }

    @Test
    public void getAllGuitars() throws Exception {
        //setup

        mockMvc.perform(get("/guitars/"))
        .andExpect(status().isOk());
    }

    @Test
    public void getGuitarById() throws Exception {
        //setup
        Guitar guitar = new Guitar("brand1", "model1", 6);
        guitar.setGuitarId(1L);

        mockMvc.perform(get("/guitars/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void addNewGuitar() throws Exception {

        mockMvc.perform(post("/guitars/")
        .contentType(MediaType.APPLICATION_JSON)
                .content("{\"brand\" : \"BRAND\", \"make\" : \"MAKE\", \"strings\" : 6}"))
                .andExpect(status().isOk());
              //  .andExpect(jsonPath("guitarId").exists());
    }

    @Test
    public void editGuitarBrandById() throws Exception {
        Guitar guitar = new Guitar("Guild", "model1", 6);
        guitarService.save(guitar);
        Long id= guitar.getGuitarId();
        mockMvc.perform(patch("/guitars/" + id +"?brand=Fender")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"brand\" : \"Fender\", \"make\" : \"model1\", \"strings\" : 6}"))
                .andExpect(status().isOk());
    }

    @Test
    public void editGuitarModelById() throws Exception {
        Guitar guitar = new Guitar("Fender", "model1", 6);
        guitarService.save(guitar);
        Long id= guitar.getGuitarId();
        String make = "Stratocaster";
        mockMvc.perform(patch("/guitars/" + id +"?make=" + make)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"brand\" : \"Fender\", \"make\" : \"" + make +" \", \"strings\" : 6}"))
                .andExpect(status().isOk());
    }

    @Test
    public void editGuitarStringsById() throws Exception{
        Guitar guitar = new Guitar("Fender", "model1", 6);
        guitarService.save(guitar);
        Long id= guitar.getGuitarId();
        String make = "model1";
        int strings = 12;
        mockMvc.perform(patch("/guitars/" + id +"?strings=" + strings)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"brand\" : \"Fender\", \"make\" : \"" + make +" \", \"strings\" : " + strings + "}"))
                .andExpect(status().isOk());
    }
}
