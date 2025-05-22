package Model;

public class DocumentLine {

    private Game Game;
    private Document Document;
    private double UnitPrice;
    private int Quantity;

    public DocumentLine(Game game, Document document, double unitPrice, int quantity) {
        Game = game;
        Document = document;
        UnitPrice = unitPrice;
        Quantity = quantity;
    }

}
