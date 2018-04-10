package lesson9.labsolns.prob4;
import java.math.BigInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class PrimeStreamProbable {
	private final BigInteger TWO = BigInteger.ONE.add(BigInteger.ONE);
	//not needed
	final Stream<BigInteger> primes = Stream.iterate(TWO, n -> n.nextProbablePrime());
	
	
	final Function<Long, Stream<BigInteger>> primesLimit =
			x -> Stream.iterate(TWO, n -> n.nextProbablePrime()).limit(x);
	
	public void printFirstNPrimes(long n) {
		primesLimit.apply(n).collect(Collectors.toList())
		           .forEach(System.out::println);
	}
	
	public static void main(String[] args) {
		PrimeStreamProbable ps = new PrimeStreamProbable();
		ps.printFirstNPrimes(10);
		System.out.println("====");
		ps.printFirstNPrimes(5);
		
	}
	
	
	
	
	
}
