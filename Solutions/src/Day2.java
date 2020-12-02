import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException{
        List<Password> inputList = parseInput("Solutions/inputs/day2.txt");
        puzzle1(inputList);
        puzzle2(inputList);
    }

    public static List<Password> parseInput(String str) throws FileNotFoundException {
        List<Password> list = new ArrayList<>();
        File inputFile = new File(str);
        Scanner scanner = new Scanner(inputFile);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] elements = line.split(" ");
            String[] numbers = elements[0].split("-");
            int min = Integer.parseInt(numbers[0]);
            int max = Integer.parseInt(numbers[1]);
            list.add(new Password(min, max, elements[1].charAt(0), elements[2]));
        }
        return list;
    }

    public static void puzzle1(List<Password> inputList){
        int count = 0;
        for (Password pswd: inputList) {
            if(pswd.meetReq1()) {
                count++;
            }
        }
        System.out.println("Puzzle 1: " + count);
    }

    public static void puzzle2(List<Password> inputList) {
        int count = 0;
        for (Password pswd: inputList) {
            if(pswd.meetReq2()) {
                count++;
            }
        }
        System.out.println("Puzzle 2: " + count);
    }

    public static class Password {
        public int min, max;
        public char req;
        public String pswd;

        public Password(int min, int max, char req, String pswd){
            this.min = min;
            this.max = max;
            this.req = req;
            this.pswd = pswd;
        }

        public boolean meetReq1(){
            int count = 0;
            for(int i = 0; i < pswd.length(); i++){
                if(pswd.charAt(i) == req){
                    count++;
                    if(count > max){
                        return false;
                    }
                }
            }
            return count >= min;
        }

        public boolean meetReq2(){
            int count = 0;
            if(pswd.charAt(min-1) == req){
                count++;
            }
            if(pswd.charAt(max-1) == req){
                count++;
            }
            return count == 1;
        }
    }
}
