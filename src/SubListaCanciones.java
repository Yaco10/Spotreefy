import java.io.Serializable;

public class SubListaCanciones implements Serializable {
    NodoCancion listaCanciones;
    public SubListaCanciones() {
        this.listaCanciones = null;
    }

    public void insertarCancion(NodoCancion cancion) {
        cancion.setSiguiente(listaCanciones);
        listaCanciones = cancion;
    }
    //sin orden
}
