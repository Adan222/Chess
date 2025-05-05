package chess.pieces;

/** Java */
import java.util.List;

/** Chess */
import chess.Move;
import chess.board.ChessBoard;

/** Virtual class for implementing chess pieces movement */
public class Piece {
    public enum Type {
        King,
        Queen,
        Rook,
        Bishop,
        Knight,
        Pawn
    }

    public enum Color {
        Black, White
    }

    protected Color color;
    protected Type type;

    public Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    /**
     * Virtual function for getting all possible moves.
     * Each class should Overwrite this method.
     */
    public List<Move> getMoves(int x, int y, ChessBoard board) {
        throw new UnsupportedOperationException("Unimplemented method 'getMoves'");
    }
}
