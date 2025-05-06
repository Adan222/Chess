package chess.ui;

/** Java */
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Mouse event listener for each of pieces, that implements
 * both MouseListener for basic mouse event operation, and
 * MouseMotionListener for mouse motion events
 */
public class BoardListener implements MouseListener, MouseMotionListener {
    ChessPanel board;

    // Drag & drop
    private boolean isDragging;
    private int oldX;
    private int oldY;

    // Click
    private boolean isClicked;
    private int x;
    private int y;

    public BoardListener(ChessPanel board) {
        this.board = board;

        // Init constants
        this.isDragging = false;
        this.oldX = 0;
        this.oldY = 0;
    }

    /** MouseListener */

    public void mousePressed(MouseEvent e) {
        Point coords = e.getPoint();

        this.oldX = (int) coords.getX() / 100;
        this.oldY = (int) coords.getY() / 100;

        System.out.println("Pressed: " + oldX + " " + oldY);

        int newX = (int) coords.getX() / 100;
        int newY = (int) coords.getY() / 100;

        // If we are clicked, then
        // - If we clicked on the same piece, then disable the move list.
        // - If we clicked on the other piece, we have to hide moves for
        // the previous piece and show for the new one.
        if (isClicked) {
            if (x == newX && y == newY) {
                board.callHideMoves(x, y);
                isClicked = false;
            } else {
                board.callHideMoves(x, y);
                board.callShowMoves(newX, newY);

                x = newX;
                y = newY;
            }
        }

        // If we wasn't clicked, then show moves for this piece and save
        // state.
        else {
            x = newX;
            y = newY;
            isClicked = true;

            board.callShowMoves(x, y);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (isDragging) {
            Point coords = e.getPoint();

            int newX = (int) coords.getX() / 100;
            int newY = (int) coords.getY() / 100;

            isDragging = false;

            System.out.println("Released: " + newX + " " + newY);

            board.callMove(oldX, oldY, newX, newY);
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    /** MouseMotionListener */

    public void mouseDragged(MouseEvent e) {
        if (!isDragging)
            isDragging = true;
    }

    public void mouseMoved(MouseEvent e) {
    }
}
