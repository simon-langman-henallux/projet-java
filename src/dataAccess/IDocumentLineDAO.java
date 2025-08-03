package dataAccess;

import exception.DataAccessException;
import model.DocumentLine;

public interface IDocumentLineDAO {
    void insert(DocumentLine line) throws DataAccessException;
}