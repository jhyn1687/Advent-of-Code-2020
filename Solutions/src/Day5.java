import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day5 {

    public static void main(String[] args) throws FileNotFoundException{
        List<String> inputList = parseInput("Solutions/inputs/day5.txt");
        List<Integer> listID = puzzle1(inputList);
        puzzle2(listID);
    }

    private static void puzzle2(List<Integer> listID) {
        for(int i = 0; i < (127 * 8) + 7; i++){
            if(!listID.contains(i) && listID.contains(i-1) && listID.contains(i+1)){
                System.out.print("Puzzle 2: " + i);
            }
        }
    }

    private static List<Integer> puzzle1(List<String> inputList){
        int highestSeatID = 0;
        List<Integer> listID = new ArrayList<>();
        for(String input: inputList){
            char[] arr = input.toCharArray();
            int lo = 0;
            int hi = 127;
            for(int i = 0; i < 7; i++){
                if(arr[i] == 'F'){
                    hi = (hi - lo)/2 + lo;
                } else if (arr[i] == 'B') {
                    lo = (hi - lo +1)/2 + lo;
                }
            }
            int row = lo;
            lo = 0;
            hi = 7;
            for(int i = 7; i < arr.length; i++){
                if(arr[i] == 'L'){
                    hi = (hi - lo)/2 + lo;
                } else if (arr[i] == 'R') {
                    lo = (hi - lo+1)/2 + lo;
                }
            }
            int col = lo;
            int seatID = row * 8 + col;
            listID.add(seatID);
            if(seatID > highestSeatID){
                highestSeatID = seatID;
            }
        }
        System.out.println("Puzzle 1: " + highestSeatID);
        return listID;
    }

    public static List<String> parseInput(String str) throws FileNotFoundException {
        List<String> list = new ArrayList<>();
        File inputFile = new File(str);
        Scanner scanner = new Scanner(inputFile);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            list.add(line);
        }
        return list;
    }
}
