package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import com.qa.ims.persistence.dao.OrdersItemsDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrdersDAO;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
    @Mock
    private Utils utils;

    @Mock
    private OrdersDAO ordersDAO;

    @Mock
    private OrdersItemsDAO ordersitemsDAO;

    @InjectMocks
    private OrderController controller;

    @Test
    public void TestReadAll() {
        final List<Orders> orders = new ArrayList<Orders>();
        final Orders read = new Orders(1l, 1l);
        orders.add(read);

        Mockito.when(ordersDAO.readAll()).thenReturn(orders);

        assertEquals(orders, controller.readAll());
    }

    @Test
    public void TestDelete() {
        final Long ID = 1L;

        Mockito.when(utils.getLong()).thenReturn(ID);

        assertEquals(0, controller.delete());

        Mockito.verify(ordersDAO, Mockito.times(1)).delete(ID);
    }

    @Test
    public void TestCreate() {
        final Long CustID = 3L;
        final Long ItemID = 3L;
        final Orders order = new Orders(CustID, ItemID);

        Mockito.when(utils.getLong()).thenReturn(CustID, ItemID, ItemID);
        Mockito.when(ordersDAO.create(order)).thenReturn(order);
        Mockito.when(utils.getString()).thenReturn("a", "b", "c");
        Mockito.when(ordersDAO.readLatest()).thenReturn(order);

        assertEquals(order, controller.create());
    }

    @Test
    public void TestUpdate() {
        final Long order_id = 2L;
        final Long item_id = 2L;
        final Long customer_id = 2L;
        final Orders order = new Orders(order_id, item_id);

        Mockito.when(utils.getLong()).thenReturn(order_id, item_id, item_id, customer_id);
        Mockito.when(ordersDAO.read(order_id)).thenReturn(order);
        Mockito.when(utils.getString()).thenReturn("ADD", "REMOVE", "CUSTOMER", "abcd", "RETURN");

        assertEquals(null, controller.update());
    }

}