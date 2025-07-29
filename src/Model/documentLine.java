package Model;

public class DocumentLine {

    private Game game;
    private Document document;
    private double unitPrice;
    private int quantity;

    public DocumentLine(Game game, Document document, double unitPrice, int quantity) {
        game = game;
        document = document;
        unitPrice = unitPrice;
        quantity = quantity;
    }

}
