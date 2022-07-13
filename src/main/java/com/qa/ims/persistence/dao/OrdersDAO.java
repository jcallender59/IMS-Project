package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DBUtils;

public class OrdersDAO implements Dao<Orders> {

    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Orders modelFromResultSet(ResultSet resultSet) throws SQLException {
        Long OrderID = resultSet.getLong("OrderID");
        Long customer_ID = resultSet.getLong("CustomerID");
        return new Orders(OrderID, customer_ID);
    }

    public Orders modelFromResultSetWithItems(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("oi.fk_order_id");
        long customerId = resultSet.getLong("o.fk_customer_id");
        String item_name = resultSet.getString("i.item_name");
        Double value = resultSet.getDouble("i.value");
        long quantity = resultSet.getLong("oi.quantity");
        return new Orders(id, customerId, item_name, value, quantity);
    }

    /**
     * Reads all orders from the database and items associated with them
     *
     * @return A list of orders including items
     */
    @Override
    public List<Orders> readAll() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement
                     .executeQuery("SELECT oi.fk_order_id, o.fk_customer_id, i.item_name, i.value, oi.quantity FROM orders_items oi JOIN orders o ON oi.fk_order_id = o.id JOIN items i ON i.id = oi.fk_item_id;");) {
            List<Orders> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(modelFromResultSetWithItems(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Orders readLatest() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");) {
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
    @Override
    public Orders create(Orders order) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO orders(Customer_ID) VALUES (?)");) {
            statement.setLong(1, orders.CustomerID());
            statement.executeUpdate();
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Orders read(Long id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE id = ?");) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery();) {
                resultSet.next();
                return modelFromResultSet(resultSet);
            }
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * Omitted update method (unused method implemented from DAO)
     */
    @Override
    public Orders update(Orders orders) {
        return null;
    }

    /**
     * Deletes an order in the database
     *
     * @param id - id of the order
     */
    @Override
    public int delete(long id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE id = ?");) {
            statement.setLong(1, id);
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

}