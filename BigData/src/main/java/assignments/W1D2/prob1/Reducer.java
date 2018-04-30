package assignments.W1D2.prob1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reducer {

	private List<Pair> pairList;
	private List<GroupByPair> groupByPairList;

	public Reducer() {
		this.pairList = new ArrayList<>();
		this.groupByPairList = new ArrayList<>();
	}

	public void copyPairList(List<Pair> pairs) {
		this.pairList.addAll(pairs);
	}

	public void run() {
		Collections.sort(pairList);
		GroupByPair group = null;
		for (Pair pair : pairList) {
			if (group == null || !group.getKey().equals(pair.getKey())) {
				group = new GroupByPair(pair.getKey(), pair.getValue());
				groupByPairList.add(group);
			} else {
				group.addValue(pair.getValue());
			}
		}
		System.out.println(groupByPairList);
		List<Pair> pairs = new ArrayList<>();
		for (GroupByPair groupByPair : groupByPairList) {
			Pair pair = new Pair(groupByPair.getKey(), groupByPair.getValues().size());
			pairs.add(pair);
		}
		System.out.println(pairs);
	}

	public List<Pair> getPairList() {
		return pairList;
	}
}
