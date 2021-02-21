package com.br.appclientregister.services;

import com.br.appclientregister.rest.dto.CityDTO;

import java.util.List;


public interface CityService {

    List<CityDTO> findAllByNameIgnoreCase(String Name);
    List<CityDTO> findAllByStateIgnoreCase(String state);
    CityDTO save(CityDTO cityDTO);

}
