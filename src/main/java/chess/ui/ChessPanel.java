package chess.ui;

/** Java */
import javax.swing.JPanel;
import javax.swing.JLayeredPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

/** Chess */
import chess.pieces.Piece;

/**
 * This is class that inherit from JPanel, that is used
 * for displaying chess board and pieces
 */
public class ChessPanel extends JPanel {
    /** UI components */
    private JPanel boardPanel;
    private JLayeredPane layerPanel;
    private JPanel[][] squarePanels;

    /** Constants */
    private static final int BOARD_DIMENSION = 8;
    private static final int SQUARE_SIZE = 100;

    /**
     * Initialize Chess Panel
     */
    public ChessPanel() {
        super(new BorderLayout());

        initBoard();
        initSquares();
        initPieces();
    }

    /**
     * Initialize board panel and layer panel
     */
    private void initBoard() {
        /** Board panel */
        boardPanel = new JPanel(new GridLayout(8, 8));
        boardPanel.setBounds(0, 0, 800, 800);

        /** Layer panel */
        layerPanel = new JLayeredPane();
        layerPanel.setPreferredSize(new Dimension(800, 800));

        layerPanel.add(boardPanel, JLayeredPane.DEFAULT_LAYER);

        layerPanel.setVisible(true);

        this.add(layerPanel, BorderLayout.CENTER);
    }

    /**
     * Initialize all squares on the board
     */
    private void initSquares() {
        squarePanels = new JPanel[BOARD_DIMENSION][BOARD_DIMENSION];

        for (int col = 0; col < BOARD_DIMENSION; col++) {
            for (int row = 0; row < BOARD_DIMENSION; row++) {
                squarePanels[row][col] = new JPanel(new GridLayout(1, 1));

                // Settings
                squarePanels[row][col].setPreferredSize(new Dimension(SQUARE_SIZE, SQUARE_SIZE));
                squarePanels[row][col].setSize(new Dimension(SQUARE_SIZE, SQUARE_SIZE));

                // Color
                Color squareColor = ((row + col) % 2 == 0 ? Color.WHITE : Color.GRAY);
                squarePanels[row][col].setBackground(squareColor);

                this.boardPanel.add(squarePanels[row][col]);
            }
        }
    }

    /**
     * Set default positions for all pieces
     */
    private void initPieces() {
        /** White pieces */
        this.squarePanels[0][0].add(new PiecePanel(Piece.Type.Rook, Piece.Color.Black));
        this.squarePanels[1][0].add(new PiecePanel(Piece.Type.Knight, Piece.Color.Black));
        this.squarePanels[2][0].add(new PiecePanel(Piece.Type.Bishop, Piece.Color.Black));
        this.squarePanels[3][0].add(new PiecePanel(Piece.Type.Queen, Piece.Color.Black));
        this.squarePanels[4][0].add(new PiecePanel(Piece.Type.King, Piece.Color.Black));
        this.squarePanels[5][0].add(new PiecePanel(Piece.Type.Bishop, Piece.Color.Black));
        this.squarePanels[6][0].add(new PiecePanel(Piece.Type.Knight, Piece.Color.Black));
        this.squarePanels[7][0].add(new PiecePanel(Piece.Type.Rook, Piece.Color.Black));

        for (int i = 0; i < 8; i++)
            this.squarePanels[i][1].add(new PiecePanel(Piece.Type.Pawn, Piece.Color.Black));

        /** Black pieces */
        this.squarePanels[0][7].add(new PiecePanel(Piece.Type.Rook, Piece.Color.White));
        this.squarePanels[1][7].add(new PiecePanel(Piece.Type.Knight, Piece.Color.White));
        this.squarePanels[2][7].add(new PiecePanel(Piece.Type.Bishop, Piece.Color.White));
        this.squarePanels[3][7].add(new PiecePanel(Piece.Type.Queen, Piece.Color.White));
        this.squarePanels[4][7].add(new PiecePanel(Piece.Type.King, Piece.Color.White));
        this.squarePanels[5][7].add(new PiecePanel(Piece.Type.Bishop, Piece.Color.White));
        this.squarePanels[6][7].add(new PiecePanel(Piece.Type.Knight, Piece.Color.White));
        this.squarePanels[7][7].add(new PiecePanel(Piece.Type.Rook, Piece.Color.White));

        for (int i = 0; i < 8; i++)
            this.squarePanels[i][6].add(new PiecePanel(Piece.Type.Pawn, Piece.Color.White));
    }
}
