package com.sergeev.visitcard.data.basket;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "costumer")
public class Costumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(name = "cookie")
    private String cookie;

    @Column(name = "date")
    private String date;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "costumer")
    private Set<InfoProdCost> infoProdCosts;

    public void addInfoProdCost(InfoProdCost info) {
        info.setCostumer(this);
        this.infoProdCosts.add(info);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Costumer costumer = (Costumer) o;
        return Objects.equals(id, costumer.id) && cookie.equals(costumer.cookie) && Objects.equals(date, costumer.date) && Objects.equals(infoProdCosts, costumer.infoProdCosts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cookie, date);
    }

    @Override
    public String toString() {
        return "Costumer{" +
                "id=" + id +
                ", cookie='" + cookie + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
