package chess;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.position.Position;

public class ChessGame {

    private Board board;

    public void initSetting() {
        BoardFactory boardFactory = new BoardFactory();
        board = boardFactory.getBoard();
    }

    public Board getBoard() {
        return board;
    }

    public void move(final Position startPoint, final Position endPoint, final Team team) {
        board.move(startPoint, endPoint, team);
    }

}