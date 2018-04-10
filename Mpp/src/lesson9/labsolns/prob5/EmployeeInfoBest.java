package lesson9.labsolns.prob5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.*;

@SuppressWarnings("rawtypes")
public class EmployeeInfoBest {
	static enum SortMethod {BYNAME, BYSALARY};
	final Function<Employee, String> byName = e -> e.getName();
	final Function<Employee, Integer> bySalary = e -> e.getSalary();
	
	final static class Pair<S,T> {
		Pair(S f, T t) {
			first = f;
			second = t;
		}
		S first;
		T second;
	}
	
	final Pair<Function,Function> byNamePair
	  = new Pair<>(byName, bySalary);
	final Pair<Function,Function> bySalaryPair
	  = new Pair<>(bySalary, byName);
	@SuppressWarnings("serial")
	final HashMap<SortMethod, Pair<Function,Function>> map =
	  new HashMap<SortMethod, Pair<Function,Function>>() {
		{
				put(SortMethod.BYNAME, byNamePair);
				put(SortMethod.BYSALARY, bySalaryPair);
	    }
	};
	
	@SuppressWarnings("unchecked")
	public void sort(List<Employee> emps, final SortMethod method) {
		Collections.sort(emps, 
				Comparator.comparing(map.get(method).first)
				          .thenComparing(map.get(method).second));
				
	}
	
	public static void main(String[] args) {
		List<Employee> emps = new ArrayList<>();
		emps.add(new Employee("Joe", 100000));
		emps.add(new Employee("Tim", 50000));
		emps.add(new Employee("Andy", 60000));
		EmployeeInfoBest ei = new EmployeeInfoBest();
		ei.sort(emps, SortMethod.BYNAME);
		System.out.println(emps);
		//same instance
		ei.sort(emps, SortMethod.BYSALARY);
		System.out.println(emps);
	}
}
