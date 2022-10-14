package pieces;

import moveBehavior.normalValidationTypes.MoveValidatorType;

public class King extends ChessPiece{
    boolean moved = false;
    public King(int playerID, MoveValidatorType moveValidatorType) {
        super(playerID , moveValidatorType);
    }
    public boolean isMoved(){
        return moved;
    }
    public void setMovedToTrue() {
        moved = true;
    }
    public String getPieceName() {
        return "King";
    }
}
