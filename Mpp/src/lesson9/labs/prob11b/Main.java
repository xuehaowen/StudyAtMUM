package lesson9.labs.prob11b;

import java.util.*;
import java.util.stream.Collectors;


public class Main {

	public static void main(String[] args) {
		List<Employee> list = Arrays.asList(new Employee("Joe", "Davis", 120000),
				          new Employee("John", "Sims", 110000),
				          new Employee("Joe", "Stevens", 200000),
		                  new Employee("Andrew", "Reardon", 80000),
		                  new Employee("Joe", "Cummings", 760000),
		                  new Employee("Steven", "Walters", 135000),
		                  new Employee("Thomas", "Blake", 111000),
		                  new Employee("Alice", "Richards", 101000),
		                  new Employee("Donald", "Trump", 100000));

		System.out.println(f.apply(list, 100000, 'M'));
	}

	public static final TriFunction<List<Employee>, Integer, Character, String> f =
			(list, salary, prefix) -> list.stream().filter(e -> e.getSalary() > salary.intValue()
			&& e.getLastName().charAt(0) > prefix.charValue() && e.getLastName().charAt(0) < 'Z')
			.sorted(Comparator.comparing(Employee::getFirstName))
			.map(e -> e.getFirstName()+ " "+ e.getLastName()).collect(Collectors.joining(", "));

}
