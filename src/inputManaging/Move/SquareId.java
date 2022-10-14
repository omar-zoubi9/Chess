package inputManaging.Move;

public class SquareId {
    public SquareId(){}
    public SquareId(int yCoordinate, int xCoordinate){
        this.yCoordinate = yCoordinate;
        this.xCoordinate = xCoordinate;
    }
    private int xCoordinate;
    private int yCoordinate;
    public int getYCoordinate() {
        return yCoordinate;
    }
    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }
}
