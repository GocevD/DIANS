package com.example.demo.model;


import lombok.*;

import javax.persistence.*;
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
    @Column(length = 2000)
    private String description;
    private String website;
    private String email;
    private String iframe;
    String logo;
    @OneToMany(mappedBy = "winery", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wine> wines;
    public Winery() {
        wines = new ArrayList<>();
    }

    public Winery(String name, String location, String description, String website, String email, String iframe,String logo)
    {
        this.name = name;
        this.location = location;
        this.description = description;
        this.website = website;
        this.email = email;
        this.wines = new ArrayList<>();
        this.iframe = iframe;
        this.logo = logo;
    }
}
