package model;

public class DocumentLine {

    private String game;
    private String document;
    private double unitPrice;
    private int quantity;

    public DocumentLine(String game, String document, double unitPrice, int quantity) {
        this.game = game;
        this.document = document;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public DocumentLine() {}

    public String getGame() {
        return game;
    }

    public String getDocument() {
        return document;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
