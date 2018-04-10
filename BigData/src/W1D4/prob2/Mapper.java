package W1D4.prob2;

import W1D4.prob1.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper {

    private List<Pair> pairList;
    private String input;
    private Map<String, Integer> map;

    public String getInput() {
        return input;
    }

    public Mapper(String input) {
        this.pairList = new ArrayList<>();
        this.input = input;
        this.map = new HashMap<>();
    }

    public void run(){
        for(String s : input.split(" ")){
            if(map.containsKey(s)){
                int sum = map.get(s);
                sum += 1;
                map.put(s, sum);
            }else {
                map.put(s, 1);
            }
        }
        for (String s : map.keySet()){
            pairList.add(new Pair(s,map.get(s).intValue()));
        }
    }

    public List<Pair> getPairList() {
        return pairList;
    }
}
