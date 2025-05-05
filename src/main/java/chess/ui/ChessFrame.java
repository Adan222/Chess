package chess.ui;

/** Java */
import javax.swing.JFrame;

import chess.Game;

/**
 * Window for chess game
 */
public class ChessFrame extends JFrame {
    Game game;

    private ChessPanel chessPanel;

    /**
     * Initialize frame
     */
    public ChessFrame(Game game) {
        super();

        this.game = game;

        initFrame();
        initComponents();
    }

    /**
     * Basic initialization of JFrame
     */
    private void initFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }

    /**
     * Initialize all the components that Chess frame
     * consist of
     */
    private void initComponents() {
        chessPanel = new ChessPanel(game);
        this.add(chessPanel);
    }

    public void makeMove(int oldX, int oldY, int newX, int newY) {
        chessPanel.makeMove(oldX, oldY, newX, newY);
    }
}
