package W1D3.prob1;

public class Main {

    public static void main(String[] args) {
        WordCount wordCount = new WordCount(
                new String[]{"testData1.txt",
                        "testData2.txt",
                        "testData3.txt",
                        "testData4.txt",
                        "testData5.txt"},4);
        wordCount.run();
    }
}
