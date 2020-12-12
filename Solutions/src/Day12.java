import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day12 {

    public static void main(String[] args) throws FileNotFoundException{
        List<String> inputList = parseInput("Solutions/inputs/day12.txt");
        puzzle1(inputList);
        puzzle2(inputList);
    }

    private static void puzzle2(List<String> inputList) {
        // east is positive west is negative, is x
        // north is positive south is negative, is y
        int x = 10, y = 1;
        int longitude = 0, latitude = 0;
        for (String s : inputList) {
            char command = s.charAt(0);
            int num = Integer.parseInt(s.substring(1));
            switch (command) {
                case 'L':
                    for(int i = 0; i < num / 90; i++){
                        int temp = x;
                        x = -y;
                        y = temp;
                    }
                    break;
                case 'R':
                    for(int i = 0; i < num / 90; i++){
                        int temp = y;
                        y = -x;
                        x = temp;
                    }
                    break;
                case 'N':
                    y += num;
                    break;
                case 'S':
                    y -= num;
                    break;
                case 'E':
                    x += num;
                    break;
                case 'W':
                    x -= num;
                    break;
                case 'F':
                    longitude += num * x;
                    latitude += num * y;
            }
        }
        System.out.println("Puzzle 2: " + (Math.abs(longitude)+Math.abs(latitude)));
    }

    private static void puzzle1(List<String> inputList){
        // east is positive west is negative
        // north is positive south is negative
        // north = 0, east = 1, south = 2, west = 3
        char[] arr = {'N', 'E', 'S', 'W'};
        int direction = 1;
        int longitude = 0, latitude = 0;
        for (String s : inputList) {
            char command = s.charAt(0);
            int num = Integer.parseInt(s.substring(1));
            switch (command) {
                case 'L':
                    direction = (direction - (num / 90) + 4) % 4;
                    break;
                case 'R':
                    direction = (direction + (num / 90) + 4) % 4;
                    break;
                case 'F':
                    command = arr[direction];
            }
            switch (command) {
                case 'N':
                    longitude += num;
                    break;
                case 'S':
                    longitude -= num;
                    break;
                case 'E':
                    latitude += num;
                    break;
                case 'W':
                    latitude -= num;
                    break;
            }
        }
        System.out.println("Puzzle 1: " + (Math.abs(longitude)+Math.abs(latitude)));

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
