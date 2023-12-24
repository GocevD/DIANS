package com.example.demo.model;


import lombok.*;

import javax.persistence.*;


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
    @Column(length = 1000)
    private String description;
    private String name;
    @Column(length = 1000)
    private String picture;
    @ManyToOne
    @JoinColumn(name = "winery_id")
    private Winery winery;
    public Wine(String name, Integer year, String description, Winery winery, String picture) {
        this.name = name;
        this.year = year;
        this.description = description;
        this.winery = winery;
        this.picture = picture;
    }

}
