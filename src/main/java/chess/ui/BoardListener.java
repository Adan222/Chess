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

    private boolean isDragging;
    private int oldX;
    private int oldY;

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
