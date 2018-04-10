package lesson9.labsolns.prob4;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimesFunctionalParallel {
	final static BiFunction<Integer, Integer, IntStream> firstNNaturalNums 
	  = (m,n) -> IntStream.rangeClosed(m, n);

	public static boolean isPrime(int n) {
		if(n==2) return true;
		int sqrtN = (int)(Math.ceil(Math.sqrt(n)));
		OptionalInt divisor = firstNNaturalNums.apply(2, sqrtN).parallel().filter(k -> (n%k==0)).findAny();
		return !divisor.isPresent();
	}
	
	public static int nextPrime(int n) {
		return firstNNaturalNums.apply(n+1, n+n).filter(k -> isPrime(k)).findFirst().orElse(0);
	}
	
	public static void main(String[] args) {
		
		
		for(int i = 20; i < 40; ++i) {
			
			System.out.println(nextPrime(i));
		}
		/*
		List<Integer> somePrimes = Arrays.asList(2, 3, 5, 7, 11, 13, 17);
		List<Integer> someComposites = Arrays.asList(4, 16, 49, 102);
		for(int prime : somePrimes) {
			System.out.println(prime + ": " + isPrime(prime));
		}
		System.out.println();
		
		for(int comp : someComposites) {
			System.out.println(comp + ": " + isPrime(comp));
		}
		List<Integer> somePrimes = Arrays.asList(2, 3, 5, 7, 11, 13, 17);
		for(int prime : somePrimes) {
			System.out.println(prime + ": " + isPrime(prime));
		} */
	}
}
