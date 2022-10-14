package pieces.factory;

import pieces.*;

public interface IPiecesFactory {
    public ChessPiece makeWhiteChessPiece(String pieceType);
    public ChessPiece makeBlackChessPiece(String pieceType);
    Bishop makeWhiteBishop();
    Bishop makeBlackBishop();
    King makeWhiteKing();
    King makeBlackKing();
    Knight makeWhiteKnight();
    Knight makeBlackKnight();
    Pawn makeWhitePawn();
    Pawn makeBlackPawn();
    Queen makeWhiteQueen();
    Queen makeBlackQueen();
    Rook makeWhiteRook();
    Rook makeBlackRook();
}
