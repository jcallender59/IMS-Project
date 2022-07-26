package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.ItemsController;
import com.qa.ims.persistence.dao.ItemsDAO;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)

public class ItemControllerTest {

    @Mock
    private Utils utils;

    @Mock
    private ItemsDAO DAO;

    @InjectMocks
    private ItemsController controller;

    @Test
    public void TestCreate() {
        final String name = "Beans";
        final Double price = 1.50;
        final Items created = new Items(name, price);

        Mockito.when(utils.getString()).thenReturn(name);
        Mockito.when(utils.getDouble()).thenReturn(price);
        Mockito.when(DAO.create(created)).thenReturn(created);

        assertEquals(created, controller.create());

        Mockito.verify(utils, Mockito.times(1)).getString();
        Mockito.verify(utils, Mockito.times(1)).getDouble();
        Mockito.verify(DAO, Mockito.times(1)).create(created);

    }

    @Test
    public void TestUpdate() {
        final Long id = 4L;
        final String title = "Cola";
        final Double price = 1.20D;
        final Items updated = new Items(id, title, price);

        Mockito.when(utils.getLong()).thenReturn(id);
        Mockito.when(utils.getString()).thenReturn(title);
        Mockito.when(utils.getDouble()).thenReturn(price);
        Mockito.when(DAO.update(updated)).thenReturn(updated);

        assertEquals(updated, controller.update());

        Mockito.verify(utils, Mockito.times(1)).getLong();
        Mockito.verify(utils, Mockito.times(1)).getString();
        Mockito.verify(utils, Mockito.times(1)).getDouble();
        Mockito.verify(DAO, Mockito.times(1)).update(updated);
    }

    @Test
    public void TestDelete() {
        final Long id = 5L;

        Mockito.when(utils.getLong()).thenReturn(id);
        Mockito.when(DAO.delete(id)).thenReturn(0);

        assertEquals(0, controller.delete());

        Mockito.verify(utils, Mockito.times(1)).getLong();
        Mockito.verify(DAO, Mockito.times(1)).delete(id);
    }

    @Test
    public void TestReadAll() {
        final List<Items> items = new ArrayList<Items>();
        final Items read = new Items(1l, "bananas", 1.35D);
        items.add(read);

        Mockito.when(DAO.readAll()).thenReturn(items);

        assertEquals(items, controller.readAll());

        Mockito.verify(DAO, Mockito.times(1)).readAll();
    }
}