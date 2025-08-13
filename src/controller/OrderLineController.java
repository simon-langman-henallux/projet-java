package controller;

import java.util.List;
import model.OrderLine;
import business.OrderLineService;

public class OrderLineController {

    private OrderLineService service;

    public OrderLineController() {
        this.service = new OrderLineService();
    }

    public void addLine(OrderLine line) {
        service.add(line);
    }

    public void deleteLine(String game, String orderRef) {
        service.delete(game, orderRef);
    }

    public List<OrderLine> getLinesByOrder(String orderRef) {
        return service.getByOrder(orderRef);
    }
}