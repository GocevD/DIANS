package com.example.demo.service.Impl;

import com.example.demo.model.Winery;
import com.example.demo.repository.WineryRepository;
import org.springframework.stereotype.Service;
import com.example.demo.service.WineryService;

import java.util.List;
import java.util.Optional;

@Service
public class WineryServiceImpl implements WineryService {

    private final WineryRepository wineryRepository;

    public WineryServiceImpl(WineryRepository wineryRepository) {
        this.wineryRepository = wineryRepository;
    }


    @Override
    public List<Winery> findAll() {
        return wineryRepository.findAll();
    }

    @Override
    public Optional<Winery> findById(Long id) {
        return wineryRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        wineryRepository.deleteById(id);
    }


    @Override
    public Winery save(String name, String location, String description, String website, String email, String iframe, String logo) {

        Winery w = new Winery(name, location, description, website, email, iframe,logo);
        return wineryRepository.save(w);
    }

    @Override
    public Winery save(Winery winery) {
        return wineryRepository.save(winery);
    }


}
