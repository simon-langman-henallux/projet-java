package dataAccess;

import model.Document;
import java.sql.SQLException;
import java.util.List;

public interface IDocumentDAO {
    void insert(Document doc) throws SQLException;
    Document find(String reference) throws SQLException;
    List<Document> findAll() throws SQLException;
    void update(Document doc) throws SQLException;
    void delete(String reference) throws SQLException;
}