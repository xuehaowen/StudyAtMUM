package assignments.W1D4.prob1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reducer {

    private List<Pair> pairList;
    private List<GroupByPair> groupByPairList;
    private List<Pair> outputPairList;

    public Reducer() {
        this.pairList = new ArrayList<>();
        this.groupByPairList = new ArrayList<>();
        this.outputPairList = new ArrayList<>();
    }

    public void addPair(Pair pair) {
        this.pairList.add(pair);
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
        for (GroupByPair groupByPair : groupByPairList) {
            Pair pair = new Pair(groupByPair.getKey(), groupByPair.getValues().size());
            outputPairList.add(pair);
        }
    }

    public List<GroupByPair> getGroupByPairList() {
        return groupByPairList;
    }

    public List<Pair> getOutputPairList() {
        return outputPairList;
    }
}
