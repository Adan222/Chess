package chess;

/** Java */
import java.util.List;

/** Chess */
import chess.board.ChessBoard;
import chess.board.MoveValidator;
import chess.ui.ChessFrame;

public class Game {
    private ChessFrame chessFrame;
    private ChessBoard chessBoard;

    public Game() {
        initComponents();
    }

    private void initComponents() {
        chessFrame = new ChessFrame(this);
        chessBoard = new ChessBoard();
    }

    public void run() {
        chessFrame.setVisible(true);
    }

    public void makeMove(int oldX, int oldY, int newX, int newY) {
        // Validate move
        if (true) {
            chessBoard.makeMove(oldX, oldY, newX, newY);
            chessFrame.makeMove(oldX, oldY, newX, newY);
        }
    }

    /**
     * Get list of all available moves
     * 
     * @return
     */
    public List<Move> getAvailableMoves(int x, int y) {
        MoveValidator validator = new MoveValidator(chessBoard);
        return validator.getMovesFor(x, y);
    }
}
