package game;

import java.io.IOException;

public class GameManager {
    int playerIdWhoseTurnIsNow = 1;
    public static GameManager gameManager = null;
    Player player1 = new Player(1);
    Player player2 = new Player(2);
    public void setPlayer1Name(String name){
        player1.setName(name);
    }
    public void setPlayer2Name(String name){
        player2.setName(name);
    }
    public String getPlayer1Name(){
        return player1.getName();
    }
    public String getPlayer2Name(){
        return player2.getName();
    }
    private GameManager(){}
    public static GameManager getInstance(){
        if(gameManager == null){
            gameManager = new GameManager();
        }
        return gameManager;
    }
    public void changeTurn(){
        if(playerIdWhoseTurnIsNow == 1){
            this.playerIdWhoseTurnIsNow = 2;
        }else{
            this.playerIdWhoseTurnIsNow = 1;
        }
    }
    public int getPlayerIdWhoseTurnIsNow(){
        return playerIdWhoseTurnIsNow;
    }
    public String getPlayerNameWhoseTurnIsNow(){
        if(playerIdWhoseTurnIsNow == 1)
            return player1.getName();
        return player2.getName();
    }
}
