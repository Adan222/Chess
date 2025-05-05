package chess.board;

/** Chess */
import chess.pieces.Piece;

public class Field {
    private Piece currentPiece;

    Field() {
        this.currentPiece = null;
    }

    public void setPiece(Piece newPiece) {
        currentPiece = newPiece;
    }

    public Piece getPiece() {
        return currentPiece;
    }
}
