package game;

import pieces.factory.PiecesFactory;
import game.board.Board;
import inputManaging.Move.SquareId;

public class GameInitializer {
    PiecesFactory piecesFactory = new PiecesFactory();
    private void initializeBlackPawnsForNewGame(Board board){
        SquareId pieceSquareId = new SquareId();
        pieceSquareId.setYCoordinate(6);
        for(int col=0 ; col <= 7 ; col++){
            pieceSquareId.setXCoordinate(col);
            board.setChessPiece(piecesFactory.makeBlackPawn() , pieceSquareId);
        }
    }
    private void initializeWhitePawnsForNewGame(Board board){
        SquareId pieceSquareId = new SquareId();
        pieceSquareId.setYCoordinate(1);
        for(int col=0 ; col <= 7 ; col++){
            pieceSquareId.setXCoordinate(col);
            board.setChessPiece(piecesFactory.makeWhitePawn() , pieceSquareId);
        }
    }
    private void initializeWhitePiecesForNewGame(Board board){
        initializeWhitePawnsForNewGame(board);
        SquareId kingSquareID = new SquareId();
        SquareId pieceSquareId = new SquareId();

        pieceSquareId.setYCoordinate(0);
        pieceSquareId.setXCoordinate(0);
        board.setChessPiece(piecesFactory.makeWhiteRook(), pieceSquareId);
        pieceSquareId.setXCoordinate(7);
        board.setChessPiece(piecesFactory.makeWhiteRook(), pieceSquareId);
        pieceSquareId.setXCoordinate(1);
        board.setChessPiece(piecesFactory.makeWhiteKnight(), pieceSquareId);
        pieceSquareId.setXCoordinate(6);
        board.setChessPiece(piecesFactory.makeWhiteKnight(), pieceSquareId);
        pieceSquareId.setXCoordinate(2);
        board.setChessPiece(piecesFactory.makeWhiteBishop(), pieceSquareId);
        pieceSquareId.setXCoordinate(5);
        board.setChessPiece(piecesFactory.makeWhiteBishop(), pieceSquareId);
        pieceSquareId.setXCoordinate(3);
        board.setChessPiece(piecesFactory.makeWhiteQueen(), pieceSquareId);
        pieceSquareId.setXCoordinate(4);
        board.setChessPiece(piecesFactory.makeWhiteKing(), pieceSquareId);
        kingSquareID.setYCoordinate(0);
        kingSquareID.setXCoordinate(4);
    }
    private void initializeBlackPiecesForNewGame(Board board){
        initializeBlackPawnsForNewGame(board);
        SquareId kingSquareID = new SquareId();
        SquareId pieceSquareId = new SquareId();

        pieceSquareId.setYCoordinate(7);
        pieceSquareId.setXCoordinate(0);
        board.setChessPiece(piecesFactory.makeBlackRook() , pieceSquareId);
        pieceSquareId.setXCoordinate(7);
        board.setChessPiece(piecesFactory.makeBlackRook() , pieceSquareId);
        pieceSquareId.setXCoordinate(1);
        board.setChessPiece(piecesFactory.makeBlackKnight() , pieceSquareId);
        pieceSquareId.setXCoordinate(6);
        board.setChessPiece(piecesFactory.makeBlackKnight() , pieceSquareId);
        pieceSquareId.setXCoordinate(2);
        board.setChessPiece(piecesFactory.makeBlackBishop() , pieceSquareId);
        pieceSquareId.setXCoordinate(5);
        board.setChessPiece(piecesFactory.makeBlackBishop() , pieceSquareId);
        pieceSquareId.setXCoordinate(3);
        board.setChessPiece(piecesFactory.makeBlackQueen() , pieceSquareId);
        pieceSquareId.setXCoordinate(4);
        board.setChessPiece(piecesFactory.makeBlackKing() , pieceSquareId);
        kingSquareID.setYCoordinate(7);
        kingSquareID.setXCoordinate(4);
    }
    public void initializeBoardForNewGame(Board board){
        initializeWhitePiecesForNewGame(board);
        initializeBlackPiecesForNewGame(board);
    }
}
