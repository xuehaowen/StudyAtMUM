package lesson9.labs.prob4;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    static final Stream<Integer> primes = Stream.iterate(2, x -> x + 1).limit(30);

    public static void main(String[] args) {
        System.out.println(primes.collect(Collectors.toList()));
    }
}
