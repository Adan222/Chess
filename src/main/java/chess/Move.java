package chess;

public class Move {
    public enum Type {
        Movement,
        Beat,
        EnPassant,
        Castling
    }

    public int sourceX;
    public int sourceY;

    public int destinationX;
    public int destinationY;

    Type type;

    public Move(int sourceX, int sourceY, int destinationX, int destinationY, Type type) {
        this.sourceX = sourceX;
        this.sourceY = sourceY;
        this.destinationX = destinationX;
        this.destinationY = destinationY;

        this.type = type;
    }
}
