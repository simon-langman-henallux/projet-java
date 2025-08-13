package dataAccess.order;

import dataAccess.SingletonConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Order;

public class OrderDAO implements IOrderDAO {

    private Connection conn;

    public OrderDAO() {
        try {
            this.conn = SingletonConnection.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(Order order) {
        String sql = "insert into `order` (reference, date, paymentMethod, isFinalized, type, person) values (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, order.getReference());
            stmt.setDate(2, new java.sql.Date(order.getDate().getTime()));
            stmt.setString(3, order.getPaymentMethod());
            stmt.setBoolean(4, order.isFinalized());
            stmt.setString(5, order.getType());
            stmt.setInt(6, order.getPerson());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Order order) {
        String sql = "update `order` set date=?, paymentMethod=?, isFinalized=?, type=?, person=? where reference=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(order.getDate().getTime()));
            stmt.setString(2, order.getPaymentMethod());
            stmt.setBoolean(3, order.isFinalized());
            stmt.setString(4, order.getType());
            stmt.setInt(5, order.getPerson());
            stmt.setString(6, order.getReference());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String reference) {
        String sql = "delete from `order` where reference=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, reference);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Order getByReference(String reference) {
        String sql = "select * from `order` where reference=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, reference);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Order o = new Order();
                o.setReference(rs.getString("reference"));
                o.setDate(rs.getDate("date"));
                o.setPaymentMethod(rs.getString("paymentMethod"));
                o.setFinalized(rs.getBoolean("isFinalized"));
                o.setType(rs.getString("type"));
                o.setPerson(rs.getInt("person"));
                return o;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Order> getAll() {
        List<Order> list = new ArrayList<>();
        String sql = "select * from `order`";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Order o = new Order();
                o.setReference(rs.getString("reference"));
                o.setDate(rs.getDate("date"));
                o.setPaymentMethod(rs.getString("paymentMethod"));
                o.setFinalized(rs.getBoolean("isFinalized"));
                o.setType(rs.getString("type"));
                o.setPerson(rs.getInt("person"));
                list.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}