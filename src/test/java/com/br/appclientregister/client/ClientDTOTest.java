package com.br.appclientregister.client;

import com.br.appclientregister.city.CityServiceTest;
import com.br.appclientregister.entities.Client;
import com.br.appclientregister.rest.dto.ClientDTO;
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
public class ClientDTOTest {

    private ClientServiceTest clientServiceTest;
    private CityServiceTest cityServicesTest;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        cityServicesTest = new CityServiceTest();
        clientServiceTest = new ClientServiceTest();
    }

    @Test
    public void mountClientDTOTest() {
        Client client = clientServiceTest.getClientObjectWithId1ToTest();
        client.setCity(cityServicesTest.getCityObjectWithId1ToTest());
        ClientDTO dto = new ClientDTO(client);
        assertTrue(dto.getClientId() == client.getId());
        assertTrue(dto.getAge() == client.getAge());
        assertTrue(dto.getClientName().equals(client.getName()));
        String genre = client.getGenre().toString().substring(0, 1).toUpperCase() + client.getGenre().toString().substring(1).toLowerCase();
        assertTrue(dto.getGenre().equals(genre));
        assertTrue(dto.getCityId() == client.getCity().getId());
        assertTrue(dto.getCity().equals(client.getCity().getName()));
    }

}
