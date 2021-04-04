package chess.dao;

import chess.dto.UserDTO;
import chess.dto.UsersDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static chess.dao.ConnectDB.CONNECTION;

public class UserDAO {

    public UsersDTO findByRoomId(final String roomId) throws SQLException {
        String query = "SELECT black.nickname AS black_user, white.nickname AS white_user " +
                "FROM room JOIN user as black on black.id = room.black_user " +
                "JOIN user as white on white.id = room.white_user " +
                "WHERE room.id = ?";
        PreparedStatement statement = CONNECTION.prepareStatement(query);
        statement.setString(1, roomId);
        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next()) {
            return null;
        }
        UsersDTO usersDTO = new UsersDTO(
                resultSet.getString("black_user"),
                resultSet.getString("white_user")
        );
        statement.close();
        return usersDTO;
    }

    public int findUserIdByNickname(final String nickname) throws SQLException {
        String query = "SELECT id FROM user WHERE nickname = ?";
        PreparedStatement statement = CONNECTION.prepareStatement(query);
        statement.setString(1, nickname);
        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next()) {
            return 0;
        }

        int id = resultSet.getInt("id");
        statement.close();
        return id;
    }

    public List<UserDTO> findAll() throws SQLException {
        String query = "SELECT * from user";
        PreparedStatement statement = CONNECTION.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        List<UserDTO> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(new UserDTO(
                    resultSet.getInt("id"),
                    resultSet.getString("nickname")
            ));
        }
        statement.close();
        return users;
    }

    public String findNicknameById(final int id) throws SQLException {
        String query = "SELECT nickname FROM user WHERE id = ?";
        ;
        PreparedStatement statement = CONNECTION.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next()) {
            return null;
        }

        String nickname = resultSet.getString("nickname");
        statement.close();
        return nickname;
    }
}
