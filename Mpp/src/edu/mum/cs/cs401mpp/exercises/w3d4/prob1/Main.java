package edu.mum.cs.cs401mpp.exercises.w3d4.prob1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Main m = new Main();
        m.printSquares(3);
    }

    void printSquares(Integer n){
        List<Integer> s = Stream.iterate(1, x -> x + 1).limit(n)
                .map(x -> x * x)
                .collect(Collectors.toList());
        System.out.println(s);
    }

    int[] combineArray(int[][] a_in){
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<a_in.length;i++){
            for(int j=0;j<a_in[i].length;j++){
                list.add(a_in[i][j]);
            }
        }

        int[] arr = new int[list.size()];
        for(int i=0;i<list.size();i++){
            arr[i] = list.get(i);
        }

        return arr;
    }

    int[] combineArrayInStream(int[][] a_in){
        return Arrays.stream(a_in).flatMapToInt(arr -> Arrays.stream(arr)).toArray();
    }

}
