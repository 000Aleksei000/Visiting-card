package com.sergeev.visitcard.data.basket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long prodId;

    @Column(name = "prodName")
    private String prodName;

    @Column(name = "price")
    private double price;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    private Set<InfoProdCost> infoProdCosts;

    public void addInfoProdCost(InfoProdCost infoProdCost) {
        infoProdCost.setProduct(this);
        this.infoProdCosts.add(infoProdCost);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && Objects.equals(prodId, product.prodId) && Objects.equals(prodName, product.prodName) && Objects.equals(infoProdCosts, product.infoProdCosts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prodId, prodName, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "prodId=" + prodId +
                ", prodName='" + prodName + '\'' +
                ", price=" + price +
                '}';
    }
}
