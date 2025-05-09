package chess.pieces;

/** Java */
import java.util.ArrayList;
import java.util.List;

/** Chess */
import chess.Move;
import chess.MoveHistory;
import chess.board.ChessBoard;

public class King extends Piece {
    private int[][] moves = {
            { -1, -1 }, // left up corner
            { 0, -1 }, // up
            { 1, -1 }, // right up corner

            { 1, 0 }, // right
            { -1, 0 }, // left

            { -1, 1 }, // left down corner
            { 0, 1 }, // down
            { 1, 1 } // right down corner
    };

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

        boolean isWhite = this.getColor() == Color.White;

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
            int my = isWhite ? 7 : 0;

            // TODO: Check if Rook moved or doesn't exist

            // Long castling
            int lcx = x - 2;
            if (board[lcx][my].getPiece() == null) {
                Piece rook = board[0][my].getPiece();
                moveList.add(new Move(x, y, x - 2, my, this, rook, Move.Type.LongCastling));
            }

            // Short castling
            int scx = x + 2;
            if (board[scx][my].getPiece() == null) {
                Piece rook = board[7][my].getPiece();
                moveList.add(new Move(x, y, x + 2, my, this, rook, Move.Type.ShortCastling));
            }
        }

        // Get list of move for other Pieces and check if these moves are in the
        // current King move list
        List<Move> enemyPieceMoves = new ArrayList<>();
        Color enemyColor = isWhite ? Color.Black : Color.White;

        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                Piece piece = board[row][col].getPiece();
                if (piece != null && piece.getColor() == enemyColor) {
                    // If we will stumble upon King, this will cause infinity loop
                    if (piece instanceof King)
                        enemyPieceMoves.addAll(getEnemyKingMoves(row, col));
                    else
                        enemyPieceMoves.addAll(piece.getMoves(row, col, chessBoard, moveHistory));
                }
            }
        }

        // Remove Kings move that has mutual target square - these moves are illegal
        List<Move> filteredMoves = new ArrayList<>();
        for (Move move : moveList) {
            boolean isMutual = false;

            for (Move enemyMove : enemyPieceMoves) {
                if (move.getTargetX() == enemyMove.getTargetX() &&
                        move.getTargetY() == enemyMove.getTargetY()) {
                    isMutual = true;
                    break;
                }
            }

            if (!isMutual) {
                filteredMoves.add(move);
            }
        }

        moveList = filteredMoves;
        return moveList;
    }

    /**
     * It only gets moves around enemy King, nothing else
     */
    private List<Move> getEnemyKingMoves(int x, int y) {
        List<Move> moveList = new ArrayList<>();
        for (int[] dir : moves) {
            int dx = dir[0], dy = dir[1];
            int nx = x + dx, ny = y + dy;

            if ((nx >= 0) && (nx <= 7) && (ny >= 0) && (ny <= 7))
                moveList.add(new Move(x, y, nx, ny, Move.Type.Movement));
        }

        return moveList;
    }
}
