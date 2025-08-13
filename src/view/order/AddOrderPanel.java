package view.order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import model.Order;
import controller.OrderController;

public class AddOrderPanel extends JPanel {

    private final JTextField referenceField;
    private final JTextField dateField;
    private final JTextField paymentMethodField;
    private final JCheckBox isFinalizedField;
    private final JTextField typeField;
    private final JTextField personField;

    public AddOrderPanel(OrderController controller) {

        setLayout(new GridLayout(6, 2));

        add(new JLabel("Reference:"));
        referenceField = new JTextField();
        add(referenceField);

        add(new JLabel("Date (yyyy-mm-dd):"));
        dateField = new JTextField();
        add(dateField);

        add(new JLabel("Payment Method:"));
        paymentMethodField = new JTextField();
        add(paymentMethodField);

        add(new JLabel("Finalized:"));
        isFinalizedField = new JCheckBox();
        add(isFinalizedField);

        add(new JLabel("Type:"));
        typeField = new JTextField();
        add(typeField);

        add(new JLabel("Person ID:"));
        personField = new JTextField();
        add(personField);

        JButton saveBtn = new JButton("Save");
        add(saveBtn);

        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Order order = new Order();
                    order.setReference(referenceField.getText());
                    order.setDate(java.sql.Date.valueOf(dateField.getText()));
                    order.setPaymentMethod(paymentMethodField.getText());
                    order.setFinalized(isFinalizedField.isSelected());
                    order.setType(typeField.getText());
                    order.setPerson(Integer.parseInt(personField.getText()));

                    controller.add(order);

                    JOptionPane.showMessageDialog(AddOrderPanel.this, "Order added");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AddOrderPanel.this, "Error: " + ex.getMessage());
                }
            }
        });
    }
}