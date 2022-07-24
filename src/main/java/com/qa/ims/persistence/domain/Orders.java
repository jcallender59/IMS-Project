package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Orders {

    private long order_id;
    private long customerId;

    public Orders(long customerId) {
        this.setCustomerId(customerId);
    }

    public Orders(long order_id, long customerId) {
        super();
        this.order_id = order_id;
        this.customerId = customerId;
    }

    public long getorder_id() {
        return order_id;
    }

    public void setId(long order_id) {
        this.order_id = order_id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }


    @Override
    public String toString() {
        return "Order [id=" + order_id + ", customer_id=" + customerId + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, order_id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Orders other = (Orders) obj;
        return customerId == other.customerId && order_id == other.order_id;
    }




}