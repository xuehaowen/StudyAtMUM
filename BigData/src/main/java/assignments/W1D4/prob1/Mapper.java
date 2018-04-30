package assignments.W1D4.prob1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper {

	private List<Pair> pairList;
	private StringBuilder stringBuilder;
	private Map<String, Integer> map;

	public Mapper(String filename) {
		this.pairList = new ArrayList<>();
		this.stringBuilder = new StringBuilder();
		this.map = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filename)))) {
			String line;
			while ((line = br.readLine()) != null) {
				stringBuilder.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		String newLine = stringBuilder.toString().replaceAll("\\-", " ").replaceAll("\"|\'|\\.", "").toLowerCase();
		String[] words = newLine.split(" ");
		for (String s : words) {
			if (s.matches("[a-z]+")) {
				if (map.containsKey(s)) {
					int sum = map.get(s);
					sum += 1;
					map.put(s, sum);
				} else {
					map.put(s, 1);
				}
			}
		}
		for (String s : map.keySet()) {
			pairList.add(new Pair(s, map.get(s).intValue()));
		}
	}

	public List<Pair> getPairList() {
		return pairList;
	}

	public String getStringBuilder() {
		return stringBuilder.toString();
	}
}
