package com.cupcakes.webshop.model;

import javax.persistence.*;

/**
 * Created by scheldejonas on 11/09/16.
 */
@Entity
public class Orderline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private CupCake cupCake;

    // Additional fields
    private int quantity;
    private int sumPrice;

    public Orderline() {
    }

    public Orderline(Order order, CupCake cupCake, int quantity, int sumPrice) {
        this.order = order;
        this.cupCake = cupCake;
        this.quantity = quantity;
        this.sumPrice = sumPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public CupCake getCupCake() {
        return cupCake;
    }

    public void setCupCake(CupCake cupCake) {
        this.cupCake = cupCake;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(int sumPrice) {
        this.sumPrice = sumPrice;
    }
}
