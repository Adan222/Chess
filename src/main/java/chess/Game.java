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
    private MoveHistory moveHistory;

    public Game() {
        initComponents();
    }

    private void initComponents() {
        chessFrame = new ChessFrame(this);
        chessBoard = new ChessBoard();
        moveHistory = new MoveHistory();
    }

    public void run() {
        chessFrame.setVisible(true);
    }

    public void makeMove(int oldX, int oldY, int newX, int newY) {
        MoveValidator validator = new MoveValidator(chessBoard, moveHistory);

        Move move = validator.validateMove(oldX, oldY, newX, newY);
        if (move != null) {
            chessBoard.makeMove(move);
            chessFrame.getChessPanel().makeMove(move);
            moveHistory.addMove(move);
        }
    }

    /**
     * Get list of all available moves
     * 
     * @return
     */
    public List<Move> getAvailableMoves(int x, int y) {
        MoveValidator validator = new MoveValidator(chessBoard, moveHistory);
        return validator.getMovesFor(x, y);
    }
}
