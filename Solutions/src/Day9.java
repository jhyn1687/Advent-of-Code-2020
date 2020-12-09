import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day9 {

    public static void main(String[] args) throws FileNotFoundException{
        List<Long> inputList = parseInput("Solutions/inputs/day9.txt");
        long invalid = puzzle1(inputList);
        puzzle2(inputList, invalid);
    }

    private static void puzzle2(List<Long> inputList, long invalid) {
        for(int i = 0; i < inputList.size(); i++){
            int sum = 0;
            long max = inputList.get(i);
            long min = inputList.get(i);
            for(int j = i; j < inputList.size(); j++){
                sum += inputList.get(j);
                if(inputList.get(j) > max){
                    max = inputList.get(j);
                }
                if(inputList.get(j) < min){
                    min = inputList.get(j);
                }
                if(sum == invalid){
                    System.out.println("Puzzle 2: " + (min + max));
                    return;
                } else if (sum > invalid){
                    break;
                }
            }
        }
    }

    private static long puzzle1(List<Long> inputList) {
        Queue<Long> queue = new LinkedList<>();
        for(int i = 0; i < 25; i++){
            queue.add(inputList.get(i));
        }
        for(int i = 25; i < inputList.size(); i++){
            long check = inputList.get(i);
            boolean isValid = false;
            List<Long> list = new ArrayList<>(queue);
            for(int j = 0; j < list.size(); j++){
                for(int k = 0; k < list.size(); k++){
                    if(j != k && list.get(j) + list.get(k) == check){
                        isValid = true;
                        break;
                    }
                }
            }
            if(!isValid){
                System.out.println("Puzzle 1: " + check);
                return check;
            }
            queue.poll();
            queue.add(check);
        }
        return -1;
    }

    public static List<Long> parseInput(String str) throws FileNotFoundException {
        List<Long> list = new ArrayList<>();
        File inputFile = new File(str);
        Scanner scanner = new Scanner(inputFile);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            long num = Long.parseLong(line);
            list.add(num);
        }
        return list;
    }
}
