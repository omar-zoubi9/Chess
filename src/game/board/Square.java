package game.board;

import pieces.ChessPiece;

public class Square {
    private ChessPiece piece;
    private boolean hasPiece;
    private boolean threatenedByWhite = false;
    private boolean threatenedByBlack = false;
    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }
    public boolean isHasPiece() {
        return hasPiece;
    }
    public void setHasPiece(boolean hasPiece) {
        this.hasPiece = hasPiece;
    }
    public boolean isThreatenedByWhite() {
        return threatenedByWhite;
    }
    public void setThreatenedByWhite(boolean threatenedByWhite) {
        this.threatenedByWhite = threatenedByWhite;
    }
    public boolean isThreatenedByBlack() {
        return threatenedByBlack;
    }
    public void setThreatenedByBlack(boolean threatenedByBlack) {
        this.threatenedByBlack = threatenedByBlack;
    }
    public void setChessPiece(ChessPiece newPiece){
        piece = newPiece;
        hasPiece = true;
    }
    public void removePiece(){
        piece = null;
        hasPiece = false;
    }
    public boolean hasPiece(){
        return hasPiece;
    }
    public ChessPiece getPiece(){
        return piece;
    }
}
