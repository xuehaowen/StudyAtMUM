package assignments.W1D4.prob2;

public class Main {
    public static void main(String[] args) {
        WordCount wordCount = new WordCount(
                new String[]{"apple lemon mango salmon wheat apple",
                "barley salmon apple orange carrot rice",
                "mango carrot lemon apple rice tuna"},3);
        wordCount.run();
//        WordCount wordCount2 = new WordCount(
//                new String[]{"aaaaa lemon mango salmon wheat rrrr",
//                        "barley salmon apple orange carrot rice",
//                        "mango carrot lemon apple rice tuna"},3);
//        wordCount2.run();
    }
}
