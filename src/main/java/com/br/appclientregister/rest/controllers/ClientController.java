package com.br.appclientregister.rest.controllers;

import com.br.appclientregister.rest.dto.ClientDTO;
import com.br.appclientregister.services.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Validated
@Api("Client API")
@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/find-by-name/{name}")
    @ResponseStatus(code = OK)
    @ApiOperation(value = "Find a client by his name")
    public List<ClientDTO> findClientByName(@PathVariable String name) {
        return clientService.findByNameIgnoreCase(name);
    }

    @GetMapping(value = "/find-by-id/{id}")
    @ResponseStatus(code = OK)
    @ApiOperation(value = "Find a client by his ID")
    public ClientDTO findClientById(@PathVariable Integer id) throws Exception {
        return clientService.findClientDTOById(id);
    }

    @PostMapping
    @ResponseStatus(code = CREATED)
    @ApiOperation(value = "Save a new client in the database")
    public ClientDTO saveClient(@RequestBody ClientDTO clientDTO) throws Exception {
        return clientService.save(clientDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = OK)
    @ApiOperation(value = "Update the name of a client by his ID")
    public void updateClientName(@PathVariable Integer id, @RequestBody String name){
        clientService.updateClientName(id, name);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = OK)
    @ApiOperation(value = "Delete a client by ID")
    public void deleteById(@PathVariable Integer id) {
        clientService.findById(id).map(client -> {
            clientService.delete(client);
            return client;
        }).orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, "Error on delete client"));
    }


}