package lesson8.labs.prob4;

import java.util.List;

public class Main {

    public static void main(String[] args) {

    }

    public int countWords(List<String> words, char c, char d, int len){
        return (int) words.stream()
                .filter(word -> word.length() == len)
                .filter(word -> word.contains(""+c))
                .filter(word -> !word.contains(""+d))
                .count();
    }
}
