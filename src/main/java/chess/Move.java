package chess;

import chess.board.ChessBoard;
import chess.board.Field;
import chess.pieces.Piece;

public class Move {
    public enum Type {
        Movement,
        Beat,
        EnPassant,
        Castling
    }

    private Piece sourcePiece;
    private Piece targetPiece;

    public int sourceX;
    public int sourceY;

    public int destinationX;
    public int destinationY;

    Type type;

    public Move(int sourceX, int sourceY, int destinationX, int destinationY, Type type) {
        this.sourceX = sourceX;
        this.sourceY = sourceY;
        this.destinationX = destinationX;
        this.destinationY = destinationY;

        this.type = type;
    }

    public Move(int sourceX, int sourceY, int destinationX, int destinationY, ChessBoard chessBoard) {
        this.sourceX = sourceX;
        this.sourceY = sourceY;
        this.destinationX = destinationX;
        this.destinationY = destinationY;

        Field[][] board = chessBoard.getBoard();
        this.sourcePiece = board[sourceX][sourceY].getPiece();
        this.targetPiece = board[destinationX][destinationY].getPiece();

        if (targetPiece != null)
            this.type = Type.Beat;
        else
            this.type = Type.Movement;
    }
}
