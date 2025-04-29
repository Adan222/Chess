package chess;

import chess.ui.ChessFrame;

/** Java */

public class Game {
    private ChessFrame chessFrame;

    public Game() {
        initComponents();
    }

    private void initComponents() {
        chessFrame = new ChessFrame();
    }

    public void run() {
        chessFrame.setVisible(true);
    }
}
