package chess.domain.moving;

import chess.domain.board.Board;
import chess.domain.pieces.Piece;
import chess.domain.position.Position;

import java.util.List;

public class BishopMoving extends MultiMoving {
    private final int[] rowDirection = {-1, 1, -1, 1};
    private final int[] colDirection = {-1, 1, 1, -1};

    @Override
    public List<Position> allMovablePositions(final Piece piece, final Board board) {
        return super.movablePositions(piece, board, rowDirection, colDirection);
    }
}
