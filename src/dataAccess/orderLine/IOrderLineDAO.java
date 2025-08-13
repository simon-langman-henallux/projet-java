package dataAccess.orderLine;

import model.OrderLine;
import java.util.List;

public interface IOrderLineDAO {
    void insert(OrderLine orderLine);
    void delete(String game, String order);
    List<OrderLine> getByOrder(String order);
    List<OrderLine> getAll();
}