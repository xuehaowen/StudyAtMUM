package W1D3.prob1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Mapper {

    private List<Pair> pairList;
    private StringBuilder stringBuilder;

    public Mapper(String filename) {
        this.pairList = new ArrayList<>();
        this.stringBuilder = new StringBuilder();
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
            if (s.matches("[a-z]+"))
                pairList.add(new Pair(s, 1));
        }
    }

    public List<Pair> getPairList() {
        return pairList;
    }



    public String getStringBuilder() {
        return stringBuilder.toString();
    }
}
