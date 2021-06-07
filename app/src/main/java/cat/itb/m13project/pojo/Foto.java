package cat.itb.m13project.pojo;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "foto", strict = false)
public class Foto implements Serializable {

    @Element(name = "foto")
    String foto;

    public Foto() {
    }

    public Foto(String foto) {
        this.foto = foto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return foto;
    }
}
