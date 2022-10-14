package pieces;

import moveBehavior.normalValidationTypes.MoveValidatorType;

public class Knight extends ChessPiece{
    public Knight(int playerID, MoveValidatorType moveValidatorType) {
        super(playerID , moveValidatorType);
    }
    public String getPieceName() {
        return "Knight";
    }
}
