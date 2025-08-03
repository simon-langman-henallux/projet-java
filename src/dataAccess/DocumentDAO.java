package dataAccess;

import exception.DataAccessException;
import model.Document;
import model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentDAO implements IDocumentDAO {

    Connection conn;

    public DocumentDAO() throws DataAccessException {
        try {
            conn = dataAccess.SingletonConnection.getInstance();
        } catch (SQLException e) {
            throw new DataAccessException("DocumentDAO Connections to Database Error");
        }
    }
    @Override
    public void insert(Document doc) throws SQLException {
        String sql = "insert into document (reference, date, paymentMethod, isFinalized, type, person) values (?, ?, ?, ?, ?, ?);";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, doc.getReference());
            ps.setDate(2, new java.sql.Date(doc.getDate().getTime()));
            ps.setString(3, doc.getPaymentMethod());
            ps.setObject(4, doc.isFinalized(), Types.BOOLEAN);
            ps.setString(5, doc.getType());
            ps.setInt(6, doc.getPerson());
            int affected = ps.executeUpdate();
            if (affected == 0) {
                System.out.println("No rows affected");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Insert Document Error");
        }
    }
    public Document getDocumentByReference(String ref) throws DataAccessException {
        String sql = "select * from document where reference = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ref);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                } else return null;
            }
        } catch  (SQLException e) {
            throw new DataAccessException("Get Document By Reference Error");
        }
    }
    @Override
    public List<Document> getAllDocument() throws DataAccessException {
        List<Document> documents = new ArrayList<>();
        String sql = "select * from document order by reference";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                documents.add(map(rs));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Get All Document Error");
        }
        return documents;
    }
    @Override
    public void update(Document doc) throws SQLException {
        String sql = "update document set date=?, paymentMethod=?, isFinalized=?, type=?, person=? where reference=?";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(doc.getDate().getTime()));
            ps.setString(2, doc.getPaymentMethod());
            ps.setBoolean(3, doc.isFinalized());
            ps.setString(4, doc.getType());
            ps.setInt(5, doc.getPerson());
            ps.setString(6, doc.getReference());
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new DataAccessException("No rows affected");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Update Document Error");
        }
    }
    @Override
    public void delete(String reference) throws SQLException {
        String sql = "delete from document where reference = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, reference);
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new DataAccessException("No rows affected");
            }
        }  catch (SQLException e) {
            throw new DataAccessException("Delete Document Error");
        }
    }
    private Document map(ResultSet rs) throws SQLException {
        return new Document(
                rs.getString("reference"),
                rs.getDate("date"),
                rs.getString("paymentMethod"),
                rs.getBoolean("isFinalized"),
                rs.getString("type"),
                rs.getInt("person")
        );

    }
}