package com.streams;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {

    private List<Pizza> pizzas;
    private Date deliveryDate;
    private Boolean finished;

    public Order(List<Pizza> pizzas, Date deliveryDate, Boolean finished) {
        this.pizzas = pizzas;
        this.deliveryDate = deliveryDate;
        this.finished = finished;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "Order{" +
                "pizzas=" + pizzas +
                ", deliveryDate=" + deliveryDate +
                ", finished=" + finished +
                '}';
    }
}
