package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "winery")
@Getter
@Setter
public class Winery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private String description;
    private String website;
    private String email;
    @OneToMany(mappedBy = "winery", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wine> wines;
    public Winery() {
        wines = new ArrayList<>();
    }

    public Winery(String name, String location, String description, String website, String email) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.website = website;
        this.email = email;
        this.wines = new ArrayList<>();
    }
}
