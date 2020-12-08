import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day8 {

    public static void main(String[] args) throws FileNotFoundException{
        List<String> inputList = parseInput("Solutions/inputs/day8.txt");
        puzzle1(inputList);
        puzzle2(inputList);
    }

    private static void puzzle2(List<String> inputList) {
        for(int i = 0; i < inputList.size(); i++){
            List<String> mutatedInputList = new ArrayList<>(inputList);
            if(inputList.get(i).split(" ")[0].equals("nop")){
                mutatedInputList.set(i, "jmp " + inputList.get(i).split(" ")[1]);
            } else if(inputList.get(i).split(" ")[0].equals("jmp")) {
                mutatedInputList.set(i, "nop " + inputList.get(i).split(" ")[1]);
            } else {
                continue;
            }
            int sum = 0;
            int instr = 0;
            int t = 0;
            while(instr < mutatedInputList.size() && t < 1000){
                t++;
                String[] arr = mutatedInputList.get(instr).split(" ");
                String instruction = arr[0];
                int arg = Integer.parseInt(arr[1]);
                switch(instruction){
                    case "acc":
                        sum += arg;
                        instr += 1;
                        break;
                    case "jmp":
                        instr += arg;
                        break;
                    case "nop":
                        instr += 1;
                        break;
                }
            }
            if(instr == mutatedInputList.size()){
                System.out.println("Puzzle 2: " + sum);
            }
        }
    }

    private static void puzzle1(List<String> inputList){
        Set<Integer> executed = new HashSet<>();
        int sum = 0;
        int instr = 0;
        while(!executed.contains(instr)){
            executed.add(instr);
            String[] arr = inputList.get(instr).split(" ");
            String instruction = arr[0];
            int arg = Integer.parseInt(arr[1]);
            switch(instruction){
                case "acc":
                    sum += arg;
                    instr += 1;
                    break;
                case "jmp":
                    instr += arg;
                    break;
                case "nop":
                    instr += 1;
                    break;
            }
        }
        System.out.println("Puzzle 1: " + sum);
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
