package com.br.appclientregister.services;

import com.br.appclientregister.entities.Client;
import com.br.appclientregister.rest.dto.ClientDTO;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<ClientDTO> findByNameIgnoreCase(String name);
    Optional<Client> findById(Integer id);
    ClientDTO findClientDTOById(Integer id) throws Exception;
    ClientDTO save(ClientDTO client) throws Exception;
    void delete(Client cliente);
    void updateClientName(Integer id, String name);
}
