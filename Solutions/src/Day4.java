import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.util.Arrays.asList;

public class Day4 {

    public static final String[] FIELDS = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
    public static final String[] COLORS = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};

    public static void main(String[] args) throws FileNotFoundException {
        List<String> inputList = parseInput("Solutions/inputs/day4.txt");
        List<String> valid1 = puzzle1(inputList);
        puzzle2(valid1);
    }

    private static List<String> puzzle1(List<String> inputList){
        List<String> validStrings = new ArrayList<>();
        int valid = 0;
        for (String input: inputList) {
            Map<String, String> map = new HashMap<>();
            String[] arr = input.split(" ");
            for (String ele : arr) {
                String[] values = ele.split(":");
                map.put(values[0], values[1]);
            }
            boolean isValid = true;
            for (String field : FIELDS){
                if (!map.containsKey(field)) {
                    isValid = false;
                    break;
                }
            }
            if(isValid){
                validStrings.add(input);
                valid++;
            }
        }
        System.out.println("Puzzle 1: " + valid);
        return validStrings;
    }

    private static void puzzle2(List<String> inputList){
        int valid = 0;
        for (String input: inputList) {
            String[] arr = input.split(" ");
            boolean validity = true;
            for(String ele : arr){
                String[] values = ele.split(":");
                try{
                    validity = isValid(values);
                    if(!validity){
                        break;
                    }
                } catch (NumberFormatException e) {
                    validity = false;
                    break;
                }
            }
            if(validity) {
                valid++;
            }
        }
        System.out.println("Puzzle 2: " + valid);
    }

    private static boolean isValid(String[] values) throws NumberFormatException {
        switch (values[0]) {
            case "byr":
                if (Integer.parseInt(values[1]) < 1920 || Integer.parseInt(values[1]) > 2002) {
                    return false;
                }
                break;
            case "iyr":
                if (Integer.parseInt(values[1]) < 2010 || Integer.parseInt(values[1]) > 2020) {
                    return false;
                }
                break;
            case "eyr":
                if (Integer.parseInt(values[1]) < 2020 || Integer.parseInt(values[1]) > 2030) {
                    return false;
                }
                break;
            case "hgt":
                if (values[1].contains("cm")) {
                    int height = Integer.parseInt(values[1].substring(0, values[1].indexOf("cm")));
                    if (height < 150 || height > 193) {
                        return false;
                    }
                } else if (values[1].contains("in")) {
                    int height = Integer.parseInt(values[1].substring(0, values[1].indexOf("in")));
                    if (height < 59 || height > 76) {
                        return false;
                    }
                } else {
                    return false;
                }
                break;
            case "hcl":
                if (!values[1].matches("#([0-9]|[a-f]){6}")) {
                    return false;
                }
                break;
            case "ecl":
                List<String> list = Arrays.asList(COLORS);
                if(!list.contains(values[1])) {
                    return false;
                }
                break;
            case "pid":
                if (!values[1].matches("^([0-9]){9}$")) {
                    return false;
                }
                break;
        }
        return true;
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
                currString.append(line).append(" ");
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
