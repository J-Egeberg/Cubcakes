package com.cupcakes.webshop.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by scheldejonas on 10/09/16.
 */
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order")
    private List<Orderline> orderlines = new ArrayList<>();

    @OneToOne
    private Invoice invoice;

    // Additional lines
    private int totalPrice;

    public Order() {
    }

    public Order(User user, List<Orderline> orderlines, Invoice invoice, int totalPrice) {
        this.user = user;
        this.orderlines = orderlines;
        this.invoice = invoice;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Orderline> getOrderlines() {
        return orderlines;
    }

    public void setOrderlines(List<Orderline> orderlines) {
        this.orderlines = orderlines;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
