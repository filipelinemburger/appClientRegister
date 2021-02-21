package com.br.appclientregister.rest.dto;

import com.br.appclientregister.entities.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {

    private Integer cityId;
    private String cityName;
    private String state;

    public CityDTO(City city){
        this.cityId = city.getId();
        this.cityName= city.getName();
        this.state = city.getState();
    }

}
