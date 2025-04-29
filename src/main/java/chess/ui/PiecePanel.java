package chess.ui;

/** Java */
import javax.swing.JPanel;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.Image;

/** Chess */
import chess.pieces.Piece;

import chess.utils.SpriteManager;

public class PiecePanel extends JPanel {
    /** Private variables */
    private Piece.Type type;
    private Piece.Color color;

    public PiecePanel(Piece.Type type, Piece.Color color) {
        super(new GridLayout(1, 1));

        this.type = type;
        this.color = color;

        // Set transparency
        this.setOpaque(false);

        initComponent();
    }

    /**
     * Return the image for the piece
     * 
     * @throws Exception
     */
    private Image getPieceImage() throws Exception {
        SpriteManager manager = new SpriteManager();

        switch (type) {
            case King:
                return manager.getKing(color);
            case Queen:
                return manager.getQueen(color);
            case Rook:
                return manager.getRook(color);
            case Bishop:
                return manager.getBishop(color);
            case Knight:
                return manager.getKnight(color);
            case Pawn:
                return manager.getPawn(color);
        }
        throw new Exception("Unknown piece type");
    }

    /**
     * Display piece image
     * 
     * @throws Exception
     */
    private void initComponent() {
        try {
            Image pieceImage = getPieceImage();

            JLabel picLabel = new JLabel(new ImageIcon(pieceImage));
            add(picLabel);
        } catch (Exception e) {
            // TODO: Rethrow this exception
            e.printStackTrace();
        }
    }
}
