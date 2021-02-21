package com.br.appclientregister.services.impl;

import com.br.appclientregister.Exception.BusinessRuleException;
import com.br.appclientregister.entities.City;
import com.br.appclientregister.helpers.SystemMessages;
import com.br.appclientregister.repositories.CityRepository;
import com.br.appclientregister.rest.dto.CityDTO;
import com.br.appclientregister.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.br.appclientregister.helpers.SystemMessages.*;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<CityDTO> findAllByNameIgnoreCase(String name){
        List<City> cities = cityRepository.findAllByNameIgnoreCase(name);
        List<CityDTO> listCities = new ArrayList<>();
        cities.forEach(city -> listCities.add(checkIfNullAndReturnCityDTO(city)));
        return listCities;
    }

    public List<CityDTO> findAllByStateIgnoreCase(String state){
        List<City> cities = cityRepository.findAllByStateIgnoreCase(state);
        List<CityDTO> listCities = new ArrayList<>();
        cities.forEach(city -> listCities.add(checkIfNullAndReturnCityDTO(city)));
        return listCities;
    }

    public CityDTO save(CityDTO cityDTO){
        if (cityDTO == null){
            throw new BusinessRuleException(SystemMessages.PARAMETER_IS_NULL);
        }
        validateMandatoryFields(cityDTO);
        City city = new City(cityDTO);
        city = cityRepository.save(city);
        return checkIfNullAndReturnCityDTO(city);
    }

    private void validateMandatoryFields(CityDTO cityDTO) {
        if (cityNameIsNullOrBlank(cityDTO)){
            throw new BusinessRuleException(FIELD_CITY_NAME_IS_MANDATORY);
        }
        if (stateNameIsNullOrBlank(cityDTO)) {
            throw new BusinessRuleException(FIELD_STATE_IS_MANDATORY);
        }
    }

    private boolean stateNameIsNullOrBlank(CityDTO cityDTO) {
        return cityDTO.getState() == null || cityDTO.getState().isEmpty();
    }

    private boolean cityNameIsNullOrBlank(CityDTO cityDTO) {
        return cityDTO.getCityName() == null || cityDTO.getCityName().isEmpty();
    }

    private CityDTO checkIfNullAndReturnCityDTO(City city){
        if (city == null){
            throw new BusinessRuleException(CITY_NOT_FOUND);
        }
        return new CityDTO(city);
    }

}
