package com.streams;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Pizza implements Serializable {

    private String name;
    private double weight;
    private double price;
    private List<String> ingredients;

    public Pizza(String name, double weight, double price, List<String> ingreds) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.ingredients = ingreds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", ingredients=" + ingredients +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return Double.compare(pizza.weight, weight) == 0 && Double.compare(pizza.price, price) == 0 && Objects.equals(name, pizza.name) && Objects.equals(ingredients, pizza.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, price, ingredients);
    }
}
