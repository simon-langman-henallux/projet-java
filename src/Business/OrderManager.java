// Business/OrderManager.java
package Business;

import Model.Person;
import Model.Game;
import java.sql.SQLException;

public class OrderManager {
    public void startOrder(Person client) throws SQLException {}
    public void addItemToOrder(String orderRef, Game game, int quantity) throws SQLException {}
    public void completeOrder(String orderRef) throws SQLException {}
}