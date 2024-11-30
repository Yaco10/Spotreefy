import java.io.BufferedWriter;
import java.io.IOException;

public class ListaCircular {
    NodoCancion listaCanciones;

    public ListaCircular() {
        listaCanciones = null;
    }

    public void insertarCancionOrdenadoCircular(NodoCancion cancion) {
        if (this.listaCanciones == null) { // Caso 1: Lista vacía
            cancion.setSiguiente(cancion);
            this.listaCanciones = cancion;
        } else if (cancion.getTitulo().compareTo(this.listaCanciones.getTitulo()) <= 0) { // Caso 2: Inserción antes del primer nodo
            NodoCancion ultimo = this.listaCanciones;
            while (ultimo.getSiguiente() != this.listaCanciones) {
                ultimo = ultimo.getSiguiente();
            }
            cancion.setSiguiente(this.listaCanciones);
            ultimo.setSiguiente(cancion);
            this.listaCanciones = cancion;
        } else { // Caso 3: Inserción en el medio o al final
            NodoCancion actual = this.listaCanciones;
            while (actual.getSiguiente() != this.listaCanciones &&
                    actual.getSiguiente().getTitulo().compareTo(cancion.getTitulo()) < 0) {
                actual = actual.getSiguiente();
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
        if(this.listaCanciones != null) {
            if(this.listaCanciones.getTitulo().equals(titulo)) {
                return this.listaCanciones;
            }
            else{
                NodoCancion actual = this.listaCanciones.getSiguiente();
                while (actual != listaCanciones && actual.equals(titulo)) {
                    actual = actual.getSiguiente();
                }
                return actual;
            }
        }
        return null;
    }

    public void guardarCanciones(BufferedWriter bw, String nombreAutor) throws IOException {
        NodoCancion actual = this.listaCanciones;
        bw.write(actual.getTitulo() + " - " + nombreAutor);
        bw.newLine();
        actual = actual.getSiguiente();
        while (actual.getSiguiente() != listaCanciones) {
            bw.write(actual.getTitulo() + " - " + nombreAutor);
            bw.newLine();
            actual = actual.getSiguiente();
        }
    }
}
