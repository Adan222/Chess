package chess.ui;

/** Java */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class CirclePanel extends JPanel {

    public CirclePanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Graphics settings
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Circle size (e.g. 30x30)
        int circleSize = 30;
        int x = (getWidth() - circleSize) / 2;
        int y = (getHeight() - circleSize) / 2;

        g2d.setColor(new Color(211, 211, 211, 160));
        g2d.fillOval(x, y, circleSize, circleSize);

        g2d.dispose();
    }
}