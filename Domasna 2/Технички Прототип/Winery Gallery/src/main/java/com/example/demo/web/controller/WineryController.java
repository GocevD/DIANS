package com.example.demo.web.controller;

import com.example.demo.model.Wine;
import com.example.demo.model.Winery;
import com.example.demo.service.WineryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/wineries")
public class WineryController {

    private final WineryService wineryService;

    public WineryController(WineryService wineryService) {
        this.wineryService = wineryService;
    }

    @GetMapping
    public String listWineries(Model model) {
        List<Winery> wineries = wineryService.findAll();
        model.addAttribute("wineries", wineries);
        return "list";
    }

    @GetMapping("/{id}")
    public String viewWinery(@PathVariable Long id, Model model) {
        Optional<Winery> winery = wineryService.findById(id);
        if (winery.isPresent()) {
            List<Wine> wines = winery.get().getWines();
            model.addAttribute("winery", winery.get());
            model.addAttribute("wines", wines);
            return "view";
        } else {
            return "redirect:/wineries";
        }
    }

    @GetMapping("/add")
    public String showAddWineryForm(Model model) {
        model.addAttribute("winery", new Winery());
        return "add";
    }

    @PostMapping("/add")
    public String addWinery(@ModelAttribute Winery winery) {
        wineryService.save(winery);
        return "redirect:/wineries";
    }

    @GetMapping("/{id}/delete")
    public String deleteWinery(@PathVariable Long id) {
        wineryService.deleteById(id);
        return "redirect:/wineries";
    }
}
