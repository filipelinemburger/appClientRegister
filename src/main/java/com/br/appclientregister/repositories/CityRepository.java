package com.br.appclientregister.repositories;

import com.br.appclientregister.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    List<City> findAllByNameIgnoreCase(String name);
    List<City> findAllByStateIgnoreCase(String state);
}
