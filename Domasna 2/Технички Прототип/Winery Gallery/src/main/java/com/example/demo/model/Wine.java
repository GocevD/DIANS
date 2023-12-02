package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;



@Table(name = "wine")
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Wine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer year;
    private String description;
    private String name;
    @ManyToOne
    @JoinColumn(name = "winery_id")
    private Winery winery;
    public Wine(String name, Integer year, String description, Winery winery) {
        this.name = name;
        this.year = year;
        this.description = description;
        this.winery = winery;
    }


}
