package com.br.appclientregister.rest.controllers;

import com.br.appclientregister.rest.dto.CityDTO;
import com.br.appclientregister.services.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Validated
@Api(value = "City API")
@RestController
@RequestMapping("/api/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping(value = "/find-by-name/{name}")
    @ApiOperation(value = "Find a city by it's name")
    public List<CityDTO> findCityByName(@PathVariable String name) {
        return cityService.findAllByNameIgnoreCase(name);
    }

    @GetMapping(value = "/find-by-state/{state}")
    @ApiOperation(value = "Find a city by it's state")
    public List<CityDTO> findCityByState(@PathVariable String state) {
        return cityService.findAllByStateIgnoreCase(state);
    }

    @PostMapping
    @ResponseStatus(code = CREATED)
    @ApiOperation(value = "Save a new city in the database")
    public CityDTO saveNewCity(@RequestBody CityDTO cityDTO) {
        return cityService.save(cityDTO);
    }

}
