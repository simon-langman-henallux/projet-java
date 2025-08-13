package model;

public class OrderLine {

    private String game;
    private String order;
    private double unitPrice;
    private int quantity;

    public OrderLine(String game, String order, double unitPrice, int quantity) {
        this.game = game;
        this.order = order;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public OrderLine() {}

    public String getGame() {
        return game;
    }

    public String getOrder() {
        return order;
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

    public void setOrder(String order) {
        this.order = order;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}