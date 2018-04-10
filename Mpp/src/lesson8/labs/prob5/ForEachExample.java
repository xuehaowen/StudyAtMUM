package lesson8.labs.prob5;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;


public class ForEachExample {
	
	public static void main(String[] args) {
		List<String> list = Arrays.asList("Hello there", "Goodbye", "Back soon", 
				"Away", "On Vacation", "Everywhere you want to be");
		
		//print each element of the list in upper case format
		//part a
		//lambda expression
		list.forEach(s -> s.toUpperCase());
		//part b
		//Class::Method
		list.forEach(String::toUpperCase);
	}
	
}