package chess.pieces;

/** Java */
import java.util.ArrayList;
import java.util.List;

/** Chess */
import chess.Move;
import chess.board.ChessBoard;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color, Piece.Type.Pawn);
    }

    @Override
    public List<Move> getMoves(int x, int y, ChessBoard chessBoard) {
        List<Move> moveList = new ArrayList<>();
        chess.board.Field[][] board = chessBoard.getBoard();

        Piece pawn = board[x][y].getPiece();
        boolean isWhite = pawn.getColor() == Color.White;

        int moveDirection = isWhite ? -1 : 1; // White moves up, Black moves down
        int startRow = isWhite ? 6 : 1;

        // One-step forward move
        int forwardY = y + moveDirection;
        if (isWithinBounds(x, forwardY) && board[x][forwardY].getPiece() == null) {
            moveList.add(new Move(x, y, x, forwardY, Move.Type.Movement));

            // Two-step forward move from starting row
            int doubleForwardY = y + 2 * moveDirection;
            if (y == startRow && board[x][doubleForwardY].getPiece() == null)
                moveList.add(new Move(x, y, x, doubleForwardY, Move.Type.Movement));
        }

        // Diagonal captures (left and right)
        int[] dxOffsets = { -1, 1 };
        for (int dx : dxOffsets) {
            int targetX = x + dx;
            int targetY = y + moveDirection;

            if (!isWithinBounds(targetX, targetY))
                continue;

            Piece targetPiece = board[targetX][targetY].getPiece();
            if (targetPiece != null && targetPiece.getColor() != pawn.getColor())
                moveList.add(new Move(x, y, targetX, targetY, Move.Type.Beat));
        }

        // TODO: Implement En Passant

        return moveList;
    }

    private boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}
