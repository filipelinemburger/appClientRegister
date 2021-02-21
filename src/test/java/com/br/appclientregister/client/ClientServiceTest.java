package com.br.appclientregister.client;

import com.br.appclientregister.city.CityServiceTest;
import com.br.appclientregister.entities.Client;
import com.br.appclientregister.entities.enums.Genre;
import com.br.appclientregister.repositories.CityRepository;
import com.br.appclientregister.repositories.ClientRepository;
import com.br.appclientregister.rest.dto.ClientDTO;
import com.br.appclientregister.services.impl.ClientServiceImpl;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.anyObject;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class ClientServiceTest {

    public Integer ID_CLIENT = 1;
    public static String CLIENT_NAME = "Mario";
    LocalDate birthday = LocalDate.of(1990, 1, 1);

    public static String GENRE_MALE = "Male";

    @InjectMocks
    private static ClientServiceImpl clientService;
    @Mock
    private CityRepository cityRepository;
    @Mock
    private ClientRepository clientRepository;
    private CityServiceTest cityServicesTest;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        cityServicesTest = new CityServiceTest();
    }

    @Test
    public void saveANewClientTest() throws Exception {
        Client client = getClientObjectWithId1ToTest();
        client.setCity(cityServicesTest.getCityObjectWithId1ToTest());
        ClientDTO clientDTO = getClientDTOObjectWithId1ToTest();
        Mockito.when(clientRepository.save(anyObject())).thenReturn(client);
        Mockito.when(cityRepository.findById(clientDTO.getCityId())).thenReturn(Optional.of(cityServicesTest.getCityObjectWithId1ToTest()));
        ClientDTO clientSaved = clientService.save(clientDTO);
        assertTrue(clientSaved.getClientId().equals(clientDTO.getClientId()));
        assertTrue(clientSaved.getClientName().equals(clientDTO.getClientName()));
        assertTrue(clientSaved.getGenre().equals(clientDTO.getGenre()));
        assertTrue(clientSaved.getCityId() == clientDTO.getCityId());
        assertTrue(clientSaved.getCity().equals(client.getCity().getName()));
    }

    @Test
    public void findClientByNameTest() throws Exception {
        Client client = getClientObjectWithId1ToTest();
        client.setCity(cityServicesTest.getCityObjectWithId1ToTest());
        List<Client> listClients = new ArrayList<>();
        listClients.add(client);
        ClientDTO clientDTO = getClientDTOObjectWithId1ToTest();
        Mockito.when(clientRepository.findAllByNameIgnoreCase(CLIENT_NAME)).thenReturn(listClients);
        List<ClientDTO> clientDTOList = clientService.findByNameIgnoreCase(CLIENT_NAME);
        assertTrue(clientDTOList.get(0).getClientId() == client.getId());
        assertTrue(clientDTOList.get(0).getClientName().equals(client.getName()));
        assertTrue(clientDTOList.get(0).getGenre().equals(GENRE_MALE));
        assertTrue(clientDTOList.get(0).getCityId().equals(client.getCity().getId()));
        assertTrue(clientDTOList.get(0).getCity().equals(client.getCity().getName()));
    }

    @Test
    public void findClientByIdTest() throws Exception {
        Client client = getClientObjectWithId1ToTest();
        client.setCity(cityServicesTest.getCityObjectWithId1ToTest());
        ClientDTO clientDTO = getClientDTOObjectWithId1ToTest();
        Mockito.when(clientRepository.findById(ID_CLIENT)).thenReturn(Optional.ofNullable(client));
        ClientDTO clientSaved = clientService.findClientDTOById(ID_CLIENT);
        assertTrue(clientSaved.getClientId() == client.getId());
        assertTrue(clientSaved.getClientName().equals(client.getName()));
        assertTrue(clientSaved.getGenre().equals(GENRE_MALE));
        assertTrue(clientSaved.getCityId().equals(client.getCity().getId()));
        assertTrue(clientSaved.getCity().equals(client.getCity().getName()));
    }

    public Client getClientObjectWithId1ToTest() {
        Client client = new Client();
        client.setId(1);
        client.setBirthday(birthday);
        client.setGenre(Genre.MALE);
        client.setName(CLIENT_NAME);
        return client;
    }

    public ClientDTO getClientDTOObjectWithId1ToTest() {
        Client client = this.getClientObjectWithId1ToTest();
        client.setCity(cityServicesTest.getCityObjectWithId1ToTest());
        return new ClientDTO(client);
    }
}
