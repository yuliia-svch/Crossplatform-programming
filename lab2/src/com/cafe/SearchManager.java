package com.cafe;

import java.util.ArrayList;
import java.util.List;

public class SearchManager {

    private List<Coffee> coffees;

    public SearchManager(List<Coffee> coffees) {
        this.coffees = coffees;
    }

    public List<Coffee> searchByType(String type) {
        List<Coffee> found = new ArrayList<>();
        for(Coffee c : coffees) {
            if(c.getTypeOfCoffee().equals(type)){
                found.add(c);
            }
        }
        return found;
    }
}
