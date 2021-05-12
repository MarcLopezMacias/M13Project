package cat.itb.m13project.pojo;

import com.paypal.android.sdk.payments.PayPalItem;

import java.math.BigDecimal;
import java.util.List;

public class Cart {

    List<CartItem> cart;

    public Cart(List<CartItem> cart) {
        this.cart = cart;
    }

    public Cart() {
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

    public double getTotal() {
        double total = 0;
        for (int i = 0; i < cart.size(); i++) {
            total += (cart.get(i).getAmount() * cart.get(i).getPrice());
        }
        return total;
    }

    public PayPalItem[] getItems() {
        PayPalItem[] items = new PayPalItem[cart.size()];
        for (int i = 0; i < cart.size(); i++) {
            PayPalItem item = new PayPalItem(
                    cart.get(i).getName(),
                    cart.get(i).getAmount(),
                    BigDecimal.valueOf(cart.get(i).getPrice()),
                    cart.get(i).getCurrency(),
                    cart.get(i).getSKU());
            items[i] = item;
        }
        return items;
    }

}
