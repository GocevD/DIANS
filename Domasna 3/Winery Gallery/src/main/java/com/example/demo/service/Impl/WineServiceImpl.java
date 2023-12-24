package com.example.demo.service.Impl;

import com.example.demo.model.Wine;
import com.example.demo.model.Winery;
import com.example.demo.repository.WineRepository;
import com.example.demo.repository.WineryRepository;
import com.example.demo.service.WineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WineServiceImpl implements WineService {

    private final WineRepository wineRepository;
    private final WineryRepository wineryRepository;

    public WineServiceImpl(WineRepository wineService, WineryRepository wineryRepository) {
        this.wineRepository = wineService;
        this.wineryRepository = wineryRepository;
    }


    @Override
    public List<Wine> findAll() {
        return wineRepository.findAll();
    }

    @Override
    public Optional<Wine> findById(Long id) {
        return wineRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        wineRepository.deleteById(id);
    }

    @Override
    public Wine save(String name, Integer year, String description, Long wineryId, String picture) {
        Wine w = new Wine(name, year, description, wineryRepository.findById(wineryId).get(),picture);
        return wineRepository.save(w);
    }

    @Override
    public Optional<Wine> edit(Long id, String name, Integer year, String description, Long wineryId,String picture) {
        Wine w = wineRepository.findById(id).get();
        w.setName(name);
        w.setYear(year);
        w.setDescription(description);
        if(w.getWinery()!=wineryRepository.findById(wineryId).get())
            w.setWinery(wineryRepository.findById(wineryId).get());
        w.setPicture(picture);
        return Optional.of(wineRepository.save(w));
    }

    @Override
    public void addWineToWinery(Wine wine, Winery winery) {
        wine.setWinery(winery);
        wineRepository.save(wine);

    }

    @Override
    public Wine save(Wine wine) {
        return wineRepository.save(wine);
    }
}
