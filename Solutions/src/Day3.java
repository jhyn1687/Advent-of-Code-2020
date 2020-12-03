import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day3 {

    public static void main(String[] args) throws FileNotFoundException{
        List<char[]> inputList = parseInput("Solutions/inputs/day3.txt");
        puzzle1(inputList);
        puzzle2(inputList);
    }

    private static void puzzle2(List<char[]> inputList) {
        long total = 1;
        total *= checkSlope(inputList, 1, 1);
        total *= checkSlope(inputList, 3, 1);
        total *= checkSlope(inputList, 5, 1);
        total *= checkSlope(inputList, 7, 1);
        total *= checkSlope(inputList, 1, 2);
        System.out.println("Puzzle 2: " + total);
    }

    private static void puzzle1(List<char[]> inputList) {
        System.out.println("Puzzle 1: " + checkSlope(inputList, 3, 1));
    }

    private static int checkSlope(List<char[]> inputList, int dx, int dy){
        int x = 0, count = 0;
        for(int i = dy; i < inputList.size(); i += dy){
            x+=dx;
            char[] arr = inputList.get(i);
            char ele = arr[x % arr.length];
            if(ele == '#'){
                count++;
            }
        }
        return count;
    }

    public static List<char[]> parseInput(String str) throws FileNotFoundException {
        List<char[]> list = new ArrayList<>();
        File inputFile = new File(str);
        Scanner scanner = new Scanner(inputFile);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            list.add(line.toCharArray());
        }
        return list;
    }


}
