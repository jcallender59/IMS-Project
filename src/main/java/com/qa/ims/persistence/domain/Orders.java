package com.qa.ims.persistence.domain;

public class Orders {

    private Long ID;
    private Long customerID;
    private String item_name;
    private Double price;
    private Long quantity;

    public Orders(Long customerID) {
        this.setCustomerID(customerID);
    }

    public Orders(Long ID, Long customerID) {
        this.setID(ID);
        this.setCustomerID(customerID);

    }

    public Orders(Long ID, Long customerID, String item_name, Double price, Long quantity) {
        this.setID(ID);
        this.setCustomerID(customerID);
        this.setitem_name(item_name);
        this.setprice(price);
        this.setQuantity(quantity);

    }

    public String getitem_name() {
        return item_name;
    }

    public void setitem_name(String item_name) {
        this.item_name = item_name;
    }

    public Double getprice() {
        return price;
    }

    public void setprice(Double price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        return "ID:" + ID + " customer ID:" + customerID;
    }

    public String toStringExtended() {
        return "ID:" + ID + " customer ID:" + customerID + " item name:" + item_name + " price:£" + price + " quantity:" + quantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ID == null) ? 0 : ID.hashCode());
        result = prime * result + ((customerID == null) ? 0 : customerID.hashCode());
        result = prime * result + ((item_name == null) ? 0 : item_name.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
        return result;
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
        if (ID == null) {
            if (other.ID != null)
                return false;
        } else if (!ID.equals(other.ID))
            return false;
        if (customerID == null) {
            if (other.customerID != null)
                return false;
        } else if (!customerID.equals(other.customerID))
            return false;
        if (item_name == null) {
            if (other.item_name != null)
                return false;
        } else if (!item_name.equals(other.item_name))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (quantity == null) {
            if (other.quantity != null)
                return false;
        } else if (!quantity.equals(other.quantity))
            return false;
        return true;
    }

}