import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day17 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> inputList = parseInput("Solutions/inputs/day17.txt");
        puzzle1(inputList);
        puzzle2(inputList);
    }

    public static List<String> parseInput(String str) throws FileNotFoundException {
        List<String> list = new ArrayList<>();
        File inputFile = new File(str);
        Scanner scanner = new Scanner(inputFile);
        while(scanner.hasNextLine()){
            list.add(scanner.nextLine());
        }
        return list;
    }

    public static void puzzle1(List<String> inputList){

    }

    public static void puzzle2(List<String> inputList){

    }
}
