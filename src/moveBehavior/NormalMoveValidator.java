package moveBehavior;

import game.board.Board;
import inputManaging.Move.Move;
import moveBehavior.validationChain.IValidationChain;
import pieces.ChessPiece;

public class NormalMoveValidator implements IValidationChain {
    public static NormalMoveValidator normalMoveValidator;
    private IValidationChain nextInChain;
    private NormalMoveValidator(){}
    public static NormalMoveValidator getInstance(){
        if(normalMoveValidator == null)
            normalMoveValidator = new NormalMoveValidator();
        return normalMoveValidator;
    }
    @Override
    public boolean isValid(Move move) {
        Board board = Board.getInstance();
        ChessPiece currentPiece = board.getSquare(move.getSrcSquareId()).getPiece();
        if(currentPiece.moveValidatorType.isValid(move)){
            return true;
        }else if(nextInChain != null){
            return nextInChain.isValid(move);
        }
        System.out.println("invalid move ");
        return false;
    }

    @Override
    public void setNextInChain(IValidationChain nextInChain) {
        this.nextInChain = nextInChain;
    }
}
