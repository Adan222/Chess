package chess.board;

/** Chess */
import chess.pieces.Queen;
import chess.pieces.Piece.Color;
import chess.pieces.Rook;
import chess.Move;
import chess.Move.Type;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Piece;

/**
 * This class is responsible for holding
 */
public class ChessBoard {
    private Field[][] board;

    /** Constants */
    private static final int BOARD_DIMENSION = 8;

    public ChessBoard() {
        initBoard();
        initPieces();
    }

    /**
     * Initialize 8x8 board array
     */
    private void initBoard() {
        board = new Field[BOARD_DIMENSION][BOARD_DIMENSION];

        for (int col = 0; col < BOARD_DIMENSION; col++) {
            for (int row = 0; row < BOARD_DIMENSION; row++) {
                board[row][col] = new Field();
            }
        }
    }

    /**
     * Set default positions for all pieces
     */
    private void initPieces() {
        /** White pieces */
        board[0][0].setPiece(new Rook(Color.Black));
        board[1][0].setPiece(new Knight(Color.Black));
        board[2][0].setPiece(new Bishop(Color.Black));
        board[3][0].setPiece(new Queen(Color.Black));
        board[4][0].setPiece(new King(Color.Black));
        board[5][0].setPiece(new Bishop(Color.Black));
        board[6][0].setPiece(new Knight(Color.Black));
        board[7][0].setPiece(new Rook(Color.Black));

        for (int i = 0; i < 8; i++)
            board[i][1].setPiece(new Pawn(Color.Black));

        /** Black pieces */
        board[0][7].setPiece(new Rook(Color.White));
        board[1][7].setPiece(new Knight(Color.White));
        board[2][7].setPiece(new Bishop(Color.White));
        board[3][7].setPiece(new Queen(Color.White));
        board[4][7].setPiece(new King(Color.White));
        board[5][7].setPiece(new Bishop(Color.White));
        board[6][7].setPiece(new Knight(Color.White));
        board[7][7].setPiece(new Rook(Color.White));

        for (int i = 0; i < 8; i++)
            board[i][6].setPiece(new Pawn(Color.White));
    }

    private void swapPiece(Field sourceField, Field targetField) {
        Piece sourcePiece = sourceField.getPiece();

        // Swap pieces
        targetField.setPiece(sourcePiece);
        sourceField.setPiece(null);
    }

    /**
     * * Make move on the board
     * 
     * @param move
     */
    public void makeMove(Move move) {
        int sourceX = move.getSourceX();
        int sourceY = move.getSourceY();
        int targetX = move.getTargetX();
        int targetY = move.getTargetY();

        // TODO: Make it switch
        Type type = move.getType();
        if (type == Type.ShortCastling || type == Type.LongCastling) {

            // Move King
            swapPiece(board[sourceX][sourceY], board[targetX][targetY]);

            // Move Rook
            if (type == Type.ShortCastling || type == Type.LongCastling) {
                int rx = (type == Type.ShortCastling) ? targetX - 1 : targetX + 1;
                int rookX = type == Type.ShortCastling ? 7 : 0;

                swapPiece(board[rookX][sourceY], board[rx][targetY]);
            }

            return;
        } else if (type == Type.EnPassant) {

            // Move source Pawn
            swapPiece(board[sourceX][sourceY], board[targetX][targetY]);

            // Remove target Pawn
            Piece sourcePiece = move.getSourcePiece();

            int targetPawnX = targetX;
            int dy = (sourcePiece.getColor() == Color.White) ? 1 : -1;
            int targetPawnY = targetY + dy;

            board[targetPawnX][targetPawnY].setPiece(null);

            return;
        } else if (type == Type.Promotion) {
            Field sourceField = board[sourceX][sourceY];
            Field targetField = board[targetX][targetY];

            Piece sourcePiece = sourceField.getPiece();

            // TODO: Make UI and choose piece
            targetField.setPiece(new Queen(sourcePiece.getColor()));
            sourceField.setPiece(null);

            return;
        }

        Field sourceField = board[sourceX][sourceY];
        Field targetField = board[targetX][targetY];

        Piece sourcePiece = sourceField.getPiece();

        // Swap pieces
        targetField.setPiece(sourcePiece);
        sourceField.setPiece(null);
    }

    public Field[][] getBoard() {
        return board;
    }
}
