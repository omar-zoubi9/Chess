package inputManaging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class InputManager {
    private String takeName(int id){
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter player" + id +" name: ");
        return scanner.nextLine();
    }
    public String takePlayer1Name(){
        return takeName(1);
    }
    public String takePlayer2Name(){
        return takeName(2);
    }
    public String[] takeInputCommand(String turnForPlayerWithName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(turnForPlayerWithName + " please enter a command: ");
        String[] inputCommand = (String[]) bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
        for (int i = 1; i < inputCommand.length ; i++) {
            inputCommand[i] = inputCommand[i].toUpperCase();
        }
        return inputCommand;
    }
}
