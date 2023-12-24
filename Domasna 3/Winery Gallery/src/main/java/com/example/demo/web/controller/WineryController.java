package com.example.demo.web.controller;

import com.example.demo.model.User;
import com.example.demo.model.Wine;
import com.example.demo.model.Winery;
import com.example.demo.service.UserService;
import com.example.demo.service.WineryService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping({"/wineries"})

public class WineryController {

    private final WineryService wineryService;
    private final UserService userService;

    public WineryController(WineryService wineryService, UserService userService) {
        this.wineryService = wineryService;
        this.userService = userService;
    }

    @GetMapping()
    public String listWineries(Model model, Authentication authentication , HttpServletRequest request){
        List<Winery> wineries = wineryService.findAll();
        User user = (User) request.getSession().getAttribute("user");
        if (authentication != null && user == null) {
            String username = authentication.getName();
            user = userService.findByUsername(username);
            request.getSession().setAttribute("user", user);
        }
        model.addAttribute("wineries", wineries);
        return "view2";
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
// @GetMapping
//    public String allEvents(Model model, Authentication authentication, HttpServletRequest request) {
//        User user = (User) request.getSession().getAttribute("user");
//        if (authentication != null && user == null) {
//            String username = authentication.getName();
//            user = userService.findByUsername(username);
//            request.getSession().setAttribute("user", user);
//        }
//        model.addAttribute("searchText", "");
//        model.addAttribute("sortText", "all");
//        return "home";
//    }