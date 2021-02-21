package com.br.appclientregister.city;

import com.br.appclientregister.entities.City;
import com.br.appclientregister.rest.dto.CityDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
@TestComponent
public class CityDTOTest {

    private static CityServiceTest cityServicesTest;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        cityServicesTest = new CityServiceTest();
    }

    @Test
    public void mountCityDTOTest() {
        City city = cityServicesTest.getCityObjectWithId1ToTest();
        CityDTO cityDTO = new CityDTO(city);
        assertTrue(cityDTO.getCityName().equals(city.getName()));
        assertTrue(cityDTO.getState().equals(city.getState()));
        assertTrue(cityDTO.getCityId() == city.getId());
    }

}
