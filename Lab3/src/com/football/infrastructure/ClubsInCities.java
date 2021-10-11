package com.football.infrastructure;

import com.football.model.FootballClub;

import java.util.*;

public class ClubsInCities {

    private Map<String, List<FootballClub>> clubsInCities;

    public ClubsInCities() {
        clubsInCities = new HashMap<>();
    }

    public void add(FootballClub club) {
        for(Map.Entry<String, List<FootballClub>> entry : clubsInCities.entrySet()){
            if(entry.getKey().equals(club.getCity())){
                List<FootballClub> clubs = entry.getValue();
                clubs.add(club);
                clubsInCities.put(club.getCity(), clubs);
                return;
            }
        }
        List<FootballClub> clubs = new ArrayList<>();
        clubs.add(club);
        clubsInCities.put(club.getCity(), clubs);
    }

    public void add2(FootballClub club) {
        for(Map.Entry<String, List<FootballClub>> entry : clubsInCities.entrySet()){
            if(entry.getKey().equals(club.getName())){
                List<FootballClub> clubs = entry.getValue();
                clubs.add(club);
                clubsInCities.put(club.getName(), clubs);
                return;
            }
        }
        List<FootballClub> clubs = new ArrayList<>();
        clubs.add(club);
        clubsInCities.put(club.getName(), clubs);
    }

    public void countCitiesForName() {
        for(Map.Entry<String, List<FootballClub>> entry: clubsInCities.entrySet()) {
            List<FootballClub> list = entry.getValue();
            if(list.size() > 1)
                System.out.println(entry.getKey() + " is in " + list.size() + " cities");
        }
    }

    public void print(int n) {
        for(Map.Entry<String, List<FootballClub>> entry: clubsInCities.entrySet()) {
            List<FootballClub> list = entry.getValue();
            System.out.println(String.valueOf(n) + " football clubs from " + entry.getKey());
            for (int i = 0; i < n; i++) {
                if(i < list.size())
                    System.out.println(list.get(i));
                else
                    System.out.println("No more found");
            }
        }
    }

    @Override
    public String toString() {
        return "ClubsInCities{" +
                "clubsInCities=" + clubsInCities +
                '}';
    }
}
