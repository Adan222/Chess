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
