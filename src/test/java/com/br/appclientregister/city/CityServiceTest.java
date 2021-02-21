package com.br.appclientregister.city;

import com.br.appclientregister.entities.City;
import com.br.appclientregister.repositories.CityRepository;
import com.br.appclientregister.rest.dto.CityDTO;
import com.br.appclientregister.services.impl.CityServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyObject;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class CityServiceTest {

    public static String CITY_PALHOCA = "Palhoça";
    public static String CITY_BLUMENAU = "Blumenau";
    public static String STATE = "SC";

    @InjectMocks
    private static CityServiceImpl cityService;
    @Mock
    private CityRepository cityRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    public City getCityObjectWithId1ToTest(){
        City city = new City();
        city.setId(1);
        city.setName(CITY_PALHOCA);
        city.setState(STATE);
        return city;
    }

    public CityDTO getCityDTOObjectWithoutIdToTest(){
        CityDTO cityDTO = new CityDTO();
        cityDTO.setCityName(CITY_PALHOCA);
        cityDTO.setState(STATE);
        return cityDTO;
    }

    @Test
    public void saveANewCityTest() {
        City city = getCityObjectWithId1ToTest();
        CityDTO cityDTO = getCityDTOObjectWithoutIdToTest();
        Mockito.when(cityRepository.save(anyObject())).thenReturn(city);
        CityDTO citySaved = cityService.save(cityDTO);
        assertTrue(citySaved.getCityName().equals(CITY_PALHOCA));
        assertTrue(citySaved.getState().equals(STATE));
    }

    @Test
    public void findCityByNameTest() {
        City city = getCityObjectWithId1ToTest();
        List<City> citiesMock = new ArrayList<>();
        citiesMock.add(city);
        Mockito.when(cityRepository.findAllByNameIgnoreCase(CITY_PALHOCA)).thenReturn(citiesMock);
        List<CityDTO> citySaved = cityService.findAllByNameIgnoreCase(CITY_PALHOCA);
        assertTrue(citySaved.get(0).getCityName().equals(CITY_PALHOCA));
        assertTrue(citySaved.get(0).getState().equals(STATE));
    }

    @Test
    public void findCityByStateTest() {
        City city = getCityObjectWithId1ToTest();
        List<City> citiesMock = new ArrayList<>();
        citiesMock.add(city);
        Mockito.when(cityRepository.findAllByStateIgnoreCase(STATE)).thenReturn(citiesMock);
        List<CityDTO> cities = cityService.findAllByStateIgnoreCase(STATE);
        assertTrue(cities.get(0).getCityName().equals(CITY_PALHOCA));
        assertTrue(cities.get(0).getState().equals(STATE));
    }

}
