package com.galvanize.guitars.services;

import com.galvanize.guitars.entities.Guitar;
import com.galvanize.guitars.repositories.GuitarRepository;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
//@Transactional
public class GuitarServiceTests {

    @Autowired
    GuitarRepository guitarRepository;

    @Autowired
    GuitarService guitarService;

    MockMvc mockMvc;
//current issue: data is saved to database, need to find out how to clear data
    // after each test, or mock database


    @Test
    public void getAllGuitars() {
        //setup
        List<Guitar> guitars = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            //guitars.add(new Guitar("Brand" + i, "Model" + i, 6));
            Guitar guitar = new Guitar("Brand" + i, "Model" + i, 6);
        }

        //guitarRepository.saveAll(guitars);

        //execution
        List<Guitar> svcGuitars = guitarService.getAll();

        //assertion
        for(Guitar guitar: svcGuitars) {
            assertNotNull(guitar.getGuitarId());
        }

        for(Guitar guitar : svcGuitars) {
            Long id = guitar.getGuitarId();
            guitarService.deleteById(id);
        }
    }

    @Test
    public void getOneGuitar() {
        //setup
        Guitar guitar = new Guitar("Guild", "D4581d", 6);
        guitarRepository.save(guitar);

        //execution
        Guitar result = guitarService.getGuitarById(guitar.getGuitarId());

        // assertion
        assertNotNull(result);
    }

    @Test
    public void deleteOneGuitar() {
        //setup
        Guitar guitar = new Guitar("Guild", "D4581d", 6);
        guitarRepository.save(guitar);
        guitarService.deleteById(guitar.getGuitarId());
        //execution
        int result = guitarService.findAll().size();
        //assertion
        assertEquals(0, result);
    }

    @Test
    public void saveGuitarInServiceLayer() {
        //setup
        Guitar guitar = new Guitar("Guild", "D4581d", 6);
        guitarService.save(guitar);
        //execution
        String result = guitarService.getGuitarById(guitar.getGuitarId()).getBrand();
        //assertion
        assertEquals("Guild", result);
    }

}
