package cat.itb.m13project.pojo;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

@Root(name = "productos")
public class Productos implements Serializable {

    @ElementList(name = "productos", inline = true, required = false)
    private List<Producto> productos;

    public Productos() {
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "Productos{" +
                "productos=" + productos +
                '}';
    }
}
