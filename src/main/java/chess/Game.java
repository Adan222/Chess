package chess;

/** Java */
import java.util.List;

/** Chess */
import chess.board.ChessBoard;
import chess.board.MoveValidator;
import chess.pieces.Piece.Color;
import chess.ui.ChessFrame;

public class Game {
    private ChessFrame chessFrame;
    private ChessBoard chessBoard;
    private MoveHistory moveHistory;

    Color playerTurn;

    public Game() {
        initComponents();
    }

    private void initComponents() {
        chessFrame = new ChessFrame(this);
        chessBoard = new ChessBoard();
        moveHistory = new MoveHistory();

        playerTurn = Color.White;
    }

    public void run() {
        chessFrame.setVisible(true);
    }

    public void makeMove(int oldX, int oldY, int newX, int newY) {
        MoveValidator validator = new MoveValidator(chessBoard, moveHistory, playerTurn);

        Move move = validator.validateMove(oldX, oldY, newX, newY);
        if (move != null) {
            chessBoard.makeMove(move);
            chessFrame.getChessPanel().makeMove(move);
            moveHistory.addMove(move);

            switchTurn();

            if (validator.isCheckMove(move)) {
                System.out.println("CHECK");
                if (validator.isMateMove(move))
                    System.out.println("MATE");
            }
        }
    }

    /**
     * Switch player turns
     */
    public void switchTurn() {
        if (playerTurn == Color.White) {
            playerTurn = Color.Black;
        } else {
            playerTurn = Color.White;
        }
    }

    /**
     * Get list of all available moves
     * 
     * @return
     */
    public List<Move> getAvailableMoves(int x, int y) {
        MoveValidator validator = new MoveValidator(chessBoard, moveHistory, playerTurn);
        return validator.getMovesFor(x, y);
    }
}
