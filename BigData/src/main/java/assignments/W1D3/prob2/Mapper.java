package assignments.W1D3.prob2;

import assignments.W1D3.prob1.Pair;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    private List<Pair> pairList;
    private String input;

    public String getInput() {
        return input;
    }

    public Mapper(String input) {
        this.input = input;
    }

    public void run(){
        pairList = new ArrayList<>();
        for(String s : input.split(" ")){
            pairList.add(new Pair(s,1));
        }
    }

    public List<Pair> getPairList() {
        return pairList;
    }
}
