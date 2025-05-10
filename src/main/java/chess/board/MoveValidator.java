package chess.board;

/** Java */
import java.util.ArrayList;
import java.util.List;

/** Chess */
import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.pieces.King;
import chess.Move;
import chess.MoveHistory;

/**
 * Class for validating moves
 */
public class MoveValidator {
    private ChessBoard chessBoard;
    private MoveHistory moveHistory;

    Color playerTurn;

    public MoveValidator(ChessBoard chessBoard, MoveHistory moveHistory, Color playerTurn) {
        this.chessBoard = chessBoard;
        this.moveHistory = moveHistory;

        this.playerTurn = playerTurn;
    }

    /** Validation functions */

    private boolean hasPositionChanged(Move move) {
        return (move.getSourceX() != move.getTargetX()) || (move.getSourceY() != move.getTargetY());
    }

    private boolean isTargetOpponentPiece(Move move) {
        Field[][] board = chessBoard.getBoard();

        int sourceX = move.getSourceX();
        int sourceY = move.getSourceY();
        int targetX = move.getTargetX();
        int targetY = move.getTargetY();

        Piece source = board[sourceX][sourceY].getPiece();
        Piece target = board[targetX][targetY].getPiece();

        // Return true if the target is not null and is of a different color
        return target == null || source.getColor() != target.getColor();
    }

    private boolean isRightPlayerTurn(Move move) {
        return playerTurn == move.getSourcePiece().getColor();
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
    private boolean validate(Move move) {
        return hasPositionChanged(move)
                && isTargetOpponentPiece(move)
                && isRightPlayerTurn(move);
    }

    public Move validateMove(int oldX, int oldY, int newX, int newY) {
        Move sMove = new Move(oldX, oldY, newX, newY, chessBoard);
        if (!validate(sMove))
            return null;

        List<Move> movesForSource = getMovesFor(oldX, oldY);
        for (Move move : movesForSource) {
            if (move.getTargetX() == newX && move.getTargetY() == newY)
                return move;
        }
        return null;
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

    /**
     * Check if this move has triggered check on King
     * 
     * @param move
     * @return
     */
    public boolean isCheckMove(Move move) {
        Field[][] board = chessBoard.getBoard();

        int targetX = move.getTargetX();
        int targetY = move.getTargetY();

        Piece piece = board[targetX][targetY].getPiece();
        List<Move> targetMoves = piece.getMoves(targetX, targetY, chessBoard, moveHistory);

        for (Move m : targetMoves) {
            Piece movePiece = m.getTargetPiece();
            if (movePiece != null && movePiece instanceof King)
                return true;
        }
        return false;
    }

    private List<Move> getKingMoves(Color color) {
        Field[][] board = chessBoard.getBoard();

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Piece piece = board[x][y].getPiece();
                if (piece != null && piece instanceof King && piece.getColor() == color)
                    return piece.getMoves(x, y, chessBoard, moveHistory);
            }
        }
        return null;
    }

    public boolean isMateMove(Move move) {
        Field[][] board = chessBoard.getBoard();

        int targetX = move.getTargetX();
        int targetY = move.getTargetY();

        Piece piece = board[targetX][targetY].getPiece();
        Color pieceColor = piece.getColor();

        List<Move> kingMoves = getKingMoves((pieceColor == Color.White) ? Color.Black : Color.White);

        return kingMoves.size() == 0;
    }
}
