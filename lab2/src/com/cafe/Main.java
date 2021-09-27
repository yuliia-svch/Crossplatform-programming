package com.cafe;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String... args) {
        List<Coffee> coffeeList = new ArrayList<>();
        System.out.println("How many drinks?");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        for(int i = 0; i<num; i++) {
            System.out.println("Enter name:");
            String name = sc.next();
            System.out.println("Enter type of coffee: ");
            String typeOfCoffee = sc.next();
            System.out.println("Enter volume: ");
            int volume = sc.nextInt();
            switch(name.toLowerCase(Locale.ROOT)){
                case "arabica":
                    coffeeList.add(new Arabica(typeOfCoffee, volume));
                    break;
                case "americano":
                    coffeeList.add(new Americano(typeOfCoffee, volume));
                    break;
                case "cappuccino":
                    coffeeList.add(new Cappuccino(typeOfCoffee, volume));
                    break;
                case "latte":
                    coffeeList.add(new Latte(typeOfCoffee, volume));
                    break;
                case "flatwhite":
                    coffeeList.add(new FlatWhite(typeOfCoffee, volume));
                    break;
                case "raf":
                    coffeeList.add(new Raf(typeOfCoffee, volume));
                    break;
                case "robusta":
                    coffeeList.add(new Robusta(typeOfCoffee, volume));
                    break;
                case "espresso":
                    coffeeList.add(new Espresso(typeOfCoffee, volume));
                    break;
                default:
                    System.out.println("No such drink");
            }
        }
        System.out.println("Initial list:");
        System.out.println(coffeeList);
        System.out.println("What type of coffee do you want to search by?");
        String search = sc.next();
        SearchManager sm = new SearchManager(coffeeList);
        List<Coffee> found = sm.searchByType(search);
        System.out.println("Search by type completed:");
        System.out.println(found);
        SortManager sortM = new SortManager(found);
        System.out.println("Inner class sorting(reversed):");
        System.out.println(sortM.sortByName(true));
        System.out.println("Nested class sorting(reversed):");
        System.out.println(sortM.sortByVolume(true));
        System.out.println("Anonymous class sorting:");
        System.out.println(sortM.sortByNameAnonymous(false));
        System.out.println("Lambda function sorting:");
        System.out.println(sortM.sortByVolumeLambda(false));
    }
}
