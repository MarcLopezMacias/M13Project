package cat.itb.m13project.pojo;

import org.simpleframework.xml.Element;

import java.io.Serializable;

@Element(name = "foto")
public class Foto implements Serializable {

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
