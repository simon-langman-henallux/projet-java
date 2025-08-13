package business;

import java.util.List;
import model.Order;
import dataAccess.order.IOrderDAO;
import dataAccess.order.OrderDAO;

public class OrderService {

    private IOrderDAO dao;

    public OrderService() {
        this.dao = new OrderDAO();
    }

    public void add(Order order) {
        dao.insert(order);
    }

    public void update(Order order) {
        dao.update(order);
    }

    public void delete(String reference) {
        dao.delete(reference);
    }

    public Order get(String reference) {
        return dao.getByReference(reference);
    }

    public List<Order> getAll() {
        return dao.getAll();
    }
}