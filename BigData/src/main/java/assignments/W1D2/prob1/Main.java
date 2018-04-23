package assignments.W1D2.prob1;

public class Main {

    public static void main(String[] args) {
        Mapper m = new Mapper();
        m.run("testDataForW1D1.txt");
        Reducer r = new Reducer();
        r.copyPairList(m.getPairList());
        r.run();
    }
}
