package controller;

import business.DocumentService;
import exception.DataAccessException;
import model.Document;

public class DocumentController {
    private final DocumentService service = new DocumentService();

    public void createDocument(Document doc) throws DataAccessException {
        service.create(doc);
    }

    public void finalizeDocument(String reference) throws DataAccessException {
        service.finalize(reference);
    }

    public DocumentService getService() {
        return service;
    }

}