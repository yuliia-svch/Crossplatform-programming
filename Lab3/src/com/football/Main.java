package com.football;

import com.football.helpers.FileReader;
import com.football.infrastructure.ClubsInCities;
import com.football.model.FootballClub;

import java.util.*;

public class Main {
    public static void main(String ...args) {
        String info = FileReader.ReadFileInOneString("src/clubs1.txt");
        String[] clubs = info.split("\\n|\\r\\n|\\r");

        System.out.println("1. Створити карту з ключем місто продюсера та значенням –списку всіх\n" +
                "футбольних клубів цього міста. Видрукувати з кожного списку тільки n\n" +
                "перших позицій цього списку.");
        System.out.println("2. Визначити скільки міст мають футбольні клуби з однією назвою.");
        System.out.println("3. З 2 різних файлів зчитати 2 вихідні набори інформації про клуби. Створити\n" +
                "колекцію, що складається з спільних членів двох переліків");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch(option) {
            case 1:
                ClubsInCities clubsInCities = new ClubsInCities();
                for(String club : clubs) {
                    String[] properties = club.split(",");
                    clubsInCities.add(new FootballClub(
                            properties[0], Integer.parseInt(properties[1]), properties[2]));
                }
                System.out.println("Enter a number of how many clubs from each city you wanna see: ");
                int limit = sc.nextInt();
                clubsInCities.print(limit);
                break;
            case 2:
                ClubsInCities clubsInCities2 = new ClubsInCities();
                for(String club : clubs) {
                    String[] properties = club.split(",");
                    clubsInCities2.add2(new FootballClub(
                            properties[0], Integer.parseInt(properties[1]), properties[2]));
                }
                clubsInCities2.countCitiesForName();
                break;
            case 3:
                String info2 = FileReader.ReadFileInOneString("src/clubs2.txt");
                String[] clubs2 = info2.split("\\n|\\r\\n|\\r");
                Set<FootballClub> set = new HashSet<>();
                for(String club : clubs) {
                    String[] properties = club.split(",");
                    set.add(new FootballClub(properties[0], Integer.parseInt(properties[1]), properties[2]));
                }
                for(String club : clubs2) {
                    String[] properties2 = club.split(",");
                    set.add(new FootballClub(properties2[0], Integer.parseInt(properties2[1]), properties2[2]));
                }
                System.out.println(set);
                break;
            default:
                System.out.println("No such option");
        }

    }
}
