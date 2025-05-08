package chess.pieces;

/** Java */
import java.util.ArrayList;
import java.util.List;

/** Chess */
import chess.Move;
import chess.MoveHistory;
import chess.board.ChessBoard;

public class King extends Piece {
    public King(Color color) {
        super(color, Piece.Type.King);
    }

    /**
     * Check if King has moved before
     * 
     * @param moveHistory
     * @return true if moved, false if not
     */
    private boolean kingMoved(MoveHistory moveHistory) {
        List<Move> moveHistoryList = moveHistory.getHistoryList();

        for (Move move : moveHistoryList) {
            if (move.getSourcePiece() == this)
                return true;
        }

        return false;
    }

    @Override
    public List<Move> getMoves(int x, int y, ChessBoard chessBoard, MoveHistory moveHistory) {
        List<Move> moveList = new ArrayList<>();
        chess.board.Field[][] board = chessBoard.getBoard();

        int[][] moves = {
                { -1, -1 }, // left up corner
                { 0, -1 }, // up
                { 1, -1 }, // right up corner

                { 1, 0 }, // right
                { -1, 0 }, // left

                { -1, 1 }, // left down corner
                { 0, 1 }, // down
                { 1, 1 } // right down corner
        };

        // Look over all moves
        for (int[] dir : moves) {
            int dx = dir[0], dy = dir[1];
            int nx = x + dx, ny = y + dy;

            if ((nx >= 0) && (nx <= 7) && (ny >= 0) && (ny <= 7)) {
                Piece piece = board[nx][ny].getPiece();
                if (piece == null) {
                    moveList.add(new Move(x, y, nx, ny, Move.Type.Movement));
                } else {
                    Color otherPieceColor = piece.getColor();
                    if (color != otherPieceColor)
                        moveList.add(new Move(x, y, nx, ny, Move.Type.Beat));
                }
            }
        }

        // Castling
        if (!kingMoved(moveHistory)) {
            Piece king = board[x][y].getPiece();
            boolean isWhite = king.getColor() == Color.White;

            int my = isWhite ? 7 : 0;

            // Long castling
            int lcx = x - 2;
            if (board[lcx][my].getPiece() == null)
                moveList.add(new Move(x, y, x - 2, my, Move.Type.Castling));

            // Short castling
            int scx = x + 2;
            if (board[scx][my].getPiece() == null)
                moveList.add(new Move(x, y, x + 2, my, Move.Type.Castling));
        }

        // TODO: Implement checking move for check

        return moveList;
    }
}
