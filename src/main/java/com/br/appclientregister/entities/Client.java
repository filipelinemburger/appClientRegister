package com.br.appclientregister.entities;

import com.br.appclientregister.entities.enums.Genre;
import com.br.appclientregister.rest.dto.ClientDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column
    private Integer id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(name = "genre", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Genre genre;

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthday;

    @ManyToOne(fetch = EAGER, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    public Client(ClientDTO clientDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        this.setBirthday(LocalDate.parse(clientDTO.getBirthdayDate(), formatter));
        Genre genre = clientDTO.getGenre().toLowerCase().equals("male") ? Genre.MALE : Genre.FEMALE;
        this.setGenre(genre);
        this.setName(clientDTO.getClientName());
    }


    public int getAge(){
        Period period = Period.between(birthday, LocalDate.now());
        return Math.abs(period.getYears());
    }



}