package chess.pieces;

/** Java */
import java.util.ArrayList;
import java.util.List;

/** Chess */
import chess.Move;
import chess.MoveHistory;
import chess.board.ChessBoard;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color, Piece.Type.Pawn);
    }

    @Override
    public List<Move> getMoves(int x, int y, ChessBoard chessBoard, MoveHistory moveHistory) {
        List<Move> moveList = new ArrayList<>();
        chess.board.Field[][] board = chessBoard.getBoard();

        Piece pawn = board[x][y].getPiece();
        boolean isWhite = pawn.getColor() == Color.White;

        int moveDirection = isWhite ? -1 : 1; // White moves up, Black moves down
        int startRow = isWhite ? 6 : 1;

        // One-step forward move
        int forwardY = y + moveDirection;
        if (isWithinBounds(x, forwardY) && board[x][forwardY].getPiece() == null) {
            moveList.add(new Move(x, y, x, forwardY, this, null, Move.Type.Movement));

            // Two-step forward move from starting row
            int doubleForwardY = y + 2 * moveDirection;
            if (y == startRow && board[x][doubleForwardY].getPiece() == null)
                moveList.add(new Move(x, y, x, doubleForwardY, this, null, Move.Type.Movement));
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
                moveList.add(new Move(x, y, targetX, targetY, this, targetPiece, Move.Type.Beat));
        }

        // En Passant
        Move lastMove = getLastMove(moveHistory);
        if (lastMove != null) {
            Piece piece = lastMove.getSourcePiece();

            if (piece instanceof Pawn) {
                // Pawn has to be on the left or right from our pawn
                int dx = lastMove.getTargetX() - x;

                // Piece had to move two fields
                int mdy = lastMove.getTargetY() - lastMove.getSourceY();

                if (Math.abs(dx) == 1 && Math.abs(mdy) == 2) {
                    boolean isTargetWhite = piece.getColor() == Color.White;

                    // Get left or right of our piece
                    int enx = x + dx;

                    // The move is always "behind" the target Pawn
                    int targetPieceY = lastMove.getTargetY();
                    int eny = targetPieceY + (isTargetWhite ? 1 : -1);

                    moveList.add(new Move(x, y, enx, eny, this, piece, Move.Type.EnPassant));
                }
            }
        }

        return moveList;
    }

    private boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    /**
     * Get the latest move from history or null
     * 
     * @param moveHistory
     * @return latest Move or null
     */
    private Move getLastMove(MoveHistory moveHistory) {
        List<Move> moveHistoryList = moveHistory.getHistoryList();
        int historySize = moveHistoryList.size();

        if (historySize > 0)
            return moveHistoryList.get(historySize - 1);
        return null;
    }

}
