package chess.pieces;

/** Java */
import java.util.ArrayList;
import java.util.List;

/** Chess */
import chess.Move;
import chess.MoveHistory;
import chess.board.ChessBoard;

public class Queen extends Piece {
    public Queen(Color color) {
        super(color, Piece.Type.Queen);
    }

    @Override
    public List<Move> getMoves(int x, int y, ChessBoard chessBoard, MoveHistory moveHistory) {
        List<Move> moveList = new ArrayList<>();
        chess.board.Field[][] board = chessBoard.getBoard();

        int[][] directions = {
                { -1, -1 }, // left up
                { -1, 1 }, // left down
                { 1, -1 }, // right up
                { 1, 1 }, // right down

                { -1, 0 }, // left
                { 1, 0 }, // right
                { 0, -1 }, // down
                { 0, 1 } // up
        };

        // Look over all directions
        for (int[] dir : directions) {
            int dx = dir[0], dy = dir[1];
            int nx = x + dx, ny = y + dy;

            while (nx >= 0 && nx <= 7 && ny >= 0 && ny <= 7) {
                Piece piece = board[nx][ny].getPiece();
                if (piece == null) {
                    moveList.add(new Move(x, y, nx, ny, Move.Type.Movement));
                    nx += dx;
                    ny += dy;
                } else {
                    Color otherPieceColor = piece.getColor();
                    if (color != otherPieceColor)
                        moveList.add(new Move(x, y, nx, ny, Move.Type.Beat));
                    break;
                }
            }
        }

        return moveList;
    }
}
