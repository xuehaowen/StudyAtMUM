package lesson9.labsolns.prob4;

import java.util.stream.IntStream;

//Solution to Part A.
public class SimplePrimeStream {
	final IntStream primes 
	  = IntStream.iterate(2, n -> PrimesOrdinary.nextPrime(n));
}
