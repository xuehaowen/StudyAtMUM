package lesson9.labsolns.prob4;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.IntStream;
public class PrimeStreamOrdinary {
	
	final Function<Integer, IntStream> primesLimit =
			x -> IntStream.iterate(2, n -> PrimesOrdinary.nextPrime(n)).limit(x);
	
	public void printFirstNPrimes(int n) {
		System.out.println(Arrays.toString(primesLimit.apply(n).toArray()));
		           
	}
	
	public static void main(String[] args) {
		PrimeStreamOrdinary ps = new PrimeStreamOrdinary();
		ps.printFirstNPrimes(10);
		System.out.println("====");
		ps.printFirstNPrimes(5);
		
	}
	
	
	
	
	
}
