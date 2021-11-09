package com.streams;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Visitor implements Serializable {

    private int num;
    private String address;
    private List<Order> orders;

    public Visitor(int num, String address, List<Order> orders) {
        this.num = num;
        this.address = address;
        this.orders = orders;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "num=" + num +
                ", address='" + address + '\'' +
                ", orders=" + orders +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visitor visitor = (Visitor) o;
        return num == visitor.num && Objects.equals(address, visitor.address) && Objects.equals(orders, visitor.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, address, orders);
    }
}
