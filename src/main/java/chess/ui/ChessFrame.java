package chess.ui;

/** Java */
import javax.swing.JFrame;

/**
 * Window for chess game
 */
public class ChessFrame extends JFrame {
    private ChessPanel chessPanel;

    /**
     * Initialize frame
     */
    public ChessFrame() {
        super();

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
        chessPanel = new ChessPanel();
        this.add(chessPanel);
    }
}
