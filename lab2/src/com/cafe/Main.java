package com.cafe;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String... args) {
        List<Coffee> coffeeList = new ArrayList<>();
        coffeeList.add(new Americano("ground", 25));
        coffeeList.add(new Cappuccino("ground", 62));
        coffeeList.add(new Arabica("grain", 37));
        coffeeList.add(new Raf("ground", 49));
        coffeeList.add(new FlatWhite("ground", 19));
        coffeeList.add(new Americano("ground", 28));
        coffeeList.add(new Americano("package", 25));
        SearchManager sm = new SearchManager(coffeeList);
        List<Coffee> found = sm.searchByType("ground");
        System.out.println(found);
        SortManager sortM = new SortManager(found);
        System.out.println(sortM.sortByName(true));
        System.out.println(sortM.sortByVolume(true));
        System.out.println(sortM.sortByNameAnonymous(false));
        System.out.println(sortM.sortByVolumeLambda(false));
    }
}
