package business;

import dataAccess.DocumentDAO;
import dataAccess.DocumentLineDAO;
import dataAccess.IDocumentDAO;
import dataAccess.IDocumentLineDAO;
import dataAccess.IGameDAO;
import dataAccess.GameDAO;
import exception.DataAccessException;
import model.Document;
import model.DocumentLine;
import model.Game;
import model.Person;

import java.sql.Date;
import java.sql.SQLException;
import java.util.UUID;

public class OrderManager {
    private final IDocumentDAO documentDAO;
    private final IDocumentLineDAO lineDAO;
    private final IGameDAO gameDAO;

    public OrderManager() {
        this.documentDAO = new DocumentDAO();
        this.lineDAO = new DocumentLineDAO();
        this.gameDAO = new GameDAO();
    }

    public void startOrder(Person client) throws DataAccessException, SQLException {
        if (client == null) {
            throw new DataAccessException("Client is required to start an order.");
        }

        String ref = "ORD-" + UUID.randomUUID();
        Document order = new Document();
        order.setReference(ref);
        order.setDate(new Date(System.currentTimeMillis()));
        order.setPerson(client.getId());
        order.setPaymentMethod("cash");
        order.setType("Sale");
        order.setFinalized(false);

        documentDAO.insert(order);
    }

    public void addItemToOrder(String orderRef, Game game, int quantity, String originalTitle) throws DataAccessException {
        if (orderRef == null || orderRef.isBlank()) {
            throw new DataAccessException("Order reference is required.");
        }
        if (game == null) {
            throw new DataAccessException("Game is required.");
        }
        if (quantity <= 0) {
            throw new DataAccessException("Quantity must be positive.");
        }

        DocumentLine line = new DocumentLine();
        line.setDocument(orderRef);
        line.setGame(game.getTitle());
        line.setQuantity(quantity);
        line.setUnitPrice(game.getPrice());

        lineDAO.insert(line);

        game.setStock(game.getStock() - quantity);
        gameDAO.update(game, originalTitle);
    }

    public void completeOrder(String orderRef) throws DataAccessException {
        if (orderRef == null || orderRef.isBlank()) {
            throw new DataAccessException("Order reference is required.");
        }

        Document doc = documentDAO.getDocumentByReference(orderRef);
        if (doc == null) {
            throw new DataAccessException("Order not found.");
        }

        doc.setFinalized(true);
        documentDAO.update(doc);
    }
}