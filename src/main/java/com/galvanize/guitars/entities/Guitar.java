package com.galvanize.guitars.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Guitar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long guitarId;

    private String brand;
    private String model;
    private int strings;

    public Guitar() {
    }

    public Guitar( String brand, String model, int strings) {
        this.brand = brand;
        this.model = model;
        this.strings = strings;
    }

    public Long getGuitarId() {
        return guitarId;
    }

    public void setGuitarId(Long guitarId) {
        this.guitarId = guitarId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getStrings() {
        return strings;
    }

    public void setStrings(int strings) {
        this.strings = strings;
    }
}
