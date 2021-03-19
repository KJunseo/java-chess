package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.position.Position;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public abstract class Piece {
    protected Position position;
    private final Team team;
    private final String initial;

    public Piece(final Position position, final String initial, final Team team) {
        this.position = position;
        this.initial = checkTeam(team, initial);
        this.team = team;
    }

    private String checkTeam(final Team team, final String initial) {
        if (team.equals(Team.WHITE)) {
            return initial.toLowerCase(Locale.ROOT);
        }
        return initial.toUpperCase(Locale.ROOT);
    }

    public String getInitial() {
        return initial;
    }

    public Position getPosition() {
        return position;
    }

    public boolean samePosition(final Position startPoint) {
        return this.position.equals(startPoint);
    };

    public abstract void move(Map<Team, List<Piece>> board, Position endPoint);
}
