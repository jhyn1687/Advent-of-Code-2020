import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day13 {

    public static void main(String[] args) throws FileNotFoundException{
        List<String> inputList = parseInput("Solutions/inputs/day13.txt");
        puzzle1(inputList);
        puzzle2(inputList);
    }

    private static void puzzle2(List<String> inputList) {
        int time = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i < inputList.size(); i++) {
            String input = inputList.get(i);
            if(!input.equals("x")){
                int inputNum = Integer.parseInt(input);
                map.put(inputNum, i-1);
            }
        }
        System.out.println(map);
        // Chinese remainder theorem
        // This is super slow, might optimize later
        while (true)
        {
            Iterator<Integer> iter = map.keySet().iterator();
            while(iter.hasNext()){
                int route = iter.next();
                if(time % route != (route - map.get(route))){
                    break;
                }
            }

            if (!iter.hasNext()) {
                System.out.println("Puzzle 2: " + time);
                break;
            }

            time++;
        }
    }

    private static void puzzle1(List<String> inputList){
        int time = Integer.parseInt(inputList.get(0));
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i < inputList.size(); i++) {
            String input = inputList.get(i);
            if(!input.equals("x")){
                int inputNum = Integer.parseInt(input);
                map.put(inputNum - (time % inputNum), inputNum);
            }
        }
        List<Integer> keyList = new ArrayList<>(map.keySet());
        Collections.sort(keyList);
        int wait = keyList.get(0);
        int route = map.get(wait);
        System.out.println("Puzzle 1: " + (wait * route));
    }

    public static List<String> parseInput(String str) throws FileNotFoundException {
        List<String> list = new ArrayList<>();
        File inputFile = new File(str);
        Scanner scanner = new Scanner(inputFile);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] arr = line.split(",");
            list.addAll(Arrays.asList(arr));
        }
        return list;
    }
}
