package chess.board;

/** Java */
import java.util.ArrayList;
import java.util.List;

/** Chess */
import chess.pieces.Piece;
import chess.Move;
import chess.MoveHistory;

/**
 * Class for validating moves
 */
public class MoveValidator {
    private ChessBoard chessBoard;
    private MoveHistory moveHistory;

    public MoveValidator(ChessBoard chessBoard, MoveHistory moveHistory) {
        this.chessBoard = chessBoard;
        this.moveHistory = moveHistory;
    }

    /** Validation functions */

    private boolean hasPositionChanged(Move move) {
        return move.sourceX != move.destinationX || move.sourceY != move.destinationY;
    }

    private boolean isTargetOpponentPiece(Move move) {
        Field[][] board = chessBoard.getBoard();

        Piece source = board[move.sourceX][move.sourceY].getPiece();
        Piece target = board[move.destinationX][move.destinationY].getPiece();

        // Return true if the target is not null and is of a different color
        return target == null || source.getColor() != target.getColor();
    }

    /**
     * Validate move
     * 
     * @param oldX
     * @param oldY
     * @param newX
     * @param newY
     * @return
     */
    public boolean validate(Move move) {
        return hasPositionChanged(move)
                && isTargetOpponentPiece(move);
    }

    /**
     * Get all valid moves for piece under given coordinates
     * 
     * @param x
     * @param y
     * @return
     */
    public List<Move> getMovesFor(int x, int y) {
        Field[][] board = chessBoard.getBoard();
        Piece piece = board[x][y].getPiece();

        // If piece is null, then there is no moves available
        if (piece == null)
            return new ArrayList<Move>();

        List<Move> pieceMoves = piece.getMoves(x, y, chessBoard, moveHistory);

        return pieceMoves;
    }
}
