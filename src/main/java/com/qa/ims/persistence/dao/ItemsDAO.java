package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qa.ims.persistence.domain.Items;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.utils.DBUtils;

public class ItemsDAO implements Dao<ItemsDAO> {

    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Items modelFromResultSet(ResultSet resultSet) throws SQLException {
        Long ItemID = resultSet.getLong("ItemID");
        String item_name = resultSet.getString("item_name");
        Double price = resultSet.getDouble("price");
        return new Items(ItemID, item_name, price);
    }

    /**
     * Reads all items from the database
     *
     * @return A list of items
     */
    @Override
    public List<ItemsDAO> readAll() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM items");) {
            List<Items> Items = new ArrayList<>();
            while (resultSet.next()) {
                Items.add(modelFromResultSet(resultSet));
            }
            return Items;
        } catch (SQLException e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Items readLatest() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM items ORDER BY id DESC LIMIT 1");) {
            resultSet.next();
            return modelFromResultSet(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * Creates a items in the database
     *
     * @param items - takes in a items object. id will be ignored
     */
    @Override
    public Items create(Items items) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO items(first_name, surname) VALUES (?, ?)");) {
            statement.setLong(1, items.getItemID();
            statement.setString(2, items.getitem_name();
            statement.setDouble(3, items.getprice());
            statement.executeUpdate();
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Items read(Long itemID) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM items WHERE id = ?");) {
            statement.setLong(1, itemID);
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
     * Updates a items in the database
     *
     * @param items - takes in a items object, the id field will be used to
     *                 update that items in the database
     * @return
     */
    @Override
    public Items update(Items items) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("UPDATE items SET first_name = ?, surname = ? WHERE id = ?");) {
            statement.setLong(1, items.getItemID());
            statement.setString(2, items.getItemName());
            statement.setDouble(3, items.getItemPrice());
            statement.executeUpdate();
            return read(items.getItemId());
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * Deletes a items in the database
     *
     * @param id - id of the items
     */
    @Override
    public int delete(long itemID) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM items WHERE id = ?");) {
            statement.setLong(1, id);
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

}
