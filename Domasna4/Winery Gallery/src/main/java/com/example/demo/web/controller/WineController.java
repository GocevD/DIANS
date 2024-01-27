package com.example.demo.web.controller;

import com.example.demo.model.Wine;

import com.example.demo.service.WineService;
import com.example.demo.service.WineryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/wines")
public class WineController {
    private final WineService wineService;
    private final WineryService wineryService;

    public WineController(WineService wineService, WineryService wineryService) {
        this.wineService = wineService;
        this.wineryService = wineryService;
    }

    @GetMapping
    public String listWines(Model model) {
        List<Wine> wines = wineService.findAll();
        model.addAttribute("wines", wines);
        return "wine/list";
    }

    @GetMapping("/add")
    public String showAddWineForm(Model model) {
        model.addAttribute("wine", new Wine());
        model.addAttribute("wineries", wineryService.findAll());
        return "wine/add";
    }
    @PostMapping("/add")
    public String addWine(@ModelAttribute Wine wine) {
        wineService.save(wine);
        return "redirect:/wines";
    }
    @PostMapping("/{id}/edit")
    public String editWine( @PathVariable Long id, @ModelAttribute Wine wine) {
        wineService.save(wine);
        return "redirect:/wines";
    }

    @GetMapping("/{id}/edit")
    public String editWineForm(@PathVariable Long id, Model model) {
        Optional<Wine> wineOptional = wineService.findById(id);
        if (wineOptional.isPresent()) {
            Wine wine = wineOptional.get();
            model.addAttribute("wine", wine);
            model.addAttribute("wineries", wineryService.findAll());  // Add wineries for the select dropdown
            return "wine/edit";
        } else {
            return "redirect:/wines";
        }
    }


    @GetMapping("/{id}/delete")
    public String deleteWine(@PathVariable Long id) {
        wineService.deleteById(id);
        return "redirect:/wines";
    }
}
