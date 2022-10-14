package game;

import game.board.Board;
import game.help.Helper;
import inputManaging.Move.Move;
import inputManaging.Move.SquareId;
import moveBehavior.NormalMoveValidator;
import pieces.ChessPiece;
import moveBehavior.specialMoveBehavior.CastlingValidator;
import moveBehavior.specialMoveBehavior.EnPassantValidator;
import moveBehavior.specialMoveBehavior.PawnPromotionValidator;

public class CommandManager {
    private Move move;
    private final Helper helper;
    private Board board = Board.getInstance();
    private CastlingValidator chain1 = CastlingValidator.getInstance();
    private EnPassantValidator chain2 = EnPassantValidator.getInstance();
    private PawnPromotionValidator chain3 = PawnPromotionValidator.getInstance();
    private NormalMoveValidator chain4 = NormalMoveValidator.getInstance();
    public CommandManager() {
        chain1.setNextInChain(chain2);
        chain2.setNextInChain(chain3);
        chain3.setNextInChain(chain4);
        helper = new Helper();
    }
    private void initializeMove(String[] moveCommand,int playerId){
        SquareId srcSquareId = new SquareId();
        srcSquareId.setYCoordinate(moveCommand[1].charAt(0) - '1');
        srcSquareId.setXCoordinate(moveCommand[1].charAt(1) - 'A');
        SquareId destSquareId = new SquareId();
        destSquareId.setYCoordinate(moveCommand[2].charAt(0) - '1');
        destSquareId.setXCoordinate(moveCommand[2].charAt(1) - 'A');
        move = new Move(playerId , srcSquareId , destSquareId);
    }
    public void doCommand(String[] command, int turnForPlayerWithID){
        GameManager gameManager = GameManager.getInstance();

        if(command.length == 0)
            throw new RuntimeException("command is missing");

        if(command[0].equalsIgnoreCase("help")){
            helper.printHelp();
        }else if(command[0].equalsIgnoreCase("move")){
            if (command.length != 3){
                helper.printHelpForWrongNonValidCommand();
                return;
            }
            initializeMove(command, gameManager.getPlayerIdWhoseTurnIsNow());
            ChessPiece pieceToBeMoved = board.getSquare(move.getSrcSquareId()).getPiece();
            if(pieceToBeMoved == null){
                System.out.println("source square doesn't hava a piece");
            }else if(pieceToBeMoved.getPlayerID() != turnForPlayerWithID){
                System.out.println("you can't move an enemy piece");
            }else if(chain1.isValid(move)){
                board.movePiece(move.getSrcSquareId() , move.getDestSquareId());
                gameManager.changeTurn();
            }
        }else{
            helper.printHelpForWrongNonValidCommand();
        }
    }
}
