package dataAccess;

import exception.DataAccessException;
import model.DocumentLine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DocumentLineDAO implements IDocumentLineDAO {

    @Override
    public void insert(DocumentLine line) throws DataAccessException {
        String sql = "INSERT INTO documentline (document, game, quantity, unitPrice) VALUES (?, ?, ?, ?)";
        try (Connection conn = SingletonConnection.getInstance();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, line.getDocument());
            ps.setString(2, line.getGame());
            ps.setInt(3, line.getQuantity());
            ps.setDouble(4, line.getUnitPrice());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}