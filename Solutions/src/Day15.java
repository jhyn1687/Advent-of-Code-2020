import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day15 {
    public static void main(String[] args) throws FileNotFoundException {
        List<Long> inputList = parseInput("Solutions/inputs/day15.txt");
        puzzle1(inputList);
        puzzle2(inputList);
    }

    public static List<Long> parseInput(String str) throws FileNotFoundException {
        List<Long> list = new ArrayList<>();
        File inputFile = new File(str);
        Scanner scanner = new Scanner(inputFile);
        while(scanner.hasNextLong()){
            list.add(scanner.nextLong());
        }
        return list;
    }

    public static void puzzle1(List<Long> inputList){
        System.out.println("Puzzle 1: " + loop(inputList, 2020));
    }

    public static void puzzle2(List<Long> inputList){
        System.out.println("Puzzle 2: " + loop(inputList, 30000000));
    }

    private static long loop(List<Long> inputList, int loops){
        Map<Long, Long> mapPrev = new HashMap<>();
        long turn = 1;
        long lastNum = 0;
        for (int i = 1; i <= loops; i++) {
            if(i == 1){
                lastNum = inputList.get(0);
            } else if(i <= inputList.size()){
                mapPrev.put(lastNum, turn++);
                lastNum = inputList.get(i-1);
            } else if(mapPrev.containsKey(lastNum)){
                long num = turn - mapPrev.get(lastNum);
                mapPrev.put(lastNum, turn++);
                lastNum = num;
            } else {
                mapPrev.put(lastNum, turn++);
                lastNum = 0;
            }
        }
        return lastNum;
    }
}
