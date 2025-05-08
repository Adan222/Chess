package chess;

/** Java */
import java.util.List;
import java.util.ArrayList;

public class MoveHistory {
    private List<Move> moveHistoryList;

    public MoveHistory() {
        this.moveHistoryList = new ArrayList<>();
    }

    public void addMove(Move move) {
        moveHistoryList.add(move);
    }

    public List<Move> getHistoryList() {
        return moveHistoryList;
    }
}
