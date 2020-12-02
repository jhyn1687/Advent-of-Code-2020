import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> inputList = parseInput("Solutions/inputs/day1.txt");
        Collections.sort(inputList);
        puzzle1(inputList);
        puzzle2(inputList);
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

    public static void puzzle1(List<Integer> inputList){
        int i = 0;
        int j = inputList.size() - 1;
        while(inputList.get(i) + inputList.get(j) != 2020){
            if(inputList.get(i) + inputList.get(j) > 2020){
                j--;
            } else if (inputList.get(i) + inputList.get(j) < 2020){
                i++;
            }
        }
        System.out.println("Puzzle 1: " + inputList.get(i) * inputList.get(j));
    }

    public static void puzzle2(List<Integer> inputList){
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
                System.out.println("Puzzle 2: " +inputList.get(i) * inputList.get(j) * inputList.get(k));
            }
        }
    }
}
