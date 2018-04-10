package lesson9.labsolns.prob4;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
public class PrimeStreamFunctionalParallel {
	Function<Integer, IntStream> primesLimit =
			x -> IntStream.iterate(2, n -> PrimesFunctionalParallel.nextPrime(n)).limit(x);
	
	public void printFirstNPrimes(int n) {
		primesLimit.apply(n).toArray();
		           //.forEach(System.out::println);
	}
	
	public static void main(String[] args) {
		PrimeStreamOrdinary ps = new PrimeStreamOrdinary();
		ps.printFirstNPrimes(10);
		System.out.println("====");
		ps.printFirstNPrimes(5);
		
	}
	
}
