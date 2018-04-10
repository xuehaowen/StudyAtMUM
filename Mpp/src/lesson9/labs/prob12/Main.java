package lesson9.labs.prob12;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
	enum CheckNameBy{
		FIRST_NAME,LAST_NAME
	}

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
		
		//print the number of Employees in list whose salary > 100000 and whose last name begins
		//with a letter that comes after the letter 'E'
		System.out.println(f1.apply(list,100000, 'E','Z'));
		
		//print a list of sorted full names - all upper case -- of Employees with
		//salary > 85000 and whose first name begins with a letter that comes before  the letter 'R'
		System.out.println(f2.apply(list, 85000, 'A','R'));
	}

	public static final TriFunction<String, Character, Character, Boolean> checkStartLetter
			= (name, start, end) -> name.charAt(0)>start && name.charAt(0)<end;

	public static final FourFunction<List<Employee>, Integer, Character, Character, Long> f1 =
			(list, salary, start, end) -> list.stream().filter(e -> e.getSalary() > salary.intValue()
					&& checkStartLetter.apply(e.getLastName(),start,end))
			.count();

	public static final FourFunction<List<Employee>, Integer, Character, Character, String> f2 =
			(list, salary, start, end) -> list.stream().filter(e -> e.getSalary() > salary.intValue()
					&& checkStartLetter.apply(e.getFirstName(),start,end))
			.sorted(Comparator.comparing(e -> e.getLastName()+""+e.getFirstName()))
			.map(e -> e.getLastName().toUpperCase()+" "+e.getFirstName().toUpperCase())
			.collect(Collectors.joining(","));

}
