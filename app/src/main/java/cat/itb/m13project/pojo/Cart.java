package cat.itb.m13project.pojo;

import com.paypal.android.sdk.payments.PayPalItem;

import java.math.BigDecimal;
import java.util.List;

import static cat.itb.m13project.ConstantVariables.CURRENCY;
import static cat.itb.m13project.ConstantVariables.DEFAULT_AMOUNT;

public class Cart {

    List<Producto> carrito;

    public Cart(List<Producto> carrito) {
        this.carrito = carrito;
    }

    public Cart() {
    }

    public List<Producto> getCarrito() {
        return carrito;
    }

    public void setCarrito(List<Producto> carrito) {
        this.carrito = carrito;
    }

    public double getTotal() {
        double total = 0;
        for (int i = 0; i < carrito.size(); i++) {
            total += (carrito.get(i).getPrecioFinalProveedor());
        }
        return total;
    }

    public PayPalItem[] getItems() {
        PayPalItem[] productos = new PayPalItem[carrito.size()];
        for (int i = 0; i < carrito.size(); i++) {
            PayPalItem producto = new PayPalItem(
                    carrito.get(i).getDescripcion(),
                    DEFAULT_AMOUNT,
                    BigDecimal.valueOf(carrito.get(i).getPrecioFinalProveedor()),
                    CURRENCY,
                    carrito.get(i).getEan());
            productos[i] = producto;
        }
        return productos;
    }

}
