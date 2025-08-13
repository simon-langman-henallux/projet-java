package dataAccess.order;

import java.util.List;
import model.Order;

public interface IOrderDAO {
    void insert(Order order);
    void update(Order order);
    void delete(String reference);
    Order getByReference(String reference);
    List<Order> getAll();
}