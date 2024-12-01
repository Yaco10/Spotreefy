import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ListaCircular {
    private NodoCancion listaCanciones;

    public ListaCircular() {
        this.listaCanciones = null;
    }

    public void insertarCancionOrdenadoCircular(NodoCancion cancion) {
        if (this.listaCanciones == null) { // Caso 1: Lista vacía
            cancion.setSiguiente(cancion);
            this.listaCanciones = cancion;
        } else if (cancion.getTitulo().compareTo(this.listaCanciones.getTitulo()) <= 0) { // Caso 2: Inserción antes del primer nodo
            NodoCancion ultimo = this.listaCanciones;
            while (ultimo.getSiguiente() != this.listaCanciones) {
                if (ultimo.getTitulo().equalsIgnoreCase(cancion.getTitulo())) {
                    // Canción duplicada, no se inserta.
                    return;
                }
                ultimo = ultimo.getSiguiente();
            }
            if (ultimo.getTitulo().equalsIgnoreCase(cancion.getTitulo())) {
                // Canción duplicada, no se inserta.
                return;
            }
            cancion.setSiguiente(this.listaCanciones);
            ultimo.setSiguiente(cancion);
            this.listaCanciones = cancion;
        } else { // Caso 3: Inserción en el medio o al final
            NodoCancion actual = this.listaCanciones;
            while (actual.getSiguiente() != this.listaCanciones &&
                    actual.getSiguiente().getTitulo().compareTo(cancion.getTitulo()) < 0) {
                if (actual.getTitulo().equalsIgnoreCase(cancion.getTitulo())) {
                    // Canción duplicada, no se inserta.
                    return;
                }
                actual = actual.getSiguiente();
            }
            // Verificar si el nodo siguiente ya tiene un título igual al de la nueva canción
            if (actual.getTitulo().equalsIgnoreCase(cancion.getTitulo())) {
                return; // Canción duplicada, no se inserta.
            }
            cancion.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(cancion);
        }
    }


    public void mostrarListaCanciones() {
        if (this.listaCanciones == null) { // Verifica si la lista está vacía
            System.out.println("La lista de canciones está vacía.");
            return;
        }

        NodoCancion cancion = this.listaCanciones;
        do {
            System.out.println(cancion.getTitulo());
            cancion = cancion.getSiguiente();
        } while (cancion != this.listaCanciones);
        System.out.println();
    }

    public NodoCancion buscarCancion(String titulo) {
        if (this.listaCanciones == null) { // Verifica si la lista está vacía
            return null;
        }
        NodoCancion actual = this.listaCanciones;
        do {
            if (actual.getTitulo().equalsIgnoreCase(titulo)) {
                return actual;
            }
            actual = actual.getSiguiente();
        } while (actual != this.listaCanciones);

        return null; // No se encontró la canción
    }

    public void guardarCanciones(String nombreAutor, ObjectOutputStream out) throws IOException {
        if (this.listaCanciones == null) { // Verifica si la lista está vacía
            return;
        }

        NodoCancion actual = this.listaCanciones;
        do {
            ArchCanciones registro = new ArchCanciones(actual.getTitulo(), nombreAutor);
            out.writeObject(registro);
            actual = actual.getSiguiente();
        } while (actual != this.listaCanciones);
    }
}
