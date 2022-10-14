package game.board;

import inputManaging.Move.SquareId;
import pieces.ChessPiece;
import pieces.King;

import static game.GameManager.gameManager;

public class Board {
    private SquareId player1KingSquare;
    private SquareId player2KingSquare;
    private static Board board;
    private static Square[][] squares;
    public static Board getInstance(){
        if(board == null){
            board = new Board();
            squares = new Square[8][8];
            for (int i = 0; i < 8 ; i++) {
                for (int j = 0; j < 8 ; j++) {
                    squares[i][j] = new Square();
                }
            }
        }
        return board;
    }
    public void setPlayer1KingSquareId(SquareId player1KingSquare) {
        this.player1KingSquare.setYCoordinate(player1KingSquare.getYCoordinate());
        this.player1KingSquare.setXCoordinate(player1KingSquare.getXCoordinate());
    }
    public void setPlayer2KingSquareId(SquareId player2KingSquare) {
        this.player2KingSquare.setYCoordinate(player2KingSquare.getYCoordinate());
        this.player2KingSquare.setXCoordinate(player2KingSquare.getXCoordinate());
    }
    public boolean squareHasAPiece(SquareId squareToCheck){
        return squares[squareToCheck.getYCoordinate()][squareToCheck.getXCoordinate()].getPiece() != null;
    }
    public void removePiece(SquareId fromSquare){
        squares[fromSquare.getYCoordinate()][fromSquare.getXCoordinate()].removePiece();
    }
    public void copyPiece(SquareId fromSquare , SquareId toSquare){
        squares[toSquare.getYCoordinate()][toSquare.getXCoordinate()].
                setChessPiece(squares[fromSquare.getYCoordinate()][fromSquare.getXCoordinate()].getPiece());
    }
    public void movePiece(SquareId fromSquare , SquareId toSquare){
        copyPiece(fromSquare , toSquare);
        removePiece(fromSquare);
        if(squares[toSquare.getYCoordinate()][toSquare.getXCoordinate()].getPiece() instanceof King) {
            if (squares[toSquare.getYCoordinate()][toSquare.getXCoordinate()].getPiece().getPlayerID() == 1)
                setPlayer1KingSquareId(toSquare);
            else
                setPlayer2KingSquareId(toSquare);
        }
    }
    public void setChessPiece(ChessPiece chessPiece, SquareId squareId) {
        squares[squareId.getYCoordinate()][squareId.getXCoordinate()].setChessPiece(chessPiece);
    }

    public static Square getSquare(SquareId squareId) {
        return squares[squareId.getYCoordinate()][squareId.getXCoordinate()];
    }

    public static Square getSquare(int y, int x) {
        return squares[y][x];
    }

    public String toString() {
        StringBuilder boardState = new StringBuilder();
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                if(!squares[i][j].hasPiece()){
                    boardState.append("empty   ");
                    continue;
                }
                String pieceName = squares[i][j].getPiece().getPieceName() + squares[i][j].getPiece().getPlayerID();
                boardState.append(pieceName);
                for(int z=0 ; z < (8-pieceName.length()) ; z++)
                        boardState.append(" ");
            }
            boardState.append('\n');
        }
        return boardState.toString();
    }

}
