package moveBehavior.specialMoveBehavior;

import pieces.factory.PiecesFactory;
import game.GameManager;
import game.board.Board;
import game.help.Helper;
import inputManaging.Move.Move;
import moveBehavior.normalValidationTypes.BlackPawnMoveValidator;
import moveBehavior.normalValidationTypes.WhitePawnMoveValidator;
import moveBehavior.wayChecking.WayCheckingAlgo;
import moveBehavior.validationChain.IValidationChain;
import pieces.ChessPiece;

import java.util.Scanner;

public class PawnPromotionValidator implements IValidationChain {
    Helper helper = new Helper();
    public static PawnPromotionValidator pawnPromotionValidator = null;
    private PawnPromotionValidator(){}
    public static PawnPromotionValidator getInstance(){
        if(pawnPromotionValidator == null)
            pawnPromotionValidator = new PawnPromotionValidator();
        return pawnPromotionValidator;
    }
    private IValidationChain nextInChain;
    public void promotePawn(Move move){
        GameManager gameManager = GameManager.getInstance();
        Board board = Board.getInstance();
        PiecesFactory piecesFactory = new PiecesFactory();
        ChessPiece chessPiece;
        helper.printPawnPromotionString();
        Scanner scanner = new Scanner(System.in);
        String pieceName = scanner.nextLine();
        if(!helper.isValidPromotionPiece(pieceName)){
            System.out.println("please enter a valid piece here are the valid pieces");
            helper.printPromotionPieces();
            promotePawn(move);
        }else{
            chessPiece = piecesFactory.makeChessPiece( pieceName , move.getPlayerId());
            board.setChessPiece(chessPiece , move.getSrcSquareId());
        }
    }
    @Override
    public boolean isValid(Move move){
        WayCheckingAlgo wayChecker = WayCheckingAlgo.getInstance();
        if(move.isPlayer1Turn() && wayChecker.isDestOnTopEnd()){
            WhitePawnMoveValidator whitePawnMoveValidator = WhitePawnMoveValidator.getInstance();
            if(whitePawnMoveValidator.isValid(move)){
                helper.printPawnPromotionString();
                promotePawn(move);
                return true;
            }
        }else if(move.isPlayer2Turn() && wayChecker.isDestOnBotEnd()){
            BlackPawnMoveValidator blackPawnMoveChecker = BlackPawnMoveValidator.getInstance();
            if(blackPawnMoveChecker.isValid(move)){
                helper.printPawnPromotionString();
                promotePawn(move);
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
