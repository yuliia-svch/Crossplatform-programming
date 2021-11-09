package com.streams;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String... args) {
        String info = ReadFileInOneString("src/pizzas.txt");
        String[] pizzas = info.split("\\n|\\r\\n|\\r");
        List<Pizza> pizzaList = new ArrayList<>();
        for(String s : pizzas){
            String[] fields = s.split(" ");
            String[] ingreds = fields[3].split(",");
            pizzaList.add(new Pizza(fields[0],
                    Double.parseDouble(fields[1]),
                    Double.parseDouble(fields[2]), List.of(ingreds)));
        }

        pizzaList.forEach(System.out::println);

        String info2 = ReadFileInOneString("src/orders.txt");
        String[] orders = info2.split("\\n|\\r\\n|\\r");
        List<Order> orderList = new ArrayList<>();
        for(String s : orders) {
            String[] fields = s.split(" ");
            String[] p = fields[0].split(",");
            List<Pizza> pizzasInOrder = new ArrayList<>();
            for(String str : p) {
                for(Pizza pizza : pizzaList) {
                    if(pizza.getName().equals(str)) {
                        pizzasInOrder.add(pizza);
                        break;
                    }
                }
            }
            Calendar calendar = new GregorianCalendar();
            calendar.set(Calendar.YEAR, Integer.parseInt(fields[1]));
            calendar.set(Calendar.MONTH, Integer.parseInt(fields[2]));
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(fields[3]));
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(fields[4]));
            calendar.set(Calendar.MINUTE, Integer.parseInt(fields[5]));
            calendar.set(Calendar.SECOND, 0);
            Date date = calendar.getTime();
            orderList.add(new Order(pizzasInOrder, date, Boolean.parseBoolean(fields[6])));

        }
        orderList.forEach(System.out::println);

        String info3 = ReadFileInOneString("src/visitors.txt");
        String[] visitors = info3.split("\\n|\\r\\n|\\r");
        List<Visitor> visitorList = new ArrayList<>();
        for(String s : visitors) {
            String[] fields = s.split(",");
            String[] o = fields[2].split(" ");
            List<Order> tmp = new ArrayList<>();
            for(String str : o) {
                if (orderList.size() > Integer.parseInt(str)-1) {
                    tmp.add(orderList.get(Integer.parseInt(str)-1));
                }
            }
            visitorList.add(new Visitor(Integer.parseInt(fields[0]), fields[1], tmp));
        }
        visitorList.forEach(System.out::println);

        Pizzeria pizzeria = new Pizzeria(pizzaList, visitorList);

        System.out.println("Choose function(1-6) or enter 0 to quit: ");
        Scanner sc = new Scanner(System.in);
        Integer choice = sc.nextInt();
        while(choice != 0){
            switch(choice) {
                case 1:
                    List<Order> sorted = pizzeria.sortByDeliveryDate();
                    for(Order o : sorted) {
                        System.out.println(o);
                    }
                    break;
                case 2:
                    List<String> addresses = pizzeria.getVisitorsWithMoreThan2Pizzas();
                    for(String s : addresses) {
                        System.out.println(s);
                    }
                    break;
                case 3:
                    System.out.println(pizzeria.countVisitorsWhoOrderedPizza("Hawaian"));
                    break;
                case 4:
                    System.out.println(pizzeria.findLargestAmountOfPizzas(visitorList.get(1), pizzaList));
                    break;
                case 5:
                    Map<Pizza, List<Visitor>> map = pizzeria.getListOfPizzaConsumers();
                    for(Map.Entry<Pizza, List<Visitor>> m: map.entrySet()) {
                        System.out.println(m);
                    }
                    break;
                case 6:
                    pizzeria.getPendingOrders().forEach((key, value) -> {
                                long diffSeconds = value / 1000 % 60;
                                long diffMinutes = value / (60 * 1000) % 60;
                                long diffHours = value / (60 * 60 * 1000) % 24;
                                long diffDays = value / (24 * 60 * 60 * 1000);
                                System.out.println(key + "\nwith overdue: ");
                                System.out.println(diffDays + " days, "
                                        + diffHours + " hours, "
                                        + diffMinutes + " minutes, "
                                        + diffSeconds + " seconds.\n");
                    });
                    break;
                default:
                    System.out.println("Wrong number, try again");

            }
            choice = sc.nextInt();
        }

    }

    public static String ReadFileInOneString(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(fileName), "UTF-8");
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
            return sb.toString().trim();
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
        return sb.toString();
    }
}
