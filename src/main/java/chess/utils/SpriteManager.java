package chess.utils;

/** Java */
import java.awt.Image;

import javax.swing.ImageIcon;

import chess.pieces.Piece;
import chess.ui.*;

/**
 * This class is responsible for loading and serving
 * sprite images for Chess pieces
 */
public class SpriteManager {
    private static final String IMAGE_PATH = "/icons/";

    private static final String WHITE_PIECE = "white-";
    private static final String BLACK_PIECE = "black-";

    public SpriteManager() {
    }

    /**
     * Get scaled image of the piece
     * 
     * @param pieceName name of the file without extension
     * @return Scaled image of the piece
     */
    private Image getImage(String pieceName) {
        final String imagePath = IMAGE_PATH + pieceName + ".png";

        Image pieceImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
        pieceImage = pieceImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

        return pieceImage;
    }

    /** ===== Get pieces functions ===== */

    public Image getKing(Piece.Color color) {
        final String piece = "king";

        if (color == Piece.Color.Black)
            return getImage(BLACK_PIECE + piece);
        else
            return getImage(WHITE_PIECE + piece);
    }

    public Image getQueen(Piece.Color color) {
        final String piece = "queen";

        if (color == Piece.Color.Black)
            return getImage(BLACK_PIECE + piece);
        else
            return getImage(WHITE_PIECE + piece);
    }

    public Image getRook(Piece.Color color) {
        final String piece = "rook";

        if (color == Piece.Color.Black)
            return getImage(BLACK_PIECE + piece);
        else
            return getImage(WHITE_PIECE + piece);
    }

    public Image getBishop(Piece.Color color) {
        final String piece = "bishop";

        if (color == Piece.Color.Black)
            return getImage(BLACK_PIECE + piece);
        else
            return getImage(WHITE_PIECE + piece);
    }

    public Image getKnight(Piece.Color color) {
        final String piece = "knight";

        if (color == Piece.Color.Black)
            return getImage(BLACK_PIECE + piece);
        else
            return getImage(WHITE_PIECE + piece);
    }

    public Image getPawn(Piece.Color color) {
        final String piece = "pawn";

        if (color == Piece.Color.Black)
            return getImage(BLACK_PIECE + piece);
        else
            return getImage(WHITE_PIECE + piece);
    }

}
