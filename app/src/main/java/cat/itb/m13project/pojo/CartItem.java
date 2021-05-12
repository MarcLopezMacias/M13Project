package cat.itb.m13project.pojo;

import java.io.Serializable;

public class CartItem implements Serializable {

    String name;
    int amount;
    double price;
    String currency;
    String SKU;

    public CartItem() {
    }

    public CartItem(String name, int amount, double price, String currency, String SKU) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.currency = currency;
        this.SKU = SKU;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }
}
