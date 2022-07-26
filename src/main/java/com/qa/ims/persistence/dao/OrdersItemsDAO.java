package com.qa.ims.persistence.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.qa.ims.persistence.domain.OrdersItems;
import com.qa.ims.utils.DBUtils;

public class OrdersItemsDAO {

    public static final Logger LOGGER = LogManager.getLogger();

    ItemsDAO itemsDAO = new ItemsDAO();
    OrdersDAO ordersDAO = new OrdersDAO();

    public OrdersItems modelFromResultSet(ResultSet resultSet) throws SQLException {
        Long orderId = resultSet.getLong("order_id");
        Long itemId = resultSet.getLong("item_id");
        Long quantity = resultSet.getLong("quantity");
        return new OrdersItems(orderId, itemId, quantity);
    }

    public String modelFromResultSetCost(ResultSet resultSet) throws SQLException {
        return "Order id: " + resultSet.getString("order_ID") + "Total Cost: £" + resultSet.getDouble("cost");
    }


    public OrdersItems readLatest() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM orders_items ORDER BY id DESC LIMIT 1");) {
            resultSet.next();
            return modelFromResultSet(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * Creates an order in the database
     *
     * @param order - takes in an order object. id will be ignored
     */
    public OrdersItems create(OrdersItems ordersItems) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO orders_items(order_id, item_id, quantity) VALUES (?, ?, ?)");) {
            statement.setLong(1, ordersItems.getOrderId());
            statement.setLong(2, ordersItems.getItemId());
            statement.setLong(3, ordersItems.getQuantity());
            statement.executeUpdate();
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * Runs a query to calculate the cost for a particular order in the database
     *
     * @param orderId - The id of the order that the cost needs to be calculated for
     *
     * @return
     */
    public String calculateCost(Long orderId) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT oi.order_id, sum(oi.quantity*i.price) AS cost FROM orders_items oi JOIN items i ON i.item_id = oi.item_id WHERE oi.order_id = ? ORDER BY cost;");) {
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return modelFromResultSetCost(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }




    public int deleteItem(long orderId, long itemId) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM orders_items WHERE order_id = ? AND item_id = ?");) {
            statement.setLong(1, orderId);
            statement.setLong(2, itemId);
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

    /**
     *
     * @param orderId - The id of the order to be deleted
     *
     * @return
     */
    public int deleteOrder(long orderId) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM orders_items WHERE id = ?");) {
            statement.setLong(1, orderId);
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

}