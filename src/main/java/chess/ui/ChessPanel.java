package chess.ui;

/** Java */
import javax.swing.JPanel;
import javax.swing.JLayeredPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

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

        for (int row = 0; row < BOARD_DIMENSION; row++) {
            for (int col = 0; col < BOARD_DIMENSION; col++) {
                squarePanels[row][col] = new JPanel(new GridLayout(1, 1));

                squarePanels[row][col].setPreferredSize(new Dimension(SQUARE_SIZE, SQUARE_SIZE));
                squarePanels[row][col].setSize(new Dimension(SQUARE_SIZE, SQUARE_SIZE));

                Color squareColor = ((row + col) % 2 == 0 ? Color.WHITE : Color.GRAY);
                squarePanels[row][col].setBackground(squareColor);

                this.boardPanel.add(squarePanels[row][col]);
            }
        }
    }
}
