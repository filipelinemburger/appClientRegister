package com.br.appclientregister.rest.dto;

import com.br.appclientregister.entities.Client;
import com.br.appclientregister.entities.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    private Integer clientId;
    private String clientName;
    private String birthdayDate;
    private String genre;
    private int age;
    private Integer cityId;
    private String city;

    public ClientDTO(Client client){
        this.setClientId(client.getId());
        this.setClientName(client.getName());
        this.setAge(client.getAge());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        this.setBirthdayDate(client.getBirthday().format(formatter));
        this.setCityId(client.getCity().getId());
        this.setCity(client.getCity().getName());
        this.setGenre(client.getGenre() == Genre.MALE ? "Male": "Female");
    }

}
