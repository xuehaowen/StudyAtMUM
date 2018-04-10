package lesson8.labs.prob1.partJ;


import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
        Supplier<Double> f = () -> Math.random();
        System.out.println(f.get());

        subSupplier s = new Main().new subSupplier();
        System.out.println(s.get());
    }

    class subSupplier implements Supplier<Double> {

        @Override
        public Double get() {
            return Math.random();
        }
    }


}
