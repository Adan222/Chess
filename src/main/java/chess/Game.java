package chess;

import chess.board.ChessBoard;
import chess.ui.ChessFrame;

/** Java */

public class Game {
    private ChessFrame chessFrame;
    private ChessBoard chessBoard;

    public Game() {
        initComponents();
    }

    private void initComponents() {
        chessFrame = new ChessFrame(this);
        chessBoard = new ChessBoard();
    }

    public void run() {
        chessFrame.setVisible(true);
    }

    public void makeMove(int oldX, int oldY, int newX, int newY) {
        // Validate move
        if (true) {
            chessBoard.makeMove(oldX, oldY, newX, newY);
            chessFrame.makeMove(oldX, oldY, newX, newY);
        }
    }
}
