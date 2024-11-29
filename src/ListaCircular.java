public class ListaCircular {
    NodoCancion listaCanciones;

    public ListaCircular() {
        listaCanciones = null;
    }

    public void insertarCancionOrdenadoCircular(NodoCancion cancion) {
        if (this.listaCanciones == null) { // Caso 1: Lista vacía
            cancion.setSiguiente(cancion); // El nodo se apunta a sí mismo
            this.listaCanciones = cancion;
        } else if (cancion.getTitulo().compareTo(this.listaCanciones.getTitulo()) <= 0) { // Caso 2: Inserción antes del primer nodo
            NodoCancion ultimo = this.listaCanciones;
            while (ultimo.getSiguiente() != this.listaCanciones) { // Encuentra el último nodo
                ultimo = ultimo.getSiguiente();
            }
            cancion.setSiguiente(this.listaCanciones); // Conecta el nuevo nodo al inicio
            ultimo.setSiguiente(cancion); // Ajusta el último nodo al nuevo
            this.listaCanciones = cancion; // Actualiza el inicio de la lista
        } else { // Caso 3: Inserción en el medio o al final
            NodoCancion actual = this.listaCanciones;
            while (actual.getSiguiente() != this.listaCanciones &&
                    actual.getSiguiente().getTitulo().compareTo(cancion.getTitulo()) < 0) {
                actual = actual.getSiguiente(); // Avanza hasta encontrar la posición correcta
            }
            cancion.setSiguiente(actual.getSiguiente()); // Conecta el nuevo nodo
            actual.setSiguiente(cancion); // Ajusta el nodo anterior
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
}
