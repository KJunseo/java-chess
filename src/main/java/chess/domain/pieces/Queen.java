package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.exception.WrongInitPositionException;

import java.util.ArrayList;
import java.util.List;

public final class Queen extends NoKingPieces {
    private static final String BLACK_TEAM_ROW = "8";
    private static final String WHITE_TEAM_ROW = "1";
    private static final double SCORE = 9.0;
    private static final int INIT_COL = 3;

    public Queen(final Team team, final Position position) {
        super(position, "Q", team, SCORE);
    }

    public static Queen of(final Team team, final int col) {
        if (col != INIT_COL) {
            throw new WrongInitPositionException();
        }
        return new Queen(team, getInitPosition(team, col));
    }

    private static Position getInitPosition(final Team team, final int col) {
        if (team.equals(Team.BLACK)) {
            return new Position(Row.getLocation(BLACK_TEAM_ROW), col);
        }
        return new Position(Row.getLocation(WHITE_TEAM_ROW), col);
    }

    @Override
    public final List<Position> getMovablePositions(final Board board) {
        List<Position> movablePositions = new ArrayList<>();

        int[] rowDir = {0, 0, -1, 1, -1, 1, -1, 1};
        int[] colDir = {-1, 1, 0, 0, -1, 1, 1, -1};

        for (int i = 0; i < rowDir.length; i++) {
            addMovablePositions(board, movablePositions, rowDir[i], colDir[i]);
        }
        return movablePositions;
    }

    private void addMovablePositions(Board board, List<Position> movablePositions, int rowDir, int colDir) {
        int distance = 1;
        while (canMove(board, movablePositions, getPosition().next(rowDir * distance, colDir * distance))) {
            distance++;
        }
    }

    private boolean canMove(Board board, List<Position> movablePositions, Position nextPosition) {
        if (!board.validateRange(nextPosition)) {
            return false;
        }
        if (board.piecesByTeam(getTeam()).containByPosition(nextPosition)) {
            return false;
        }
        if (board.piecesByTeam(Team.enemyTeam(getTeam())).containByPosition(nextPosition)) {
            movablePositions.add(nextPosition);
            return false;
        }
        movablePositions.add(nextPosition);
        return true;
    }
}
