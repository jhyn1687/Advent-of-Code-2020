import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day6 {

    public static void main(String[] args) throws FileNotFoundException{
        List<String> inputList = parseInput("Solutions/inputs/day6.txt");
        puzzle1(inputList);
        puzzle2(inputList);
    }

    private static void puzzle2(List<String> inputList) {
        int sum = 0;
        for(String str : inputList){
            String[] arr = str.split("\n");
            Set<Character> alphabet = new HashSet<>();
            for(char i = 'a'; i <= 'z'; i++){
                alphabet.add(i);
            }
            for(String ele : arr){
                Set<Character> charSet = new HashSet<>();
                char[] charArr = ele.toCharArray();
                for(char c : charArr){
                    charSet.add(c);
                }
                alphabet.retainAll(charSet);
            }
            sum += alphabet.size();
        }
        System.out.println("Puzzle 2: " + sum);
    }

    private static void puzzle1(List<String> inputList){
        int sum = 0;
        for(String str : inputList){
            String newStr = str.replace(" ", "").replace("\n", "");
            String[] arr = newStr.split("");
            Set<String> set = new HashSet<>();
            Collections.addAll(set, arr);
            sum += set.size();
        }
        System.out.println("Puzzle 1: " + sum);
    }

    public static List<String> parseInput(String str) throws FileNotFoundException {
        List<String> list = new ArrayList<>();
        File inputFile = new File(str);
        Scanner scanner = new Scanner(inputFile);
        int currIndex = 0;
        StringBuilder currString = new StringBuilder();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(!line.equals("")){
                currString.append(line).append("\n");
            } else {
                list.add(currIndex, currString.toString());
                currString = new StringBuilder();
                currIndex++;
            }
        }
        list.add(currIndex, currString.toString());
        return list;
    }
}
