package com.sergeev.visitcard.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Table(name = "Town")
@AllArgsConstructor
@NoArgsConstructor
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "townName")
    private String name;

    @JoinColumn(name = "Country_Id")
    @ManyToOne(fetch = FetchType.EAGER)
    @NonNull
    private Country country;

    @OneToMany(fetch = FetchType.EAGER , mappedBy = "town")
    @NonNull
    private Set<People> peoples = new HashSet<>();


    public void addPeople(People people) {
        people.setTown(this);
        this.peoples.add(people);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Town town = (Town) o;
        return Objects.equals(name, town.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
