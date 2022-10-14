package moveBehavior.normalValidationTypes;

import inputManaging.Move.Move;
import moveBehavior.wayChecking.WayCheckingAlgo;

public class QueenMoveValidator implements MoveValidatorType {
    WayCheckingAlgo wayChecker = WayCheckingAlgo.getInstance();
    public static QueenMoveValidator queenMoveValidator = null;
    private QueenMoveValidator(){}
    public static QueenMoveValidator getInstance(){
        if(queenMoveValidator == null)
            queenMoveValidator = new QueenMoveValidator();
        return queenMoveValidator;
    }
    @Override
    public boolean isValid(Move move) {
        wayChecker.setSquares(move);
        if (wayChecker.isDestinationOnTop()) {
            wayChecker.setWayCheckingDirectionToTop();
        } else if (wayChecker.isDestinationOnBot()) {
            wayChecker.setWayCheckingDirectionToBot();
        } else if (wayChecker.isDestinationOnRight()) {
            wayChecker.setWayCheckingDirectionToRight();
        } else if (wayChecker.isDestinationOnLeft()) {
            wayChecker.setWayCheckingDirectionToLeft();
        } else if (wayChecker.isDestinationOnDiagonalTopRight()) {
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
