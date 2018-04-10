package lesson9.labs.prob9;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void printSquares(int num){
        IntFunction<Integer> f = x -> x + 1;
        IntStream.iterate(1, f::apply).limit(num).forEach(x -> System.out.println(x * x));
    }

    public static void main(String[] args) {
        printSquares(4);

        Main m = new Main();
        Stream<Integer> s = Stream.iterate(1, x-> x+ 1).limit(20);
        m.test2(s);
    }

    public void test1(Stream<String> stringStream){
        stringStream.sorted((s1, s2) -> s1.compareTo(s2)).collect(Collectors.toList());
    }

    public void test2(Stream<Integer> myIntStream){
//        List<Integer> list = myIntStream.sorted().collect(Collectors.toList());
//        System.out.println(list.get(0));
//        System.out.println(list.get(list.size()-1));

        IntSummaryStatistics summary = myIntStream.collect(Collectors.summarizingInt(x -> x.intValue()));
        System.out.println(summary.getMax());
        System.out.println(summary.getMin());
    }

}
