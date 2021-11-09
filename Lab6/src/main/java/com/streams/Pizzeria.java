package com.streams;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Pizzeria implements Serializable {

    private List<Pizza> pizzas;
    private List<Visitor> visitors;

    public Pizzeria(List<Pizza> pizzas, List<Visitor> visitors) {
        this.pizzas = pizzas;
        this.visitors = visitors;
    }

    public List<Order> sortByDeliveryDate() {
        return visitors.stream()
                .flatMap(client -> client.getOrders().stream())
                .sorted(Comparator.comparing(Order::getDeliveryDate))
                .collect(Collectors.toList());
    }

    public List<String> getVisitorsWithMoreThan2Pizzas() {
        return visitors.stream()
                .filter(v -> v.getOrders().stream()
                        .mapToInt(order -> order.getPizzas().size())
                        .sum() > 2)
                .flatMap(v -> Stream.of(v.getAddress()))
                .collect(Collectors.toList());
    }

    public long countVisitorsWhoOrderedPizza(String name) {
        return visitors.stream()
                .filter(v -> v.getOrders().stream()
                        .flatMap(order -> order.getPizzas().stream())
                        .anyMatch(pizza -> name.equals(pizza.getName())))
                .count();

    }

    public long findLargestAmountOfPizzas(Visitor v, List<Pizza> pizzaList) {
        return pizzas.stream().map(x -> Collections.frequency(v.getOrders().stream()
                .flatMap(order -> order.getPizzas().stream())
                .filter(pizza -> pizzaList.stream()
                        .anyMatch(pizza::equals))
                .collect(Collectors.toList()), x))
                .max(Comparator.comparing(Integer::intValue)).orElse(0);
    }

    public Map<Pizza, List<Visitor>> getListOfPizzaConsumers() {
        Map<Pizza, List<Visitor>> pizzaConsumers = new HashMap<>();
        pizzas.forEach(pizza -> pizzaConsumers.merge(
                pizza,
                visitors.stream()
                        .filter(v -> v.getOrders().stream()
                                .flatMap(order -> order.getPizzas().stream())
                                .anyMatch(pizzaConsumer -> pizzaConsumer.equals(pizza)))
                        .collect(Collectors.toList()),
                (l1, l2) -> {
                    List<Visitor> visitors = new ArrayList<>(l1);
                    visitors.addAll(l2);
                    return visitors;
                }));
        return pizzaConsumers;
    }

    public Map<Order, Long> getPendingOrders() {
        return visitors.stream()
                .flatMap(v -> v.getOrders().stream())
                .filter(order -> new Date().after(order.getDeliveryDate())
                        && !order.getFinished())
                .collect(Collectors.toMap(Function.identity(),
                        order -> new Date().getTime() - order.getDeliveryDate().getTime()));
    }
}
