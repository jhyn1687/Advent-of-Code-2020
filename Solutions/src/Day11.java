import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day11 {

    public static void main(String[] args) throws FileNotFoundException{
        char[][] inputList = parseInput("Solutions/inputs/day11.txt");
        puzzle1(inputList);
//        puzzle2(inputList);
    }

    private static void puzzle2(char[][] inputList) {

    }

    private static void puzzle1(char[][] inputList){
        char[][] prevArr = new char[94][97];
        char[][] currArr = inputList.clone();
        while(true){
            prevArr = java.util.Arrays.stream(currArr).map(char[]::clone).toArray(char[][]::new);
            currArr = puzzle1Helper(currArr);
            if(Arrays.deepEquals(prevArr, currArr)){
                int count = 0;
                for(char[] arr: currArr){
                    for(char chr: arr){
                        if(chr == '#'){
                            count++;
                        }
                    }
                }
                System.out.println("Puzzle 1: " + count);
                break;
            }
        }
    }

    private static char[][] puzzle1Helper(char[][] currArr){
        int[][] directions = {{-1, -1}, {0, -1}, {1, -1}, {-1, 0}, {1, 0}, {-1, 1}, {0, 1}, {1, 1}};
        for (int row = 0; row < currArr.length; row++) {
            for(int col = 0; col < currArr[row].length; col++){
                char currChar = currArr[row][col];
                switch(currChar){
                    case 'L':
                        for (int[] coord: directions) {
                            if(coordIsValid(row + coord[0], col + coord[1], currArr)){
                                if(currArr[row + coord[0]][col + coord[1]] == '#'){
                                    break;
                                }
                            }
                        }
                        currArr[row][col] = '#';
                        break;
                    case '#':
                        int neighbors = 0;
                        for (int[] coord: directions) {
                            if(coordIsValid(row + coord[0], col + coord[1], currArr)){
                                if(currArr[row + coord[0]][col + coord[1]] == '#'){
                                    neighbors++;
                                }
                            }
                        }
                        if(neighbors >= 4){
                            currArr[row][col] = 'L';
                        }
                        break;
                }
            }
        }
        return currArr;
    }
    private static boolean coordIsValid(int row, int col, char[][] arr){
        return (row > -1 && row < arr.length && col > -1 && col < arr[row].length);
    }

    public static char[][] parseInput(String str) throws FileNotFoundException {
        char[][] list = new char[97][94];
        File inputFile = new File(str);
        Scanner scanner = new Scanner(inputFile);
        int row = 0;

        while(scanner.hasNextLine()){
            int col = 0;
            String line = scanner.nextLine();
            for(char chr: line.toCharArray()){
                list[row][col] = chr;
                col++;
            }
            row++;
        }
        return list;
    }
}
