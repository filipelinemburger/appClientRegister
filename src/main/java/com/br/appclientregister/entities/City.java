package com.br.appclientregister.entities;

import com.br.appclientregister.rest.dto.CityDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "city")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column
    private Integer id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String state;

    @OneToMany(mappedBy = "city", fetch = LAZY, cascade = ALL)
    private Set<Client> clients;

    public City(CityDTO cityDTO){
        this.setName(cityDTO.getCityName());
        this.setState(cityDTO.getState());
    }

}
