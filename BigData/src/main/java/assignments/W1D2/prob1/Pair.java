package assignments.W1D2.prob1;


public class Pair<K, V> implements Comparable<Pair> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "(" + key + "," + value + ")";
    }

    @Override
    public int compareTo(Pair o) {
        return this.getKey().toString().compareTo(o.getKey().toString());
    }
}
