package W1D4.prob1;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GroupByPair<K, V> {

    private K key;
    private List<V> values;

    public GroupByPair(K key, V value) {
        this.key = key;
        this.values = new ArrayList<>();
        this.values.add(value);
    }

    public K getKey() {
        return key;
    }

    public List<V> getValues() {
        return values;
    }

    public void addValue(V value) {
        this.values.add(value);
    }

    @Override
    public String toString() {
        return "<" + key + "," + values + ">";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupByPair<?, ?> that = (GroupByPair<?, ?>) o;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {

        return Objects.hash(key);
    }
}
