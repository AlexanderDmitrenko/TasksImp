import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CorrectBracketExp {

    private int number;
    private int countPair;
    private int position;
    private List<String> lines;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCountPair() {
        return countPair;
    }

    public List<String> getLines() {
        return lines;
    }

    public void loadLines(String pathFile) throws IOException {
        Path file = Paths.get(pathFile);
        lines = Files.readAllLines(file);
    }

    //Counts the number of expressions that satisfy the task
    private int searchCountExp() {
        int count = 0;
        for (String line : lines) {
            int findNum = checkExpression(line);
            if (findNum == number) count++;
        }
        return count;
    }

    // Returns 1 if the expression is correct, 0 if not. Counts the number of bracket pairs
    // - if it meets with ")" returns to 0
    // - if it meets with "(", generate the "checkPair" extra method for finding a pair
    private int checkExpression(String line) {
        countPair = 0;
        position = 0;

        while (position < line.length()) {
            char ch = line.charAt(position);
            if (ch == ')') return 0;
            if(checkPair(line) == 0) {
                return 0;
            }else {
                position++;
            }
        }
        return countPair;
    }

    // Returns 1 if the pair is found, else 0
    //  - if the end of the line is reached, it returns to  0, no pair
    //  - if it meets with ")", the pair is found and returns 1
    //  - if it meets with "(", recursively generate itself to find a pair
    private int checkPair(String line) {

        if(++position > line.length()-1) return 0;
        while (true) {
            char ch = line.charAt(position);
            if (ch == '(') {
                if (checkPair(line) == 0) return 0;
                position++;
            }else{
                countPair++;
                return 1;
            }
        }
    }

    public void run(){
        System.out.println("Number of correct parentheses containing " + number +
                " opening and "+ number +" closing brackets = " + searchCountExp());
    }
}
