package business;

import java.util.List;
import model.OrderLine;
import dataAccess.order.OrderLineDAO;

public class OrderLineService {

    private OrderLineDAO dao;

    public OrderLineService() {
        this.dao = new OrderLineDAO();
    }

    public void add(OrderLine line) {
        dao.insert(line);
    }

    public void delete(String game, String orderRef) {
        dao.delete(game, orderRef);
    }

    public List<OrderLine> getByOrder(String orderRef) {
        return dao.getByOrder(orderRef);
    }
}