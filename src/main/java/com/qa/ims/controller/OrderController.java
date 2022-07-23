package com.qa.ims.controller;

import java.util.List;

import com.qa.ims.persistence.domain.Orders;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrdersDAO;
import com.qa.ims.persistence.dao.OrdersItemsDAO;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.persistence.domain.OrdersItems;
import com.qa.ims.utils.Utils;

/**
 * Takes in order details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Orders> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrdersDAO ordersDAO;
	private final OrdersItemsDAO ordersItemsDAO;
	private Utils utils;

	public OrderController(OrdersDAO orderDAO, Utils utils) {
		super();
		ordersItemsDAO = new OrdersItemsDAO();
		this.ordersDAO = orderDAO;
		this.utils = utils;
	}

	/**
	 * Reads all orders to the logger
	 */
	@Override
	public List<Orders> readAll() {
		List<Orders> orders = ordersDAO.readAll();
		for (Orders order : orders) {
			LOGGER.info(order.toStringExtended());
		}
		return orders;
	}

	/**
	 * Creates a order by taking in user input
	 */
	@Override
	public Orders create() {
		LOGGER.info("Please enter a customer id");
		Long customer_ID = utils.getLong();
		Orders order = ordersDAO.create(new Orders(customer_ID));
		LOGGER.info("Order created");
		return order;
	}

	/**
	 * Updates an existing order by taking in user input
	 */
	@Override
	public Orders update() {
		LOGGER.info("Please enter a choice:");
		LOGGER.info("1. Add item to order");
		LOGGER.info("2. Delete item from order");
		LOGGER.info("3. Calculate order cost");
		int choice = utils.getInt();
		switch(choice) {
			case 1:
				LOGGER.info("Please enter the id of an order you would like to add an item to");
				Long orderID = utils.getLong();
				LOGGER.info("Please enter an item id to add to the order");
				Long itemID = utils.getLong();
				LOGGER.info("Please enter an item quantity to add to the order");
				Long quantity = utils.getLong();
				ordersItemsDAO.create(new OrdersItems(orderID, itemID, quantity));
				LOGGER.info("Order Updated");
				break;
			case 2:
				LOGGER.info("Please enter the id of an order you would like to delete an item from");
				Long orderDeleteID = utils.getLong();
				LOGGER.info("Please enter an item id to remove to the order");
				Long itemDeleteID = utils.getLong();
				ordersItemsDAO.deleteItem(orderDeleteID, itemDeleteID);
				LOGGER.info("Order Updated");
				break;
			case 3:
				LOGGER.info("Please enter the id of an order you would like to calculate the cost for");
				Long orderCostId = utils.getLong();
				LOGGER.info(ordersItemsDAO.calculateCost(orderCostId));
				break;
		}
		return null;
	}

	/**
	 * Deletes an existing order by the id of the customer
	 *
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = utils.getLong();
		ordersItemsDAO.deleteOrder(id);
		return ordersDAO.delete(id);
	}

}