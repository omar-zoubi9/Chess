package moveBehavior.normalValidationTypes;

import inputManaging.Move.Move;
import pieces.Pawn;
import moveBehavior.wayChecking.WayCheckingAlgo;

public class BlackPawnMoveValidator implements MoveValidatorType {
    WayCheckingAlgo wayChecker = WayCheckingAlgo.getInstance();
    public static BlackPawnMoveValidator blackPawnMoveValidator = null;
    private BlackPawnMoveValidator(){}
    public static BlackPawnMoveValidator getInstance(){
        if(blackPawnMoveValidator == null)
            blackPawnMoveValidator = new BlackPawnMoveValidator();
        return  blackPawnMoveValidator;
    }

    @Override
    public boolean isValid(Move move) {
        wayChecker.setSquares(move);
        Pawn currentPawn = (Pawn) wayChecker.getPieceToBeMoved();
        if ( wayChecker.isDestOneMoveAwayOnBot() &&
                !wayChecker.isThereAnEnemyOnDestination()) {
            currentPawn.setLastMoveWasDoubleMove(false);
            currentPawn.setMovedToTrue();
            return true;
        }else if(wayChecker.isDestTwoMovesAwayOnBot() && !currentPawn.isMoved() &&
                !wayChecker.isThereAnEnemyOnDestination() ) {
            currentPawn.setLastMoveWasDoubleMove(true);
            currentPawn.setMovedToTrue();
            return true;
        } else if ( (wayChecker.isDestOneMoveAwayOnBotRight() || wayChecker.isDestOneMoveAwayOnBotLeft())
                && wayChecker.isThereAnEnemyOnDestination() ) {
            currentPawn.setLastMoveWasDoubleMove(false);
            currentPawn.setMovedToTrue();
            return true;
        }
        return false;
    }
}
