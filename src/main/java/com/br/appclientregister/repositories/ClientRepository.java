package com.br.appclientregister.repositories;

import com.br.appclientregister.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findAllByNameIgnoreCase(@Param("name") String state);
}
