package moveBehavior.wayChecking;

import game.board.Board;
import inputManaging.Move.Move;
import inputManaging.Move.SquareId;
import pieces.ChessPiece;
import pieces.Pawn;

public class WayCheckingAlgo {
    WayCheckingDirection wayCheckingDirection = new WayCheckingDirection();
    public static WayCheckingAlgo wayChecker = null;
    private WayCheckingAlgo(){}
    public static  WayCheckingAlgo getInstance(){
        if(wayChecker == null)
            wayChecker = new WayCheckingAlgo();
        return wayChecker;
    }
    private Board board;
    private SquareId srcSquareId = new SquareId();
    private SquareId destSquareId = new SquareId();
    public void setSquares(Move move) {
        setSrcSquareId(move.getSrcSquareId());
        setDestSquareId(move.getDestSquareId());
    }

    public void setSrcSquareId(SquareId srcSquareId) {
        this.srcSquareId.setYCoordinate(srcSquareId.getYCoordinate());
        this.srcSquareId.setXCoordinate(srcSquareId.getXCoordinate());
    }
    public void setDestSquareId(SquareId destSquareId) {
        this.destSquareId.setYCoordinate(destSquareId.getYCoordinate());
        this.destSquareId.setXCoordinate(destSquareId.getXCoordinate());
    }
    private int getSquaresToCheckCount(){
        if(isDestinationOnTop()){
            return destSquareId.getYCoordinate() - srcSquareId.getYCoordinate();
        }else if(isDestinationOnBot()){
            return srcSquareId.getYCoordinate() - destSquareId.getYCoordinate();
        }else if(isDestinationOnRight()){
            return destSquareId.getXCoordinate() - srcSquareId.getXCoordinate();
        }else if(isDestinationOnLeft()){
            return srcSquareId.getXCoordinate() - destSquareId.getXCoordinate();
        }
        return 0;
    }
    public boolean isWayFreeTillDestination() {
        int squaresToCheckCount = getSquaresToCheckCount();
        for(int i=0; i < squaresToCheckCount ; i++){
            srcSquareId.setYCoordinate(srcSquareId.getXCoordinate() + wayCheckingDirection.getDx());
            srcSquareId.setYCoordinate(srcSquareId.getYCoordinate() + wayCheckingDirection.getDy());

            if(board.squareHasAPiece(srcSquareId)){
                System.out.println("there is a piece that prevents you from moving to the destination on square "
                        + srcSquareId.getYCoordinate() + (srcSquareId.getXCoordinate()+'A') );
                return false;
            }
        }
        return true;
    }
    public boolean isDestOneSquareAway() {
        if(srcSquareId.getXCoordinate()+1 == destSquareId.getXCoordinate() && srcSquareId.getYCoordinate() == destSquareId.getYCoordinate()){
            return true;
        }else if(srcSquareId.getXCoordinate() == destSquareId.getXCoordinate() && srcSquareId.getYCoordinate()+1 == destSquareId.getYCoordinate()){
            return true;
        }else if(srcSquareId.getXCoordinate()-1 == destSquareId.getXCoordinate() && srcSquareId.getYCoordinate() == destSquareId.getYCoordinate()){
            return true;
        }else if(srcSquareId.getXCoordinate() == destSquareId.getXCoordinate() && srcSquareId.getYCoordinate()-1 == destSquareId.getYCoordinate()){
            return true;
        }else if(srcSquareId.getXCoordinate()+1 == destSquareId.getXCoordinate() && srcSquareId.getYCoordinate()+1 == destSquareId.getYCoordinate()){
            return true;
        }else if(srcSquareId.getXCoordinate()+1 == destSquareId.getXCoordinate() && srcSquareId.getYCoordinate()-1 == destSquareId.getYCoordinate()){
            return true;
        }else if(srcSquareId.getXCoordinate()-1 == destSquareId.getXCoordinate() && srcSquareId.getYCoordinate()+1 == destSquareId.getYCoordinate()){
            return true;
        }else if(srcSquareId.getXCoordinate()-1 == destSquareId.getXCoordinate() && srcSquareId.getYCoordinate()-1 == destSquareId.getYCoordinate()){
            return true;
        }
        return false;
    }
    public boolean isDestinationOnTop(){
        return srcSquareId.getYCoordinate() < destSquareId.getYCoordinate();
    }
    public boolean isDestinationOnBot(){
        return srcSquareId.getYCoordinate() > destSquareId.getYCoordinate();
    }
    public boolean isDestinationOnRight(){
        return srcSquareId.getXCoordinate() < destSquareId.getXCoordinate();
    }
    public boolean isDestinationOnLeft(){
        return srcSquareId.getXCoordinate() > destSquareId.getXCoordinate();
    }
    private boolean isOnFrontSlashDiagonal(){
        return srcSquareId.getXCoordinate() - srcSquareId.getYCoordinate() == destSquareId.getXCoordinate() - destSquareId.getYCoordinate();
    }
    public boolean isDestinationOnDiagonalTopRight(){
        return srcSquareId.getYCoordinate() < destSquareId.getYCoordinate() && srcSquareId.getXCoordinate() < destSquareId.getXCoordinate()
                && isOnFrontSlashDiagonal();
    }
    public boolean isDestinationOnDiagonalBotLeft(){
        return srcSquareId.getYCoordinate() > destSquareId.getYCoordinate() && srcSquareId.getXCoordinate() > destSquareId.getXCoordinate()
                && isOnFrontSlashDiagonal();
    }
    private boolean isOnBackSlashDiagonal(){
        return srcSquareId.getXCoordinate() + srcSquareId.getYCoordinate() == destSquareId.getXCoordinate() + destSquareId.getYCoordinate();
    }
    public boolean isDestinationOnDiagonalTopLeft(){
        return srcSquareId.getYCoordinate() < destSquareId.getYCoordinate() && srcSquareId.getXCoordinate() > destSquareId.getXCoordinate()
                && isOnBackSlashDiagonal();
    }
    public boolean isDestinationOnDiagonalBotRight(){
        return srcSquareId.getYCoordinate() > destSquareId.getYCoordinate() && srcSquareId.getXCoordinate() < destSquareId.getXCoordinate()
                && isOnBackSlashDiagonal();
    }
    public void setWayCheckingDirectionToTop() {
        wayCheckingDirection.setDx(0);
        wayCheckingDirection.setDy(1);
    }
    public void setWayCheckingDirectionToBot() {
        wayCheckingDirection.setDx(0);
        wayCheckingDirection.setDy(-1);
    }
    public void setWayCheckingDirectionToRight() {
        wayCheckingDirection.setDx(1);
        wayCheckingDirection.setDy(0);
    }
    public void setWayCheckingDirectionToLeft() {
        wayCheckingDirection.setDx(-1);
        wayCheckingDirection.setDy(0);
    }

