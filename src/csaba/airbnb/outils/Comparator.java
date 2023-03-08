package csaba.airbnb.outils;

public class Comparator<T extends Comparable<T>> {
    private final T obj1;
    private final T obj2;

    public Comparator(T obj1, T obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    public T getHigher() {
        if (obj1 != null && obj2 != null) {
            if (obj1.compareTo(obj2) >= 0) {
                return obj1;
            }
            return obj2;
        } else {
            return null;
        }
    }

    public T getLower() {
        if (obj1 != null && obj2 != null) {
            if (obj1.compareTo(obj2) >= 0) {
                return obj2;
            }
            return obj1;
        } else {
            return null;
        }
    }

    public T getObj1() {
        return obj1;
    }

    public T getObj2() {
        return obj2;
    }
}
