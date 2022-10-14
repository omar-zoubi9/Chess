import game.CommandManager;
import game.GameInitializer;
import game.GameManager;
import game.board.Board;
import inputManaging.CommandChecker;
import inputManaging.InputManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Board board = Board.getInstance();
        GameInitializer gameInitializer = new GameInitializer();
        gameInitializer.initializeBoardForNewGame(board);
        InputManager inputManager = new InputManager();
        CommandChecker commandChecker = new CommandChecker();
        CommandManager commandManager = new CommandManager();

        GameManager gameManager = GameManager.getInstance();
        gameManager.setPlayer1Name(inputManager.takePlayer1Name());
        gameManager.setPlayer2Name(inputManager.takePlayer2Name());
        boolean gameIsGoing = true;
        while(gameIsGoing){
            System.out.println(board);
            String[] inputCommand = inputManager.takeInputCommand(gameManager.getPlayerNameWhoseTurnIsNow());
            if(inputCommand.length == 1 && inputCommand[0].equalsIgnoreCase("exit")){
                System.out.println("Thanks for playing bye!");
                return;
            }
            boolean commandHasValidFormat = commandChecker.checkCommand(inputCommand);
            if(commandHasValidFormat){
                commandManager.doCommand(inputCommand , gameManager.getPlayerIdWhoseTurnIsNow());
            }
        }
    }
}