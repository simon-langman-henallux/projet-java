package dataAccess;

import model.Document;
import java.sql.SQLException;
import java.util.List;

public class DocumentDAO implements IDocumentDAO {
    public void insert(Document doc) throws SQLException {}
    public Document find(String reference) throws SQLException { return null; }
    public List<Document> findAll() throws SQLException { return null; }
    public void update(Document doc) throws SQLException {}
    public void delete(String reference) throws SQLException {}
}