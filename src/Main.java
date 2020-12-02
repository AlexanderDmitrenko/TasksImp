import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        String filePath = "inputTask_1.txt";
        int number;


        while (true) {
            System.out.println("Enter integer N");
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                number = Integer.parseInt(reader.readLine());
                break;
            }catch (NumberFormatException | NullPointerException nfe){
                System.out.println("data is not correct!");
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        CorrectBracketExp correctBracketExp = new CorrectBracketExp();
        try {
            correctBracketExp.loadLines(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load data!. Check the file path is correct");
        }
        correctBracketExp.setNumber(number);
        correctBracketExp.run();


        System.out.println("_________________________________________________________________________________________");


        SumDigitsFact sumDigitsFact = new SumDigitsFact();
        sumDigitsFact.run();


        System.out.println("_________________________________________________________________________________________");



        Dijkstra dijkstra = new Dijkstra();
        dijkstra.loadData();
        dijkstra.run();


    }

}
