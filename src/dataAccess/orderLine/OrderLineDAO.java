package dataAccess.order;

import dataAccess.SingletonConnection;
import dataAccess.orderLine.IOrderLineDAO;
import model.OrderLine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderLineDAO implements IOrderLineDAO {

    private Connection conn;

    public OrderLineDAO() {
        try {
            this.conn = SingletonConnection.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(OrderLine orderLine) {
        String sql = "insert into orderLine (game, `order`, unitPrice, quantity) values (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, orderLine.getGame());
            stmt.setString(2, orderLine.getOrder());
            stmt.setDouble(3, orderLine.getUnitPrice());
            stmt.setInt(4, orderLine.getQuantity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String game, String order) {
        String sql = "delete from orderLine where game=? and `order`=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, game);
            stmt.setString(2, order);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<OrderLine> getByOrder(String order) {
        List<OrderLine> list = new ArrayList<>();
        String sql = "select * from orderLine where `order`=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, order);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrderLine ol = new OrderLine();
                ol.setGame(rs.getString("game"));
                ol.setOrder(rs.getString("order"));
                ol.setUnitPrice(rs.getDouble("unitPrice"));
                ol.setQuantity(rs.getInt("quantity"));
                list.add(ol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<OrderLine> getAll() {
        List<OrderLine> list = new ArrayList<>();
        String sql = "select * from orderLine";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                OrderLine ol = new OrderLine();
                ol.setGame(rs.getString("game"));
                ol.setOrder(rs.getString("order"));
                ol.setUnitPrice(rs.getDouble("unitPrice"));
                ol.setQuantity(rs.getInt("quantity"));
                list.add(ol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean exists(String game, String orderRef) {
        String sql = "select 1 from orderLine where game=? and `order`=? limit 1";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, game);
            st.setString(2, orderRef);
            try (ResultSet rs = st.executeQuery()) { return rs.next(); }
        } catch (SQLException e) { throw new RuntimeException(e.getMessage()); }
    }

    public void updateQuantity(String game, String orderRef, int addQty) {
        String sql = "update orderLine set quantity = quantity + ? where game=? and `order`=?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, addQty);
            st.setString(2, game);
            st.setString(3, orderRef);
            st.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e.getMessage()); }
    }


}