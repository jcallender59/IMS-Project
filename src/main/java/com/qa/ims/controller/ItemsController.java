package com.qa.ims.controller;

import java.util.List;

import com.qa.ims.persistence.domain.Items;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemsDAO;
import com.qa.ims.utils.Utils;

/**
 * Takes in items details for CRUD functionality
 *
 */
public class ItemsController implements CrudController<Items> {

	public static final Logger LOGGER = LogManager.getLogger();

	private ItemsDAO itemsDAO;
	private Utils utils;

	public ItemsController(ItemsDAO itemsDAO, Utils utils) {
		super();
		this.itemsDAO = itemsDAO;
		this.utils = utils;
	}

	/**
	 * Reads all items to the logger
	 */
	@Override
	public List<Items> readAll() {
		List<Items> items = ItemsDAO.readAll();
		for (Items items : items) {
			LOGGER.info(items);
		}
		return items;
	}

	/**
	 * Creates a items by taking in user input
	 */
	@Override
	public com.qa.ims.persistence.dao.ItemsDAO create() {
		LOGGER.info("Please enter an item name");
		String item_name = Utils.getString();
		LOGGER.info("Please enter a price");
		Double price= Utils.getDouble();
		com.qa.ims.persistence.dao.ItemsDAO items = ItemsDAO.create(new Items(item_name, price));
		LOGGER.info("items created");
		return items;
	}

	/**
	 * Updates an existing items by taking in user input
	 */
	@Override
	public com.qa.ims.persistence.dao.ItemsDAO update() {
		LOGGER.info("Please enter the id of the items you would like to update");
		Long ItemID = utils.getLong();
		LOGGER.info("Please enter an item name");
		String firstName = utils.getString();
		LOGGER.info("Please enter a price");
		Double price = utils.getDouble();
		com.qa.ims.persistence.dao.ItemsDAO items = itemsDAO.update(new items(id, item_name, price));
		LOGGER.info("items Updated");
		return items;
	}

	/**
	 * Deletes an existing items by the id of the items
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the items you would like to delete");
		Long ItemID = utils.getLong();
		return itemsDAO.delete(id);
	}

}



