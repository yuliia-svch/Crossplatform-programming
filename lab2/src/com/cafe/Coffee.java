package com.cafe;

public abstract class Coffee {

    protected String typeOfCoffee;
    protected int volume;
    protected String name = "Coffee";

    protected Coffee(String typeOfCoffee, int volume) {
        this.typeOfCoffee = typeOfCoffee;
        this.volume = volume;
    }

    public String getTypeOfCoffee() {
        return typeOfCoffee;
    }

    public int getVolume() {
        return volume;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + ", " + typeOfCoffee + ", " + volume + "\n";
    }
}
