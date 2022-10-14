package moveBehavior.normalValidationTypes;

import inputManaging.Move.Move;
import moveBehavior.wayChecking.WayCheckingAlgo;

public class BishopMoveValidator implements MoveValidatorType {
    public static BishopMoveValidator bishopMoveValidator = null;
    WayCheckingAlgo wayChecker = WayCheckingAlgo.getInstance();
    private BishopMoveValidator(){}
    public static MoveValidatorType getInstance(){
        if(bishopMoveValidator == null)
            bishopMoveValidator = new BishopMoveValidator();
        return  bishopMoveValidator;
    }
    @Override
    public boolean isValid(Move move) {
        wayChecker.setSquares(move);
        if (wayChecker.isDestinationOnDiagonalTopRight()) {
            wayChecker.setWayCheckingDirectionToDiagonalTopRight();
        } else if (wayChecker.isDestinationOnDiagonalTopLeft()) {
            wayChecker.setWayCheckingDirectionToDiagonalTopLeft();
        } else if (wayChecker.isDestinationOnDiagonalBotRight()) {
            wayChecker.setWayCheckingDirectionToDiagonalBotRight();
        } else if (wayChecker.isDestinationOnDiagonalBotLeft()) {
            wayChecker.setWayCheckingDirectionToDiagonalBotLeft();
        } else {
            return false;
        }
        return wayChecker.isWayFreeTillDestination();
    }

}
