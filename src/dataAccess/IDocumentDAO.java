package dataAccess;

import exception.DataAccessException;
import model.Document;
import java.util.List;

public interface IDocumentDAO {
    void insert(Document doc) throws DataAccessException;
    Document getDocumentByReference(String ref) throws DataAccessException;
    List<Document> getAllDocument() throws DataAccessException;
    void update(Document doc) throws DataAccessException;
    void delete(String ref) throws DataAccessException;
    void finalize(Document doc) throws DataAccessException;
}