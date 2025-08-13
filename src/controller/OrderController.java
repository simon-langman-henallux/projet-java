package controller;

import java.util.List;
import model.Order;
import business.OrderService;

public class OrderController {

    private OrderService service;

    public OrderController() {
        this.service = new OrderService();
    }

    public void add(Order order) {
        service.add(order);
    }

    public void update(Order order) {
        service.update(order);
    }

    public void delete(String reference) {
        service.delete(reference);
    }

    public Order get(String reference) {
        return service.get(reference);
    }

    public List<Order> getAll() {
        return service.getAll();
    }

    public void remove(String reference) {
        service.remove(reference);
    }

}