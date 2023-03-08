package csaba.airbnb.outils;

import java.util.ArrayList;

public class ComparatorPlus<T extends Comparable<T>> {
    private final ArrayList<T> list;

    public ComparatorPlus() {
        this.list = new ArrayList<>();
    }

    public ComparatorPlus(ArrayList<T> list) {
        this.list = list;
    }

    public void add(T obj) {
        list.add(obj);
    }

    public void remove(int index) {
        list.remove(index);
    }

    public void remove(Object o) {
        list.remove(o);
    }

    public T getHighest() {
        boolean onFirstObj = true;
        T highest = null;

        for (T t : list) {
            if (onFirstObj) {
                highest = t;
                onFirstObj = false;
            } else {
                if (t != null && highest != null) {
                    if (t.compareTo(highest) > 0) {
                        highest = t;
                    }
                }
            }
        }

        return highest;
    }

    public T getLowest() {
        boolean onFirstObj = true;
        T lowest = null;

        for (T t : list) {
            if (onFirstObj) {
                lowest = t;
                onFirstObj = false;
            } else {
                if (t != null && lowest != null) {
                    if (t.compareTo(lowest) < 0) {
                        lowest = t;
                    }
                }
            }
        }

        return lowest;
    }
}
