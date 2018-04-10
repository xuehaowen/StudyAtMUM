package edu.mum.cs.mpp.finalexam.part3q2a;

import javax.naming.InitialContext;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MyFibonacciApp {

	public static void main(String[] args) {
		
		System.out.println("Begin Printing Fibonacci numbers...");
		printFirstNFibonacci(0, 1, 10);
		System.out.println();
		int sumFibo = sumFirstNFibonacci(0, 1, 10);
		System.out.println(sumFibo);

	}
	
	// Generate and print the first n fibonacci numbers using Stream API.
	// Example Fibonacci sequence: 0,1,1,2,3,5,8,13,21,34,55,89,...
	public static void printFirstNFibonacci(int a, int b, int n) {
		
		/* Implement your code here */
		System.out.println(Stream.iterate(new int[]{a, b}, s -> new int[]{s[1], s[0] + s[1]})
				.limit(n)
				.map(x -> x[0])
				.collect(Collectors.toList()));
		
	}

	// Compute the sum of first n fibonacci numbers using Stream API.
	private static int sumFirstNFibonacci(int a, int b, int n) {

		/* Implement your code here */
		return Stream.iterate(new int[]{a, b}, s -> new int[]{s[1], s[0] + s[1]})
				.limit(n)
				.map(x -> x[0])
				.reduce((x1, x2)-> x1+ x2)
				.get();
	}

}