    public void setWayCheckingDirectionToDiagonalTopRight() {
        wayCheckingDirection.setDx(1);
        wayCheckingDirection.setDy(1);
    }
    public void setWayCheckingDirectionToDiagonalTopLeft() {
        wayCheckingDirection.setDx(-1);
        wayCheckingDirection.setDy(1);
    }
    public void setWayCheckingDirectionToDiagonalBotRight() {
        wayCheckingDirection.setDx(1);
        wayCheckingDirection.setDy(-1);
    }
    public void setWayCheckingDirectionToDiagonalBotLeft() {
        wayCheckingDirection.setDx(-1);
        wayCheckingDirection.setDy(-1);
    }
    public boolean isThereAnEnemyOnDestination() {
        if(board.getSquare(destSquareId).getPiece() != null){
            return board.getSquare(destSquareId).getPiece().getPlayerID() !=
                    board.getSquare(destSquareId).getPiece().getPlayerID();
        }
        return false;
    }

    public boolean isDestOneMoveAwayOnTopRight(){
        return srcSquareId.getYCoordinate() + 1 == destSquareId.getYCoordinate() &&
                srcSquareId.getXCoordinate() + 1 == destSquareId.getXCoordinate();
    }
    public boolean isDestOneMoveAwayOnTopLeft(){
        return srcSquareId.getYCoordinate() + 1 == destSquareId.getYCoordinate() &&
                srcSquareId.getXCoordinate() - 1 == destSquareId.getXCoordinate();
    }
    public boolean isDestOneMoveAwayOnBotRight(){
        return srcSquareId.getYCoordinate() - 1 == destSquareId.getYCoordinate() &&
                srcSquareId.getXCoordinate() + 1 == destSquareId.getXCoordinate();
    }
    public boolean isDestOneMoveAwayOnBotLeft(){
        return srcSquareId.getYCoordinate() - 1 == destSquareId.getYCoordinate() &&
                srcSquareId.getXCoordinate() - 1 == destSquareId.getXCoordinate();
    }
    public boolean isDestOneMoveAwayOnTop(){
        return srcSquareId.getYCoordinate() + 1 == destSquareId.getYCoordinate()
                && srcSquareId.getXCoordinate() == destSquareId.getXCoordinate();
    }
    public boolean isDestTwoMovesAwayOnTop(){
        return srcSquareId.getYCoordinate() + 2 == destSquareId.getYCoordinate()
                && srcSquareId.getXCoordinate() == destSquareId.getXCoordinate();
    }
    public boolean isDestOneMoveAwayOnBot(){
        return srcSquareId.getYCoordinate() - 1 == destSquareId.getYCoordinate()
                && srcSquareId.getXCoordinate() == destSquareId.getXCoordinate();
    }
    public boolean isDestTwoMovesAwayOnBot(){
        return srcSquareId.getYCoordinate() - 2 == destSquareId.getYCoordinate()
                && srcSquareId.getXCoordinate() == destSquareId.getXCoordinate();
    }
    public boolean isDestTwoMovesAwayOnRight(){
        return srcSquareId.getYCoordinate() == destSquareId.getYCoordinate() && destSquareId.getXCoordinate()- srcSquareId.getXCoordinate() == 2;
    }
    public boolean isDestTwoMovesAwayOnLeft(){
        return srcSquareId.getYCoordinate() == destSquareId.getYCoordinate() && destSquareId.getXCoordinate()- srcSquareId.getXCoordinate() == -2;
    }
    public ChessPiece getPieceToBeMoved(){
        return board.getSquare(srcSquareId).getPiece();
    }
    public boolean isDestinationFree(){
        return board.getSquare(destSquareId) == null;
    }
    public boolean isThereAnEnemyPawnOneMoveAwayOnRight(){
        SquareId squareOnRightId = new SquareId(srcSquareId.getYCoordinate() , srcSquareId.getXCoordinate()+1);
        return board.getSquare(squareOnRightId).getPiece() instanceof Pawn &&
                board.getSquare(destSquareId).getPiece().getPlayerID() !=
                        board.getSquare(squareOnRightId).getPiece().getPlayerID();
    }
    public boolean isThereAnEnemyPawnOneMoveAwayOnLeft(){
        SquareId squareOnLeftId = new SquareId(srcSquareId.getYCoordinate() , srcSquareId.getXCoordinate()+1);
        return board.getSquare(squareOnLeftId).getPiece() instanceof Pawn &&
                board.getSquare(srcSquareId).getPiece().getPlayerID() !=
                        board.getSquare(squareOnLeftId).getPiece().getPlayerID();
    }
    public boolean isDestOnTopEnd(){
        return destSquareId.getYCoordinate() == 7;
    }
    public boolean isDestOnBotEnd(){
        return destSquareId.getYCoordinate() == 0;
    }
}
