package dataAccess;

import model.Document;
import java.sql.SQLException;
import java.util.List;

public interface IDocumentDAO {
    void insert(Document doc) throws SQLException;
    Document getDocumentByReference(String ref) throws SQLException;
    List<Document> getAllDocument() throws SQLException;
    void update(Document doc) throws SQLException;
    void delete(String ref) throws SQLException;
}