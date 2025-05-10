package chess.pieces;

/** Java */
import java.util.ArrayList;
import java.util.List;

/** Chess */
import chess.Move;
import chess.MoveHistory;
import chess.board.ChessBoard;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color, Piece.Type.Knight);
    }

    @Override
    public List<Move> getMoves(int x, int y, ChessBoard chessBoard, MoveHistory moveHistory) {
        List<Move> moveList = new ArrayList<>();
        chess.board.Field[][] board = chessBoard.getBoard();

        int[][] moves = {
                { -1, -2 }, // up left
                { 1, -2 }, // up right

                { 2, -1 }, // right up
                { 2, 1 }, // right down

                { 1, 2 }, // down right
                { -1, 2 }, // down left

                { -2, -1 }, // left up
                { -2, 1 }, // left down
        };

        // Look over all moves
        for (int[] dir : moves) {
            int dx = dir[0], dy = dir[1];
            int nx = x + dx, ny = y + dy;

            if ((nx >= 0) && (nx <= 7) && (ny >= 0) && (ny <= 7)) {
                Piece piece = board[nx][ny].getPiece();
                if (piece == null) {
                    moveList.add(new Move(x, y, nx, ny, this, null, Move.Type.Movement));
                } else {
                    Color otherPieceColor = piece.getColor();
                    if (color != otherPieceColor)
                        moveList.add(new Move(x, y, nx, ny, this, piece, Move.Type.Beat));
                }
            }
        }

        return moveList;
    }
}
