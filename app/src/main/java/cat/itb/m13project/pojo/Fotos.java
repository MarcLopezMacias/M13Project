package cat.itb.m13project.pojo;

import org.simpleframework.xml.ElementList;

import java.io.Serializable;
import java.util.List;

public class Fotos implements Serializable {

    @ElementList(name = "fotos", inline = true, required = false)
    private List<Foto> fotos;

    public Fotos() {
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

    @Override
    public String toString() {
        return "Fotos{" +
                "fotos=" + fotos +
                '}';
    }
}
