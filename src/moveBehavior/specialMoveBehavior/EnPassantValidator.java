package moveBehavior.specialMoveBehavior;

import game.GameManager;
import game.board.Board;
import inputManaging.Move.Move;
import inputManaging.Move.SquareId;
import moveBehavior.wayChecking.WayCheckingAlgo;
import moveBehavior.validationChain.IValidationChain;
import pieces.ChessPiece;
import pieces.Pawn;

public class EnPassantValidator implements IValidationChain {
    public static EnPassantValidator enPassantValidator = null;
    private IValidationChain nextInChain;
    private EnPassantValidator(){}
    public static EnPassantValidator getInstance(){
        if(enPassantValidator == null)
            enPassantValidator = new EnPassantValidator();
        return enPassantValidator;
    }

    private boolean isEnemyPawnEligibleForEnPassant(Pawn pawnToCheck){
        return pawnToCheck.isLastMoveADoubleMove();
    }
    @Override
    public boolean isValid(Move move){
        SquareId enemySquare = new SquareId();
        GameManager gameManager = GameManager.getInstance();
        Board board = Board.getInstance();
        WayCheckingAlgo wayChecker = WayCheckingAlgo.getInstance();
        ChessPiece pieceToCheck = board.getSquare(move.getSrcSquareId()).getPiece();
        if(move.isPlayer1Turn() && pieceToCheck instanceof Pawn){
            if(wayChecker.isDestOneMoveAwayOnTopRight() && wayChecker.isThereAnEnemyPawnOneMoveAwayOnRight()){
                enemySquare.setYCoordinate(move.getSrcSquareId().getYCoordinate());
                enemySquare.setXCoordinate(move.getSrcSquareId().getXCoordinate()+1);
                if(isEnemyPawnEligibleForEnPassant((Pawn) wayChecker.getPieceToBeMoved()))
                    board.removePiece(enemySquare);
                return true;
            }else if(wayChecker.isDestOneMoveAwayOnTopLeft() && wayChecker.isThereAnEnemyPawnOneMoveAwayOnLeft()){
                enemySquare.setYCoordinate(move.getSrcSquareId().getYCoordinate());
                enemySquare.setXCoordinate(move.getSrcSquareId().getXCoordinate()-1);
                if(isEnemyPawnEligibleForEnPassant((Pawn) wayChecker.getPieceToBeMoved()))
                    board.removePiece(enemySquare);
                return true;
            }
        }else if(move.isPlayer2Turn() && pieceToCheck instanceof Pawn){
            if(wayChecker.isDestOneMoveAwayOnBotRight() && wayChecker.isThereAnEnemyPawnOneMoveAwayOnRight()){
                enemySquare.setYCoordinate(move.getSrcSquareId().getYCoordinate());
                enemySquare.setXCoordinate(move.getSrcSquareId().getXCoordinate()+1);
                if(isEnemyPawnEligibleForEnPassant((Pawn) wayChecker.getPieceToBeMoved()))
                    board.removePiece(enemySquare);
                return true;
            }else if(wayChecker.isDestOneMoveAwayOnBotLeft() && wayChecker.isThereAnEnemyPawnOneMoveAwayOnLeft()){
                enemySquare.setYCoordinate(move.getSrcSquareId().getYCoordinate());
                enemySquare.setXCoordinate(move.getSrcSquareId().getXCoordinate()-1);
                if(isEnemyPawnEligibleForEnPassant((Pawn) wayChecker.getPieceToBeMoved()))
                    board.removePiece(enemySquare);
                return true;
            }
        }
        if(nextInChain != null){
            return nextInChain.isValid(move);
        }
        return false;
    }

    @Override
    public void setNextInChain(IValidationChain nextInChain) {
        this.nextInChain = nextInChain;
    }
}
