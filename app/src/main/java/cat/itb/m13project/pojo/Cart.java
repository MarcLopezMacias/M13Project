package cat.itb.m13project.pojo;


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

}
