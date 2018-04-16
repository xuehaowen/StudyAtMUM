package assignments.W1D4.prob2;

import assignments.W1D4.prob1.GroupByPair;
import assignments.W1D4.prob1.Pair;
import assignments.W1D4.prob1.Reducer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordCount {

    /**
     * number of mapper
     */
    private int m;
    /**
     * number of reducer
     */
    private int r;
    private String[] inputSplit;
    private List<Mapper> mappers = new ArrayList<>();
    private List<Reducer> reducers = new ArrayList<>();

    public WordCount(String[] inputSplit, int r) {
        this.inputSplit = inputSplit;
        this.m = inputSplit.length;
        this.r = r;
        System.out.printf("Number of Input-Splits: %d \n", this.m);
        System.out.printf("Number of Reducers: %d \n", this.r);
        for (int i = 0; i < m; i++) {
            Mapper mapper = new Mapper(inputSplit[i]);
            mappers.add(mapper);
            System.out.printf("Mapper %d Input: \n", i);
            System.out.println(mapper.getInput());
        }
        for (int j = 0; j < r; j++) {
            reducers.add(new Reducer());
        }

    }

    public void run() {
        Mapper mapper = null;
        for (int i = 0; i < mappers.size(); i++) {
            mapper = mappers.get(i);
            mapper.run();
            System.out.printf("Mapper %d Output:\n%s \n", i, getPairOutput(mappers.get(i).getPairList()));

        }
        Map<Integer, List<Pair>> map = null;
        for (int i = 0; i < mappers.size(); i++) {
            map = new HashMap<>();
            for (int j = 0; j < r; j++) {
                map.put(j, new ArrayList<Pair>());
            }
            for (Pair pair : mappers.get(i).getPairList()) {
                int index = getPartition(pair.getKey().toString());
                map.get(index).add(pair);
                reducers.get(index).addPair(pair);
            }
            for (int j = 0; j < r; j++) {
                System.out.printf("Pairs send from Mapper %d Reducer %d \n", i, j);
                System.out.println(getPairOutput(map.get(j)));
            }
        }
        Reducer reducer = null;
        for (int i = 0; i < r; i++) {
            reducer = reducers.get(i);
            reducer.run();
            System.out.printf("Reducer %d input: \n%s \n",i, getGroupByPairOutput(reducer.getGroupByPairList()));

        }
        for (int i = 0; i < r; i++) {
            reducer = reducers.get(i);
            System.out.printf("Reducer %d output: \n%s \n",i, getPairOutput(reducer.getOutputPairList()));
        }
    }

    public int getPartition(String key) {
        int index = 1;
        char[] chars = key.toCharArray();
        for(int i=0;i<chars.length;i++){
            if(i==0){
                if(chars[i] <= 'k'){
                    index = 0;
                }else if(chars[i] >= 'r'){
                    index = 2;
                }else {
                    return 1;
                }
            }else {
                if(index == 0){
                   if(chars[i] > 'k')
                       return 1;
                }
                if(index == 2){
                    if (chars[i] < 'r')
                        return 1;
                }
            }
        }
        return index;
    }

    public String getPairOutput(List<Pair> pairList){
        StringBuilder sb = new StringBuilder();
        for (Pair pair : pairList){
            sb.append(pair).append("\n");
        }
        return sb.toString();
    }

    public String getGroupByPairOutput(List<GroupByPair> groupByPairList){
        StringBuilder sb = new StringBuilder();
        for (GroupByPair groupByPair:groupByPairList){
            sb.append(groupByPair).append("\n");
        }
        return sb.toString();
    }
}
