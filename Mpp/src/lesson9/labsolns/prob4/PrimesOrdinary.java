package lesson9.labsolns.prob4;

/**
 * This class implements standard (not very efficient) methods
 * for generating/detecting primes. The best implementation
 * in PrimeStream makes use of BigInteger's nextProbablePrime method.
 */
public class PrimesOrdinary {
	public static int nextPrime(int num) {
		if(num < 2) return 2;
		int next = num + 1;
		while(!isPrime(next)) {
			next = next + 1;
		}
		return next;
	}
	
	public static boolean isPrime(int n) {
		for(int k = 2; k * k <= n; ++k) {
			if(n % k == 0) return false;
		}
		return true;
		
	}
	
	
	
	
	
}
