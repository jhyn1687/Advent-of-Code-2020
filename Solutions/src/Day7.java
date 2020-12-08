import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day7 {

    public static void main(String[] args) throws FileNotFoundException{
        Map<String, List<String>> inputMap1 = parseInput1("Solutions/inputs/day7.txt");
        puzzle1(inputMap1);
        Map<String, List<String>> inputMap2 = parseInput2("Solutions/inputs/day7.txt");
        List<String> inputList = inputMap2.get("shiny gold");
        int out = puzzle2(inputMap2, inputList);
        System.out.println("Puzzle 2: " + out);
    }

    private static int puzzle2(Map<String, List<String>> inputMap, List<String> inputList) {
        int sum = 0;
        for(String str: inputList){
            List<String> list = inputMap.get(str);
            if(list != null){
                sum += puzzle2(inputMap, list);
            }
        }
        return sum + inputList.size();
    }

    private static void puzzle1(Map<String, List<String>> inputMap){
        PriorityQueue<String> activeQueue = new PriorityQueue<>();
        Set<String> finishedSet = new HashSet<>();
        activeQueue.add("shiny gold");
        while(!activeQueue.isEmpty()){
            String active = activeQueue.poll();
            List<String> list = inputMap.get(active);
            if(finishedSet.contains(active)){
                continue;
            }
            if(list != null){
                activeQueue.addAll(list);
            }
            finishedSet.add(active);
        }
        System.out.println(finishedSet);
        System.out.println("Puzzle 1: " + (finishedSet.size() - 1));
    }

    public static Map<String, List<String>> parseInput1(String str) throws FileNotFoundException {
        Map<String, List<String>> map = new HashMap<>();
        File inputFile = new File(str);
        Scanner scanner = new Scanner(inputFile);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            line = line.replaceAll("[0-9]|contain|,|\\.","")
                    .replace("bags", "bag");
            String[] arr = line.split("bag");
            String outerBag = arr[0].trim();
            for(int i = 1; i < arr.length; i++){
                String innerBag = arr[i].trim();
                if(map.containsKey(innerBag)){
                    List<String> list = map.get(innerBag);
                    list.add(outerBag);
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(outerBag);
                    map.put(innerBag, list);
                }
            }
        }
        System.out.println(map.get("shiny gold"));
        return map;
    }

    public static Map<String, List<String>> parseInput2(String str) throws FileNotFoundException {
        Map<String, List<String>> map = new HashMap<>();
        File inputFile = new File(str);
        Scanner scanner = new Scanner(inputFile);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            line = line.replaceAll("contain|,|\\.","")
                    .replace("bags", "bag")
                    .replace("no", "0");
            String[] arr = line.split("bag");
            String outerBag = arr[0].trim();
            for(int i = 1; i < arr.length; i++){
                String[] numBags = arr[i].split("(?<=\\d)(?=\\D)");
                String innerBag = numBags[1].trim();
                for(int j = 0; j < Integer.parseInt(numBags[0].trim()); j++){
                    if(map.containsKey(outerBag)){
                        List<String> list = map.get(outerBag);
                        list.add(innerBag);
                    } else {
                        List<String> list = new ArrayList<>();
                        list.add(innerBag);
                        map.put(outerBag, list);
                    }
                }
            }
        }
        System.out.println(map.get("vibrant gray"));
        return map;
    }
}
