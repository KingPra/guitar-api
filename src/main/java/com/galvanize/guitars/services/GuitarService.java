package com.galvanize.guitars.services;

import com.galvanize.guitars.entities.Guitar;
import com.galvanize.guitars.repositories.GuitarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuitarService {

    @Autowired
    GuitarRepository guitarRepository;

    public List<Guitar> getAll() {
        return guitarRepository.findAll();

    }

    public Guitar getGuitarById(Long id) {
        return guitarRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        guitarRepository.deleteById(id);
    }

    public List<Guitar> findAll() {
        return guitarRepository.findAll();
    }

    public void save(Guitar guitar) {
        guitarRepository.save(guitar);
    }

    public void saveAll(List<Guitar> guitars) {
        guitarRepository.saveAll(guitars);
    }
}
