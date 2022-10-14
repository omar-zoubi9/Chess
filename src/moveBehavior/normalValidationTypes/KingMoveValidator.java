package moveBehavior.normalValidationTypes;

import inputManaging.Move.Move;
import pieces.King;
import moveBehavior.wayChecking.WayCheckingAlgo;

public class KingMoveValidator implements MoveValidatorType {
    WayCheckingAlgo wayChecker = WayCheckingAlgo.getInstance();
    public static KingMoveValidator kingMoveValidator = null;
    private KingMoveValidator(){}
    public static KingMoveValidator getInstance(){
        if(kingMoveValidator == null)
            kingMoveValidator = new KingMoveValidator();
        return kingMoveValidator;
    }
    @Override
    public boolean isValid(Move move) {
        wayChecker.setSquares(move);
        King currentKing = (King) wayChecker.getPieceToBeMoved();
        if(wayChecker.isDestOneSquareAway()){
            currentKing.setMovedToTrue();
            return true;
        }
        return false;
    }
}
