package com.br.appclientregister.services.impl;

import com.br.appclientregister.Exception.BusinessRuleException;
import com.br.appclientregister.entities.City;
import com.br.appclientregister.entities.Client;
import com.br.appclientregister.helpers.SystemMessages;
import com.br.appclientregister.repositories.CityRepository;
import com.br.appclientregister.repositories.ClientRepository;
import com.br.appclientregister.rest.dto.ClientDTO;
import com.br.appclientregister.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.br.appclientregister.helpers.SystemMessages.CITY_NOT_FOUND;
import static com.br.appclientregister.helpers.SystemMessages.CLIENT_NOT_FOUND;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<ClientDTO> findByNameIgnoreCase(String name) {
        List<Client> clients = clientRepository.findAllByNameIgnoreCase(name);

        List<ClientDTO> listClients = new ArrayList<>();
        clients.forEach(client -> listClients.add(new ClientDTO(client)));
        return listClients;
    }

    @Override
    public Optional<Client> findById(Integer id) {
        Optional<Client> client = clientRepository.findById(id);
        return client;
    }

    public ClientDTO findClientDTOById(Integer id) throws Exception {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()){
            Client client = clientOptional.get();
            return new ClientDTO(client);
        }
        throw new BusinessRuleException(CLIENT_NOT_FOUND);
    }

    @Override
    public ClientDTO save(ClientDTO clientDTO) throws Exception {
        if (clientDTO == null) {
            throw new BusinessRuleException(SystemMessages.PARAMETER_IS_NULL);
        }
        Client client = mountClientObjectToSave(clientDTO);
        Client savedClient = clientRepository.save(client);
        return new ClientDTO(savedClient);
    }

    private Client mountClientObjectToSave(ClientDTO clientDTO) throws Exception {
        Client client = new Client(clientDTO);
        Optional<City> city = cityRepository.findById(clientDTO.getCityId());
        if (city.isPresent()){
            client.setCity(city.get());
        } else {
            throw new BusinessRuleException(CITY_NOT_FOUND);
        }
        return client;
    }

    public void delete(Client client) {
        clientRepository.delete(client);
    }

    public void updateClientName(Integer id, String name) {
        clientRepository.findById(id).map( client -> {
                    client.setName(name);
                    return clientRepository.save(client);
                }).orElseThrow(() -> new BusinessRuleException(CLIENT_NOT_FOUND) );;
    }

}
