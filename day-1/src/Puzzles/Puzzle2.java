package Puzzles;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

public class Puzzle2 {
    public static void main(String[] args) {
        List<Integer> inputList;
        try{
            inputList = Puzzle1.parseInput("C:/Users/Tony9/IdeaProjects/Advent-of-Code-2020/day-1/data/input.txt");
        } catch (FileNotFoundException e){
            System.out.println("File not found");
            return;
        }
        Collections.sort(inputList);
        int i = 0;
        int j = 1;
        int k = inputList.size() - 1;
        while(inputList.get(i) + inputList.get(j) + inputList.get(k) > 2020){
            k--;
        }
        // 11, 23, 34, 45, 56, 57, 67
        for(; k >= 2; k--){
            i = 0;
            j = k-1;
            while(inputList.get(i) + inputList.get(j) + inputList.get(k) != 2020 && i < j){
                if(inputList.get(i) + inputList.get(j) + inputList.get(k) > 2020){
                    j--;
                } else if (inputList.get(i) + inputList.get(j) + inputList.get(k) < 2020){
                    i++;
                }
            }
            if(inputList.get(i) + inputList.get(j) + inputList.get(k) == 2020){
                System.out.println(inputList.get(i) * inputList.get(j) * inputList.get(k));
                break;
            }
        }
    }
}
