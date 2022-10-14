package moveBehavior.normalValidationTypes;

import inputManaging.Move.Move;
import pieces.Pawn;
import moveBehavior.wayChecking.WayCheckingAlgo;

public class WhitePawnMoveValidator implements MoveValidatorType {
    WayCheckingAlgo wayChecker = WayCheckingAlgo.getInstance();
    public static WhitePawnMoveValidator whitePawnMoveValidator = null;
    private WhitePawnMoveValidator(){}
    public static WhitePawnMoveValidator getInstance(){
        if(whitePawnMoveValidator == null)
            whitePawnMoveValidator = new WhitePawnMoveValidator();
        return whitePawnMoveValidator;
    }
    @Override
    public boolean isValid(Move move) {
        Pawn currentPawn = (Pawn) wayChecker.getPieceToBeMoved();
        if ( wayChecker.isDestOneMoveAwayOnTop() &&
                !wayChecker.isThereAnEnemyOnDestination()) {
            currentPawn.setLastMoveWasDoubleMove(false);
            currentPawn.setMovedToTrue();
            return true;
        }else if(wayChecker.isDestTwoMovesAwayOnTop() && !currentPawn.isMoved() &&
                    !wayChecker.isThereAnEnemyOnDestination() ) {
            currentPawn.setLastMoveWasDoubleMove(true);
            currentPawn.setMovedToTrue();
            return true;
        } else if ( (wayChecker.isDestOneMoveAwayOnTopRight() || wayChecker.isDestOneMoveAwayOnTopLeft())
                && wayChecker.isThereAnEnemyOnDestination() ) {
            currentPawn.setLastMoveWasDoubleMove(false);
            currentPawn.setMovedToTrue();
            return true;
        }
        return false;
    }
}
