package business;

import dataAccess.IDocumentDAO;
import dataAccess.DocumentDAO;
import exception.DataAccessException;
import model.Document;

import java.sql.SQLException;

public class DocumentService {

    private final IDocumentDAO documentDAO;

    public DocumentService() {
        this.documentDAO = new DocumentDAO();
    }

    public void create(Document doc) throws DataAccessException {
        validate(doc);
        documentDAO.insert(doc);
    }

    public void finalize(String reference) throws DataAccessException {
        if (reference == null || reference.isBlank()) {
            throw new DataAccessException("Document reference is required.");
        }

        Document doc = documentDAO.getDocumentByReference(reference);
        if (doc == null) {
            throw new DataAccessException("Document not found.");
        }

        doc.setFinalized(true);
        documentDAO.finalize(doc);
    }

    private void validate(Document doc) throws DataAccessException {
        if (doc.getReference() == null || doc.getReference().isBlank()) {
            throw new DataAccessException("Reference is required.");
        }
        if (doc.getDate() == null) {
            throw new DataAccessException("Date is required.");
        }
        if (doc.getPaymentMethod() == null || doc.getPaymentMethod().isBlank()) {
            throw new DataAccessException("Payment method is required.");
        }
        if (doc.getType() == null || doc.getType().isBlank()) {
            throw new DataAccessException("Type is required.");
        }
    }

}