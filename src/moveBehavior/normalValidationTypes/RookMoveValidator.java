package moveBehavior.normalValidationTypes;

import inputManaging.Move.Move;
import pieces.Rook;
import moveBehavior.wayChecking.WayCheckingAlgo;

public class RookMoveValidator implements MoveValidatorType {
    WayCheckingAlgo wayChecker = WayCheckingAlgo.getInstance();
    public static RookMoveValidator rookMoveValidator = null;
    private RookMoveValidator(){}
    public static RookMoveValidator getInstance(){
        if(rookMoveValidator == null)
            rookMoveValidator = new RookMoveValidator();
        return rookMoveValidator;
    }
    @Override
    public boolean isValid(Move move) {
        Rook currentRook = (Rook) wayChecker.getPieceToBeMoved();
        if (wayChecker.isDestinationOnTop()) {
            wayChecker.setWayCheckingDirectionToTop();
        } else if (wayChecker.isDestinationOnBot()) {
            wayChecker.setWayCheckingDirectionToBot();
        } else if (wayChecker.isDestinationOnRight()) {
            wayChecker.setWayCheckingDirectionToRight();
        } else if (wayChecker.isDestinationOnLeft()) {
            wayChecker.setWayCheckingDirectionToLeft();
        } else {
            return false;
        }
        if(wayChecker.isWayFreeTillDestination()){
            currentRook.setMovedToTrue();
            return true;
        }
        return false;
    }
}
