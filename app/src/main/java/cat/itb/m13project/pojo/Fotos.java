package cat.itb.m13project.pojo;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

@Root(name = "fotos", strict = false)
public class Fotos implements Serializable {

    @ElementList(name = "fotos", entry = "foto", inline = true)
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
