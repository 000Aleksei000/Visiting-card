package com.sergeev.visitcard.data.basket;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "info")
public class InfoProdCost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int quantity;

    @JoinColumn(name = "Costumer_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Costumer costumer;

    @JoinColumn(name = "Product_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Product product;


    @Override
    public String toString() {
        return "InfoProdCost{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoProdCost that = (InfoProdCost) o;
        return quantity == that.quantity && Objects.equals(id, that.id) && Objects.equals(costumer, that.costumer) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity);
    }
}
