import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class SubListaCanciones implements Serializable {
    NodoSublistaCanciones listaCanciones;
    public SubListaCanciones() {
        this.listaCanciones = null;
    }

    public void insertarCancion(NodoCancion cancion) {
        NodoSublistaCanciones nuevo = new NodoSublistaCanciones(cancion);
        nuevo.setSiguiente(listaCanciones);
        listaCanciones = nuevo;

    }
    //sin orden

    public void guardarCanciones(BufferedWriter bw) throws IOException {
        NodoSublistaCanciones actual = listaCanciones;
        bw.write("Canciones:");
        bw.newLine();
        while (actual != null) {
            bw.write("- " + actual.getCancion().getTitulo());
            bw.newLine();
            actual = actual.getSiguiente();
        }
    }


}
