package model;

public class DocumentLine {

    private String game;
    private String document;
    private double unitPrice;
    private int quantity;

    public DocumentLine(String game, String document, double unitPrice, int quantity) {
        game = game;
        document = document;
        unitPrice = unitPrice;
        quantity = quantity;
    }

}
