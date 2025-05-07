package chess.board;

/** Java */
import java.util.ArrayList;
import java.util.List;

import chess.Move;
/** Chess */
import chess.pieces.Piece;

/**
 * Class for validating moves
 */
public class MoveValidator {
    private ChessBoard chessBoard;

    public MoveValidator(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    /** Validation functions */

    // TODO: Use Move class for this, not coordiantes

    private boolean hasPositionChanged(int oldX, int oldY, int newX, int newY) {
        return oldX != newX || oldY != newY;
    }

    private boolean isTargetOpponentPiece(int oldX, int oldY, int newX, int newY) {
        Field[][] board = chessBoard.getBoard();

        Piece source = board[oldX][oldY].getPiece();
        Piece target = board[newX][newY].getPiece();

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
    public boolean validate(int oldX, int oldY, int newX, int newY) {
        return hasPositionChanged(oldX, oldY, newX, newY)
                && isTargetOpponentPiece(oldX, oldY, newX, newY);
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

        List<Move> pieceMoves = piece.getMoves(x, y, chessBoard);

        return pieceMoves;
    }
}
