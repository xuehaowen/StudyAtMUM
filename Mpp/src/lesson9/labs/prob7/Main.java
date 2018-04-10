package lesson9.labs.prob7;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

	public static void main(String[] args) {
		List<Integer> intList = Arrays.asList(4, 5, -2, 0, -3, -1, -5, -4);
		//expected output: [0, -1, -2, -3, -4, 4, -5, 5]
		ordering1(intList);
		List<String> stringList = Arrays.asList("cba", "efg", "doe", "fie", "set");
		//expected output: [cba, fie, doe, efg, set]
		ordering2(stringList);
		
	}
	
	//Orders the integers according to this pattern:
	// 0, -1, 1, -2, 2, -3, 3, . . .
	//Using this ordering, this method sorts the list as part of 
	//a stream pipeline, and prints to the console
	public static void ordering1(List<Integer> list) {
		Comparator<Integer> com1 = (i1,i2) -> Math.abs(i1) - Math.abs(i2);
		Comparator<Integer> com2 = (i1, i2) -> i1-i2;

		System.out.println(list.stream().sorted(com1.thenComparing(com2)).collect(Collectors.toList()));
	}
	
	//Orders words by first reversing each and comparing 
	//in the usual way.  So 
	//    cba precedes bed
	//since
	//    cba.reverse() precedes bed.reverse()
	//in the usual ordering
	//Using this ordering, this method sorts the list as part of 
	//a stream pipeline, and prints to the console
	public static void ordering2(List<String> words) {
		Comparator<String> com1 = (s1, s2) -> new StringBuilder(s1).reverse().toString().compareTo(new StringBuilder(s2).reverse().toString());

		System.out.println(words.stream().sorted(com1).collect(Collectors.toList()));
	}

}
