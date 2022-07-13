package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemsDAO;
import com.qa.ims.persistence.domain.Items;
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
		this.itemsDAO = ItemsDAO;
		this.utils = utils;
	}

	/**
	 * Reads all items to the logger
	 */
	@Override
	public List<Items> readAll() {
		List<Items> items = ItemsDAO.readAll();
		for (Items item : items) {
			LOGGER.info(items);
		}
		return items;
	}

	/**
	 * Creates a items by taking in user input
	 */
	@Override
	public Items create() {
		LOGGER.info("Please enter an item name");
		String item_name = utils.getString();
		LOGGER.info("Please enter a price");
		Double price= utils.getDouble();
		Items items = itemsDAO.create(new items(item_name, price));
		LOGGER.info("items created");
		return items;
	}

	/**
	 * Updates an existing items by taking in user input
	 */
	@Override
	public items update() {
		LOGGER.info("Please enter the id of the items you would like to update");
		Long ItemID = utils.getLong();
		LOGGER.info("Please enter an item name");
		String firstName = utils.getString();
		LOGGER.info("Please enter a price");
		Double price = utils.getDouble();
		items items = itemsDAO.update(new items(id, item_name, price));
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



