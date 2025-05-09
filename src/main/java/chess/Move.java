package chess;

import chess.board.ChessBoard;
import chess.board.Field;
import chess.pieces.Piece;

public class Move {
    public enum Type {
        Movement,
        Beat,
        EnPassant,
        ShortCastling,
        LongCastling
    }

    /** Variables */

    private Piece sourcePiece;
    private Piece targetPiece;

    private int sourceX;
    private int sourceY;

    private int targetX;
    private int targetY;

    Type type;

    public Move(int sourceX, int sourceY, int targetX, int targetY, Type type) {
        this.sourceX = sourceX;
        this.sourceY = sourceY;
        this.targetX = targetX;
        this.targetY = targetY;

        this.type = type;
    }

    public Move(int sourceX, int sourceY, int targetX, int targetY, Piece sourcePiece, Piece targetPiece, Type type) {
        this.sourceX = sourceX;
        this.sourceY = sourceY;
        this.targetX = targetX;
        this.targetY = targetY;

        this.sourcePiece = sourcePiece;
        this.targetPiece = targetPiece;

        this.type = type;
    }

    public Move(int sourceX, int sourceY, int targetX, int targetY, ChessBoard chessBoard) {
        this.sourceX = sourceX;
        this.sourceY = sourceY;
        this.targetX = targetX;
        this.targetY = targetY;

        Field[][] board = chessBoard.getBoard();
        this.sourcePiece = board[sourceX][sourceY].getPiece();
        this.targetPiece = board[targetX][targetY].getPiece();

        if (targetPiece != null)
            this.type = Type.Beat;
        else
            this.type = Type.Movement;
    }

    /** Getters */

    public int getSourceX() {
        return sourceX;
    }

    public int getSourceY() {
        return sourceY;
    }

    public int getTargetX() {
        return targetX;
    }

    public int getTargetY() {
        return targetY;
    }

    public Piece getSourcePiece() {
        return sourcePiece;
    }

    public Piece getTargetPiece() {
        return targetPiece;
    }

    public Type getType() {
        return type;
    }
}
