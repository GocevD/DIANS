package com.example.demo.service;

import com.example.demo.model.Winery;

import java.util.List;
import java.util.Optional;

public interface WineryService {

        List<Winery> findAll();

        Optional<Winery> findById(Long id);

        void deleteById(Long id);
    Winery save(String name, String location, String description, String website, String email, String iframe, String logo);

    Winery save(Winery winery);

}
