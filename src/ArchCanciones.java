import java.io.Serializable;

public class ArchCanciones implements Serializable {
    String nombreCancion;
    String nombreAutor;
    private static final long serialVersionUID = 1L;

    public ArchCanciones(String nombreCancion, String nombreAutor) {
        this.nombreCancion = nombreCancion;
        this.nombreAutor = nombreAutor;


    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }
}
