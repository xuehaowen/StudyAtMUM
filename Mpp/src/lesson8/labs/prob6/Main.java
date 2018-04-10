package lesson8.labs.prob6;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {
    //A
    Function<Employee, String> f1 = (Employee e) -> e.getName();
    Function<Employee, String> f2 = Employee::getName;

    //B
    BiConsumer<Employee, String> bc1 = (Employee e, String s) -> e.setName(s);
    BiConsumer<Employee, String> bc2 = Employee::setName;

    //C
    BiFunction<Integer, Integer, Double> bf1 = (Integer x,Integer y) -> Math.pow(x,y);
    BiFunction<Integer, Integer, Double> bf2 = Math::pow;

    //G
    Supplier<EmployeeNameComparator> s1 = () -> new EmployeeNameComparator();
    BiFunction<Employee, Employee, Integer> bf3 = (Employee e1, Employee e2) -> s1.get().compare(e1, e2);

    Supplier<EmployeeNameComparator> s2 = EmployeeNameComparator::new;
    BiFunction<Employee, Employee, Integer> bf4 = s2.get()::compare;

    public static void main(String[] args) {
        Main m = new Main();
        m.evaluator();
    }

    void evaluator(){
        Employee e1 = new Employee("John",200);
        Employee e2 = new Employee("Jake",300);
        System.out.println(f1.apply(e1));
        System.out.println(f2.apply(e2));

        bc1.accept(e1, "Ali");
        bc2.accept(e2, "Bob");

        System.out.println(bf1.apply(2,3));
        System.out.println(bf2.apply(2,3));

        System.out.println(bf3.apply(e1, e2));
        System.out.println(bf4.apply(e1, e2));

    }
}
