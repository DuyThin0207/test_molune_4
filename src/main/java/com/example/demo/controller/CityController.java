package com.example.demo.controller;

import com.example.model.City;
import com.example.model.Country;
import com.example.service.cityService.CityServiceImpl;
import com.example.service.countryService.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/city")
public class CityController {
    @Autowired
    public CityServiceImpl cityService;

    @Autowired
    public CountryService countryService;

    @GetMapping("/detail/{id}")
    public String detailCity(@PathVariable("id") Long id, Model model) {
        City city = cityService.findById(id).get();
        model.addAttribute("city", city);
        return "detail";
    }

    @GetMapping("/list")
    public String listCity(Model model) {
        Iterable<City> cities = cityService.findAll();
        model.addAttribute("cities", cities);
        return "list";
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        Iterable<Country> countries = countryService.findAll();
        model.addAttribute("countries", countries);
        model.addAttribute("city", new City());
        return "create";
    }

    @PostMapping("/create")
    public String createCity(@Validated @ModelAttribute("city") City city, BindingResult bindingResult, Model model, RedirectAttributes redirect) {
        Iterable<Country> countries = countryService.findAll();
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("countries", countries);
            return "create";
        }
        cityService.save(city);
        redirect.addFlashAttribute("message", "Thêm thành phố mới thành công!");
        return "redirect:/city/list";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable("id") Long id, Model model) {
        Iterable<Country> countries = countryService.findAll();
        City city = cityService.findById(id).get();
        model.addAttribute("countries", countries);
        model.addAttribute("city", city);
        return "edit";
    }

    @PostMapping("/edit")
    public String editCity(@ModelAttribute("city") City city, RedirectAttributes redirect) {
        cityService.save(city);
        redirect.addFlashAttribute("message", "Chỉnh sửa thành phố thành công!");
        return "redirect:/city/list";
    }

    @GetMapping("/delete/{id}")
    public String showDelete(@PathVariable("id") Long id, Model model) {
        City city = cityService.findById(id).get();
        model.addAttribute("city", city);
        return "delete";
    }

    @PostMapping("/delete")
    public String deleteCity(@ModelAttribute("city") City city, RedirectAttributes redirect) {
        cityService.delete(city);
        redirect.addFlashAttribute("message", "Đã xóa thành phố bạn vừa chọn!");
        return "redirect:/city/list";
    }
}
