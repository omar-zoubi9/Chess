package pieces;

import moveBehavior.normalValidationTypes.MoveValidatorType;

public class Queen extends ChessPiece{
    public Queen(int playerID, MoveValidatorType moveValidatorType) {
        super(playerID , moveValidatorType);
    }
    public String getPieceName() {
        return "Queen";
    }
}
