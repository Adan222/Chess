package chess.ui;

/** Java */
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.JLayeredPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

/** Chess */
import chess.Game;
import chess.Move;
import chess.Move.Type;
import chess.pieces.Piece;

/**
 * This is class that inherit from JPanel, that is used
 * for displaying chess board and pieces
 */
public class ChessPanel extends JPanel {
    Game game;

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
    public ChessPanel(Game game) {
        super(new BorderLayout());

        this.game = game;

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

        BoardListener eventManager = new BoardListener(this);
        layerPanel.addMouseListener(eventManager);
        layerPanel.addMouseMotionListener(eventManager);

        this.add(layerPanel, BorderLayout.CENTER);
    }

    /**
     * Initialize all squares on the board
     */
    private void initSquares() {
        squarePanels = new JPanel[BOARD_DIMENSION][BOARD_DIMENSION];

        for (int col = 0; col < BOARD_DIMENSION; col++) {
            for (int row = 0; row < BOARD_DIMENSION; row++) {
                squarePanels[row][col] = new JPanel();

                // Settings
                squarePanels[row][col].setLayout(new OverlayLayout(squarePanels[row][col]));
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

    /**
     * This function is responsible for calling business logic
     * for move with given start and stop board coordinates.
     * 
     * @param oldX
     * @param oldY
     * @param newX
     * @param newY
     */
    public void callMove(int oldX, int oldY, int newX, int newY) {
        game.makeMove(oldX, oldY, newX, newY);
    }

    /**
     * Remove piece from given field
     * 
     * @param square
     */
    private void removePieceFromField(JPanel square) {
        int sourceComponentCount = square.getComponentCount();
        if (sourceComponentCount > 0) {
            Component oldPiece = square.getComponent(sourceComponentCount - 1);

            // Swap pieces
            square.remove(oldPiece);

            // Repaint
            square.revalidate();
            square.repaint();
        }
    }

    /**
     * 
     * @param square
     * @param piece
     */
    private void swapPiecesOnFields(JPanel sourceSquare, JPanel targetSquare) {
        int componentCount = sourceSquare.getComponentCount();
        if (componentCount > 0) {
            Component oldPiece = sourceSquare.getComponent(componentCount - 1);

            // Swap pieces
            sourceSquare.remove(oldPiece);
            targetSquare.add(oldPiece);

            // Repaint
            sourceSquare.revalidate();
            sourceSquare.repaint();
            targetSquare.revalidate();
            targetSquare.repaint();
        }
    }

    /**
     * This function is responsible for changing the pieces on the board,
     * if any move were made.
     * 
     * @param move
     */
    public void makeMove(Move move) {
        int sourceX = move.getSourceX();
        int sourceY = move.getSourceY();
        int targetX = move.getTargetX();
        int targetY = move.getTargetY();

        Type type = move.getType();
        if (type == Type.ShortCastling || type == Type.LongCastling) {

            // Move King
            swapPiecesOnFields(squarePanels[sourceX][sourceY], squarePanels[targetX][targetY]);

            // Move Rook
            if (type == Type.ShortCastling || type == Type.LongCastling) {
                int rx = (type == Type.ShortCastling) ? targetX - 1 : targetX + 1;
                int rookX = type == Type.ShortCastling ? 7 : 0;

                swapPiecesOnFields(squarePanels[rookX][sourceY], squarePanels[rx][targetY]);
            }

            return;
        } else if (type == Type.EnPassant) {

            // Move source Pawn
            swapPiecesOnFields(squarePanels[sourceX][sourceY], squarePanels[targetX][targetY]);

            // Remove target Pawn
            Piece sourcePiece = move.getSourcePiece();

            int targetPawnX = targetX;
            int dy = (sourcePiece.getColor() == Piece.Color.White) ? 1 : -1;
            int targetPawnY = targetY + dy;

            removePieceFromField(squarePanels[targetPawnX][targetPawnY]);

            return;
        }

        JPanel sourceSquare = this.squarePanels[sourceX][sourceY];
        JPanel targetSquare = this.squarePanels[targetX][targetY];

        removePieceFromField(targetSquare);
        swapPiecesOnFields(sourceSquare, targetSquare);
    }

    /**
     * This function is invoked when we click on piece
     * and should show the available moves for the clicked
     * piece.
     * 
     * @param x
     * @param y
     */
    public void callShowMoves(int x, int y) {
        List<Move> moveList = game.getAvailableMoves(x, y);

        for (Move move : moveList) {
            int targetX = move.getTargetX();
            int targetY = move.getTargetY();

            JPanel square = squarePanels[targetX][targetY];

            // Add CirclePanel
            CirclePanel circle = new CirclePanel();
            square.add(circle);

            square.setComponentZOrder(circle, 0);
            square.revalidate();
            square.repaint();
        }
    }

    /**
     * Hide available moves from the board
     * 
     * @param x
     * @param y
     */
    public void callHideMoves(int x, int y) {
        List<Move> moveList = game.getAvailableMoves(x, y);

        for (Move move : moveList) {
            int targetX = move.getTargetX();
            int targetY = move.getTargetY();

            JPanel square = squarePanels[targetX][targetY];

            // We can't relay on getComponentCount() if we change Z-order
            for (Component comp : square.getComponents())
                if (comp instanceof CirclePanel) {
                    square.remove(comp);
                    break;
                }

            square.revalidate();
            square.repaint();
        }
    }
}
