class ListaCanciones {
    NodoCancion listaCanciones;

    // Constructor innecesario
    public ListaCanciones() {
        this.listaCanciones = null;
    }

    // Inserción con orden alfabético y conexión circular.
    public void insertarCancion(String titulo) {
        NodoCancion cancion = new NodoCancion(titulo);
        if (this.listaCanciones == null) { // Caso 1: lista vacía
            cancion.setSiguiente(cancion);
            this.listaCanciones = cancion;
        } else if (this.listaCanciones.getTitulo().compareToIgnoreCase(titulo) > 0) { // Caso 2: inserción antes del primer nodo
            NodoCancion ultimo = this.listaCanciones;
            while (ultimo.getSiguiente() != this.listaCanciones) {
                ultimo = ultimo.getSiguiente();
            }
            cancion.setSiguiente(this.listaCanciones);
            ultimo.setSiguiente(cancion);
            this.listaCanciones = cancion;
        } else { // Caso 3: inserción en el medio o al final
            NodoCancion actual = this.listaCanciones;
            while (actual.getSiguiente() != this.listaCanciones && actual.getSiguiente().getTitulo().compareToIgnoreCase(titulo) < 0) {
                actual = actual.getSiguiente();
            }
            cancion.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(cancion);
        }
    }

    public void mostrar() {
        if (this.listaCanciones != null) {
            NodoCancion actual = this.listaCanciones;
            do {
                System.out.println("* " + actual.getTitulo());
                actual = actual.getSiguiente();
            } while (actual != this.listaCanciones);
        }
    }
}