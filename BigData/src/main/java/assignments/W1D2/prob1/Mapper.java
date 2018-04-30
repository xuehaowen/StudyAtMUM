package assignments.W1D2.prob1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mapper {

	private List<Pair> pairList;

	public Mapper() {

		this.pairList = new ArrayList<>();
	}

	public void run(String filename) {
		List<Pair> pairList = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filename)))) {
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				pairList.addAll(getPairList(line));
			}
			Collections.sort(pairList);
			System.out.println(pairList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.pairList = pairList;
	}

	private List<Pair> getPairList(String line) {
		// System.out.println(line);
		List<Pair> pairList = new ArrayList<>();
		String newLine = line.replaceAll("\\-", " ").replaceAll("\"|\'|\\.", "").toLowerCase();
		// System.out.println(newLine);
		String[] words = newLine.split(" ");
		for (String s : words) {
			if (s.matches("[a-z]+"))
				pairList.add(new Pair(s, 1));
		}
		return pairList;
	}

	public List<Pair> getPairList() {
		return pairList;
	}

	// private void runStream(String filename) {
	//
	// try (BufferedReader br = new BufferedReader(new
	// InputStreamReader(getClass().getResourceAsStream(filename)))) {
	// br.lines().flatMap(s -> Stream.of(s.split(" ")))
	// .map(String::toLowerCase)
	// .filter(s -> s.matches("[a-z]+"))
	// .map(s -> new StringIntPair(s, 1))
	// .sorted(Comparator.comparing(p -> p.getKey().toString()))
	// .forEach(System.out::println);
	// } catch (Exception e) {
	//
	// }
	// }

}
