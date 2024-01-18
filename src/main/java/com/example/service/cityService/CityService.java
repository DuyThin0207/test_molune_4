package com.example.service.cityService;

import com.example.model.City;
import com.example.service.GeneralService;

import java.util.Optional;
public interface CityService extends GeneralService<City> {
    Iterable<City> findAll();

    Optional<City> findById(Long id);
    void save(City city);

    void delete(City city);
}
