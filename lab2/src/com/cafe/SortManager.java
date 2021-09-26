package com.cafe;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortManager {
    private List<Coffee> coffees;

    public SortManager(List<Coffee> coffees) {
        this.coffees = coffees;
    }

    public List<Coffee> sortByVolume(boolean reversed) {
        if(reversed) {
            VolumeComparator volumeComparator = new VolumeComparator();
            coffees.sort(Collections.reverseOrder(volumeComparator));
            return coffees;
        }
        coffees.sort(new VolumeComparator());
        return coffees;
    }

    public List<Coffee> sortByVolumeLambda(boolean reversed) {
        if(reversed) {
            coffees.sort((Coffee o1, Coffee o2)-> (o2.getVolume()-o1.getVolume()));
            return coffees;
        }
        coffees.sort((Coffee o1, Coffee o2)-> (o1.getVolume()-o2.getVolume()));
        return coffees;
    }

    public List<Coffee> sortByName(boolean reversed) {
        if(reversed) {
            NameComparator nameComparator = new NameComparator();
            coffees.sort(Collections.reverseOrder(nameComparator));
            return coffees;
        }
        coffees.sort(new NameComparator());
        return coffees;
    }

    public List<Coffee> sortByNameAnonymous(boolean reversed) {
        if(reversed) {
            coffees.sort(new Comparator<Coffee>() {
                public int compare(Coffee o1, Coffee o2) {
                    return o2.getName().compareTo(o1.getName());
                }
            });
            return coffees;
        }
        coffees.sort(new Comparator<Coffee>() {
            public int compare(Coffee o1, Coffee o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return coffees;
    }

    private static class VolumeComparator implements Comparator<Coffee> {

        @Override
        public int compare(Coffee o1, Coffee o2) {
            return o1.getVolume()-o2.getVolume();
        }
    }

    private class NameComparator implements Comparator<Coffee> {

        @Override
        public int compare(Coffee o1, Coffee o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
}
