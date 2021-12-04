package com.sergeev.visitcard.data.crud;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Table(name = "Country")
@AllArgsConstructor
@NoArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "country")
    private Set<Town> towns = new HashSet<>();


    @OneToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL, mappedBy = "country")
    private Set<People> peoples = new HashSet<>();


    public void addPeople(People people) {
        people.setCountry(this);
        this.peoples.add(people);
    }

    public void addTown(Town town) {
        town.setCountry(this);
        this.towns.add(town);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(name, country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


}
