import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class SubListaCanciones  {
    NodoSublistaCanciones listaCanciones;
    public SubListaCanciones() {
        this.listaCanciones = null;
    }

    public void insertarCancion(NodoCancion cancion) {
        NodoSublistaCanciones nuevo = new NodoSublistaCanciones(cancion);
        nuevo.setSiguiente(listaCanciones);
        listaCanciones = nuevo;

    }

    public void guardarCancionesDeLista(String nombreUsuario, String nombrePlaylist, ObjectOutputStream out) throws IOException {
        NodoSublistaCanciones actual = listaCanciones;
        if(actual != null) {
            while (actual != null) {
                ArchListasPropias registro = new ArchListasPropias(nombreUsuario,nombrePlaylist,actual.getCancion());
                out.writeObject(registro);
                actual = actual.getSiguiente();

            }
        }
        else {
            ArchListasPropias registro = new ArchListasPropias(nombreUsuario,nombrePlaylist,null);
            out.writeObject(registro);
        }

    }
}





