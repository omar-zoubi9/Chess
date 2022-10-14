package inputManaging.Move;

public class Move {
    private int playerId;
    private SquareId srcSquareId = new SquareId();
    private SquareId destSquareId = new SquareId();

    public Move(int playerId, SquareId srcSquareId, SquareId destSquareId) {
        this.playerId = playerId;
        this.srcSquareId = srcSquareId;
        this.destSquareId = destSquareId;
    }

    public int getPlayerId() {
        return playerId;
    }
    public boolean isPlayer1Turn() {
        return playerId == 1;
    }
    public boolean isPlayer2Turn() {
        return playerId == 2;
    }
    public SquareId getSrcSquareId() {
        return srcSquareId;
    }
    public SquareId getDestSquareId() {
        return destSquareId;
    }
}
