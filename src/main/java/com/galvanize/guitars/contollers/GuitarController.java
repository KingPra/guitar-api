package com.galvanize.guitars.contollers;

import com.galvanize.guitars.entities.Guitar;
import com.galvanize.guitars.services.GuitarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guitars")
public class GuitarController {

    @Autowired
    GuitarService guitarService;

    @GetMapping("/")
    public List<Guitar> getAllGuitars(){
        return guitarService.findAll();
    }

    @GetMapping("/{id}")
    public Guitar getGuitarById(@PathVariable Long id) {
        return guitarService.getGuitarById(id);
    }

    @PostMapping("/")
    public Guitar addNewGuitar(@RequestBody Guitar guitar) {
        guitarService.save(guitar);
        return guitar;
    }

    @PutMapping("/{id}")
    public Guitar editGuitar(@PathVariable Long id, @RequestBody String brand) {
       Guitar oldGuitar = guitarService.getGuitarById(id);
       oldGuitar.setBrand(brand);
       guitarService.save(oldGuitar);
       return guitarService.getGuitarById(id);
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public Guitar editGuitarBrand(@PathVariable Long id, @RequestBody String brand) {
        guitarService.getGuitarById(id).setBrand(brand);
        //Guitar guitar = guitarService.getGuitarById(id);
        //guitar.setBrand(brand);
        //guitarService.save(guitar);
        //return guitar;
        return guitarService.getGuitarById(id);
    }

}
