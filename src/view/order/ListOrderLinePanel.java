package view.order;

import controller.OrderLineController;
import model.OrderLine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListOrderLinePanel extends JPanel {

    private final DefaultTableModel model;
    private final OrderLineController controller;
    private final String orderRef;

    public ListOrderLinePanel(OrderLineController controller, String orderRef) {
        this.controller = controller;
        this.orderRef = orderRef;

        setLayout(new BorderLayout());

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Game", "Order Ref", "Unit Price", "Quantity"});
        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        JButton btnAddLine = new JButton("Add Line");
        JButton btnExit = new JButton("Exit");
        bottom.add(btnAddLine);
        bottom.add(btnExit);
        add(bottom, BorderLayout.SOUTH);

        btnExit.addActionListener(e -> {
            Window w = SwingUtilities.getWindowAncestor(this);
            if (w != null) w.dispose();
        });

        btnAddLine.addActionListener(e -> {
            JFrame frame = new JFrame("Add Order Line");
            frame.setContentPane(new AddOrderLinePanel(controller, orderRef));
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        loadData();
    }

    private void loadData() {
        model.setRowCount(0);
        List<OrderLine> lines = controller.getLinesByOrder(orderRef);
        for (OrderLine line : lines) {
            model.addRow(new Object[]{
                    line.getGame(),
                    line.getOrder(),
                    line.getUnitPrice(),
                    line.getQuantity()
            });
        }
    }
}