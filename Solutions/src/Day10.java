import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day10 {

    public static void main(String[] args) throws FileNotFoundException{
        List<Integer> inputList = parseInput("Solutions/inputs/day10.txt");
        Collections.sort(inputList);
        inputList.add(0, 0);
        inputList.add(inputList.get(inputList.size() - 1) + 3);
        puzzle1(inputList);
         puzzle2(inputList);
    }

    private static void puzzle2(List<Integer> inputList) {
        Map<Integer, Long> traversed = new HashMap<>();
        System.out.println("Puzzle 2: " + puzzle2Helper(inputList, 0, traversed));

    }

    private static long puzzle2Helper(List<Integer> inputList, int i, Map<Integer, Long> traversed){
        if(i == inputList.size() - 1){
            return 1;
        } if (traversed.containsKey(i)){
            return traversed.get(i);
        }
        long ans = 0;
        for(int j = i + 1; j < inputList.size(); j++){
            if(inputList.get(j) - inputList.get(i) <= 3){
                ans += puzzle2Helper(inputList, j, traversed);
            } else {
                break;
            }
        }
        traversed.put(i, ans);
        return ans;
    }

    private static void puzzle1(List<Integer> inputList){
        int sum1 = 0;
        int sum3 = 0;

        for(int i = 0; i < inputList.size() - 1; i++){
            if(inputList.get(i+1) - inputList.get(i) == 1){
                sum1++;
            }
            if(inputList.get(i+1) - inputList.get(i) == 3){
                sum3++;
            }
        }

        System.out.println("Puzzle 1: " + (sum1 * sum3));
    }

    public static List<Integer> parseInput(String str) throws FileNotFoundException {
        List<Integer> list = new ArrayList<>();
        File inputFile = new File(str);
        Scanner scanner = new Scanner(inputFile);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            list.add(Integer.parseInt(line));

        }
        return list;
    }
}
