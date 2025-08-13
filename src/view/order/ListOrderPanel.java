package view.order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import controller.OrderController;
import controller.GameController;
import controller.OrderLineController;
import controller.PersonController;
import model.Order;

public class ListOrderPanel extends JPanel {

    private final DefaultTableModel model;
    private final OrderController orderController;

    public ListOrderPanel(OrderController orderController, GameController gameController, PersonController personController) {

        this.orderController = orderController;


        setLayout(new BorderLayout());

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[] {"Reference", "Date", "Payment Method", "Finalized", "Type", "Person"});
        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        JButton btnCreate = new JButton("Create Order");
        JButton btnAddLines = new JButton("Add Lines");
        JButton btnDelete = new JButton("Delete Order");
        JButton btnExit = new JButton("Exit");
        bottom.add(btnCreate);
        bottom.add(btnAddLines);
        bottom.add(btnDelete);
        bottom.add(btnExit);
        add(bottom, BorderLayout.SOUTH);

        btnAddLines.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String ref = (String) model.getValueAt(row, 0);
                model.Order o = orderController.get(ref);
                if (o != null) {
                    JFrame f = new JFrame("Add Lines: " + ref);
                    f.setContentPane(new AddOrderLinePanel(o, gameController));
                    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    f.pack();
                    f.setLocationRelativeTo(null);
                    f.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select an order first.");
            }
        });

        loadData();

        btnExit.addActionListener(e -> {
            Window w = SwingUtilities.getWindowAncestor(this);
            if (w != null) w.dispose();
        });

        btnCreate.addActionListener(e -> {
            JFrame frame = new JFrame("Create Order");
            frame.setContentPane(new CreateOrderPanel(orderController, gameController, personController));
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String ref = (String) model.getValueAt(row, 0);
                int c = JOptionPane.showConfirmDialog(this, "Delete order " + ref + " ?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (c == JOptionPane.YES_OPTION) {
                    orderController.remove(ref);
                    loadData();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select an order to delete.");
            }
        });

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        String ref = (String) model.getValueAt(row, 0);
                        JFrame frame = new JFrame("Order Lines for " + ref);
                        frame.setContentPane(new ListOrderLinePanel(new OrderLineController(), ref));
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                }
            }
        });

    }

    private void loadData() {
        model.setRowCount(0);
        List<Order> orders = orderController.getAll();
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