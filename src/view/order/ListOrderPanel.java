package view.order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import model.Order;
import controller.OrderController;

public class ListOrderPanel extends JPanel {

    private final DefaultTableModel model;
    private final OrderController controller;

    public ListOrderPanel(OrderController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[] {
                "reference", "date creation", "payment method", "finalized", "type", "name person", ""
        });

        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadData();
    }

    private void loadData() {
        model.setRowCount(0);
        List<Order> orders = controller.getAll();
        for (Order o : orders) {
            model.addRow(new Object[] {
                    o.getReference(),
                    o.getDate(),
                    o.getPaymentMethod(),
                    o.isFinalized(),
                    o.getType(),
                    o.getPerson()
            });
        }
    }
}