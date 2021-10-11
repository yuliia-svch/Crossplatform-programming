package com.football.model;

import java.util.Objects;

public class FootballClub {
    private String name;
    private int year;
    private String city;

    public FootballClub(String name, int year, String city) {
        this.name = name;
        this.year = year;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootballClub that = (FootballClub) o;
        return year == that.year && name.equals(that.name) && city.equals(that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, year, city);
    }

    @Override
    public String toString() {
        return "FootballClub{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", city='" + city + '\'' +
                '}';
    }
}
