package moveBehavior.specialMoveBehavior;

import game.board.Board;
import inputManaging.Move.Move;
import inputManaging.Move.SquareId;
import moveBehavior.wayChecking.WayCheckingAlgo;
import moveBehavior.validationChain.IValidationChain;
import pieces.ChessPiece;
import pieces.King;
import pieces.Rook;

public class CastlingValidator implements IValidationChain {
    public static CastlingValidator castlingValidator = null;
    private CastlingValidator(){}
    public static CastlingValidator getInstance(){
        if(castlingValidator == null)
            castlingValidator = new CastlingValidator();
        return castlingValidator;
    }
    private IValidationChain nextInChain;

    @Override
    public boolean isValid(Move move){
        Board board = Board.getInstance();
        WayCheckingAlgo wayChecker = WayCheckingAlgo.getInstance();
        if(board.squareHasAPiece(move.getDestSquareId()) && nextInChain == null)
            return false;
        if(board.squareHasAPiece(move.getDestSquareId()))
            return nextInChain.isValid(move);
        ChessPiece pieceToCheck = board.getSquare(move.getSrcSquareId()).getPiece();
        SquareId rookSrcSquare = new SquareId();
        SquareId rookDestSquare = new SquareId();

        if(move.isPlayer1Turn() && pieceToCheck instanceof King && !((King) pieceToCheck).isMoved()){
            if(wayChecker.isDestTwoMovesAwayOnRight() && wayChecker.isDestinationFree() && board.getSquare(0,7).getPiece() instanceof Rook
                    && !((Rook) board.getSquare(0,7).getPiece()).isMoved()){
                rookSrcSquare.setYCoordinate(0);
                rookSrcSquare.setXCoordinate(7);
                rookDestSquare.setYCoordinate(0);
                rookDestSquare.setXCoordinate(5);
                board.movePieceFromSquare(rookSrcSquare , rookDestSquare);
                return true;
            }else if(wayChecker.isDestTwoMovesAwayOnLeft() && board.getSquare(0,3).getPiece() == null && board.getSquare(0,0).getPiece() instanceof Rook
                    && !((Rook) board.getSquare(0,0).getPiece()).isMoved()){
                rookSrcSquare.setYCoordinate(0);
                rookSrcSquare.setXCoordinate(0);
                rookDestSquare.setYCoordinate(0);
                rookDestSquare.setXCoordinate(3);

                board.movePieceFromSquare(rookSrcSquare , rookDestSquare);
                return true;
            }
        }else if(move.isPlayer2Turn() && pieceToCheck instanceof King && !((King) pieceToCheck).isMoved()){
            if(wayChecker.isDestTwoMovesAwayOnRight() && board.getSquare(7,5).getPiece() == null && board.getSquare(7,7).getPiece() instanceof Rook
                    && !((Rook) board.getSquare(7,7).getPiece()).isMoved()){
                rookSrcSquare.setYCoordinate(7);
                rookSrcSquare.setXCoordinate(7);
                rookDestSquare.setYCoordinate(7);
                rookDestSquare.setXCoordinate(5);

                board.movePieceFromSquare(rookSrcSquare , rookDestSquare);
                return true;
            }else if(wayChecker.isDestTwoMovesAwayOnLeft() && board.getSquare(7,3).getPiece() == null && board.getSquare(7,0).getPiece() instanceof Rook
                    && !((Rook) board.getSquare(0,0).getPiece()).isMoved()){
                rookSrcSquare.setYCoordinate(7);
                rookSrcSquare.setXCoordinate(0);
                rookDestSquare.setYCoordinate(7);
                rookDestSquare.setXCoordinate(3);

                board.movePieceFromSquare(rookSrcSquare , rookDestSquare);
                return true;
            }
        }
        if(nextInChain != null){
            return nextInChain.isValid(move);
        }
        return false;
    }

    @Override
    public void setNextInChain(IValidationChain nextInChain) {
        this.nextInChain = nextInChain;
    }
}
