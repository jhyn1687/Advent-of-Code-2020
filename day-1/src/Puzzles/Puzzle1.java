package Puzzles;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Puzzle1 {
    public static void main(String[] args) {
        List<Integer> inputList;
        try{
            inputList = parseInput("C:/Users/Tony9/IdeaProjects/Advent-of-Code-2020/day-1/data/input.txt");
        } catch (FileNotFoundException e){
            System.out.println("File not found");
            return;
        }
        Collections.sort(inputList);
        int i = 0;
        int j = inputList.size() - 1;
        while(inputList.get(i) + inputList.get(j) != 2020){
            if(inputList.get(i) + inputList.get(j) > 2020){
                j--;
            } else if (inputList.get(i) + inputList.get(j) < 2020){
                i++;
            }
        }
        System.out.println(inputList.get(i) * inputList.get(j));
    }

    public static List<Integer> parseInput(String str) throws FileNotFoundException {
        List<Integer> list = new ArrayList<>();
        File inputFile = new File(str);
        Scanner scanner = new Scanner(inputFile);
        while(scanner.hasNextInt()){
            list.add(scanner.nextInt());
        }
        return list;
    }
}
