package chess.pieces;

/** Java */
import java.util.ArrayList;
import java.util.List;

/** Chess */
import chess.Move;
import chess.board.ChessBoard;

public class King extends Piece {
    public King(Color color) {
        super(color, Piece.Type.King);
    }

    @Override
    public List<Move> getMoves(int x, int y, ChessBoard chessBoard) {
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

        // TODO: Implement castling
        // TODO: Implement checking move for check

        return moveList;
    }
}
