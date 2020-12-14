import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day14 {

    public static void main(String[] args) throws FileNotFoundException{
        List<String> inputList = parseInput("Solutions/inputs/day14.txt");
        puzzle1(inputList);
        puzzle2(inputList);
    }

    private static void puzzle2(List<String> inputList) {
        Map<Long, Long> mem = new HashMap<>();
        char[] mask = new char[36];
        for (String str: inputList) {
            if(str.split(" ")[0].equals("mask")){
                mask = str.split( " ")[2].toCharArray();
            } else {
                long value = Long.parseLong(str.split(" ")[2]);
                puzzle2Recurse(mem, str, mask.clone(), value, 0);
            }
        }
        Iterator<Long> iterator = mem.values().iterator();
        long sum = 0;
        while(iterator.hasNext()){
            sum += iterator.next();
        }
        System.out.println("Puzzle 2: " + sum);
    }

    private static void puzzle2Recurse(Map<Long, Long> mem, String str, char[] mask, long value, int index){
        if(index >= mask.length){
            puzzle2Helper(mem, str, mask.clone(), value);
        } else if(mask[index] == '0'){
            mask[index] = 'X';
            puzzle2Recurse(mem, str, mask.clone(), value, index+1);
        } else if(mask[index] == 'X'){
            char[] mask1 = mask.clone();
            mask1[index] = '0';
            char[] mask2 = mask.clone();
            mask2[index] = '1';
            puzzle2Recurse(mem, str, mask1, value, index+1);
            puzzle2Recurse(mem, str, mask2, value, index+1);
        } else {
            puzzle2Recurse(mem, str, mask.clone(), value, index+1);
        }
    }

    private static void puzzle2Helper(Map<Long, Long> mem, String str, char[] mask, long value){
        char[] key = new char[36];
        char[] binary = Long.toBinaryString(Long.parseLong(str.substring(str.indexOf("[") + 1, str.indexOf("]")))).toCharArray();
        for(int i = 0; i < key.length; i++){
            if(i >= key.length - binary.length){
                key[i] = binary[i - (key.length - binary.length)];
            } else {
                key[i] = '0';
            }
        }
        for (int i = 0; i < mask.length; i++) {
            if(mask[i] != 'X'){
                key[i] = mask[i];
            }
        }
        mem.put(Long.parseLong(String.valueOf(key), 2), value);
    }

    private static void puzzle1(List<String> inputList){
        Map<Long, Long> mem = new HashMap<>();
        char[] mask = new char[36];
        for (String str: inputList) {
            if(str.split(" ")[0].equals("mask")){
                mask = str.split( " ")[2].toCharArray();
            } else {
                long key = Long.parseLong(str.substring(str.indexOf("[") + 1, str.indexOf("]")));
                puzzle1Helper(mem, key, str, mask);
            }
        }
        Iterator<Long> iterator = mem.values().iterator();
        long sum = 0;
        while(iterator.hasNext()){
            sum += iterator.next();
        }
        System.out.println("Puzzle 1: " + sum);
    }

    public static void puzzle1Helper(Map<Long, Long> mem, long key, String str, char[] mask){
        char[] value = new char[36];
        char[] binary = Long.toBinaryString(Long.parseLong(str.split(" ")[2])).toCharArray();
        for(int i = 0; i < value.length; i++){
            if(i >= value.length - binary.length ){
                value[i] = binary[i - (value.length - binary.length)];
            } else {
                value[i] = '0';
            }
        }
        for (int i = 0; i < mask.length; i++) {
            if(mask[i] != 'X'){
                value[i] = mask[i];
            }
        }
        mem.put(key, Long.parseLong(String.valueOf(value), 2));
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
