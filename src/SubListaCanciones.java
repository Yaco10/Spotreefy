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
        NodoSublistaCanciones actual = listaCanciones;
        // Verificar si la canción ya existe en la lista
        while (actual != null) {
            if (actual.getCancion().equals(cancion)) {
                return; // No se inserta si la canción ya existe
            }
            actual = actual.getSiguiente();
        }
        // Si no se encontró la canción, insertamos al principio de la lista
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





