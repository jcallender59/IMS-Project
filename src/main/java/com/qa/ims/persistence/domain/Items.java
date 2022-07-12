package com.qa.ims.persistence.domain;

public class Items {

    private Long ItemID;
    private String item_name;
    private Double price;

    public Items(String item_name, Double price) {
        this.setitem_name(item_name);
        this.setPrice(price);
    }

    public Items(Long ItemID, String item_name, Double price) {
        this.setItemID(ItemID);
        this.setitem_name(item_name);
        this.setPrice(price);
    }

    public Long getItemID() {
        return ItemID;
    }

    public void setItemID(Long ItemID) {
        this.ItemID = ItemID;
    }

    public String getitem_name() {
        return item_name;
    }

    public void setitem_name(String item_name) {
        this.item_name = item_name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ItemID:" + ItemID + " Item:" + item_name + " price:" + price;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((item_name == null) ? 0 : item_name.hashCode());
        result = prime * result + ((ItemID == null) ? 0 : itemID.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
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
        Items other = (Items) obj;
        if (getitem_name() == null) {
            if (other.getitem_name() != null)
                return false;
        } else if (!getitem_name().equals(other.getitem_name()))
            return false;
        if (ItemID == null) {
            if (other.ItemID != null)
                return false;
        } else if (!ItemID.equals(other.ItemID))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        return true;
    }


}




