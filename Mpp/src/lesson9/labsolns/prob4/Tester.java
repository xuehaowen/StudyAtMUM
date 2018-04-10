package lesson9.labsolns.prob4;

public class Tester {
	public static void main(String[] args) {
		final int NUM_PRIMES = 5000;
		PrimeStreamOrdinary pso = new PrimeStreamOrdinary();
		PrimeStreamFunctionalSerial psfs = new PrimeStreamFunctionalSerial();
		PrimeStreamFunctionalParallel psfp = new PrimeStreamFunctionalParallel();
		PrimeStreamProbable psp = new PrimeStreamProbable();

		long start1 = System.currentTimeMillis();
		pso.printFirstNPrimes(NUM_PRIMES);
		long end1 = System.currentTimeMillis();

		long start2 = System.currentTimeMillis();
		psfs.printFirstNPrimes(NUM_PRIMES);
		long end2 = System.currentTimeMillis();
		
		long start3 = System.currentTimeMillis();
		psfp.printFirstNPrimes(NUM_PRIMES);
		long end3 = System.currentTimeMillis();

		long start4 = System.currentTimeMillis();
		psp.printFirstNPrimes(NUM_PRIMES);
		long end4 = System.currentTimeMillis();

		System.out.println("Results");
		System.out.println(" Ordinary: " + (end1 - start1) + " milliseconds");
		System.out.println(" Functional - Serial: " + (end2 - start2) + " milliseconds");
		System.out.println(" Functional - Parallel: " + (end3 - start3) + " milliseconds");
		System.out.println(" Probable: " + (end4 - start4) + " milliseconds");
	}
}
