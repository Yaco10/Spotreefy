class ListaIndexadaCanciones {
    private NodoIndexadoCancion listaCanciones;

    // Constructor innecesario.
    public ListaIndexadaCanciones() {
        this.listaCanciones = null;
    }

    // Inserción sin orden.
    public void insertarCancion(NodoCancion cancion) {
        NodoIndexadoCancion nuevo = new NodoIndexadoCancion(cancion);
        nuevo.setSiguiente(this.listaCanciones);
        this.listaCanciones = nuevo;
    }

    public boolean existeCancion(NodoCancion cancion) {
        NodoIndexadoCancion actual = this.listaCanciones;
        while (actual != null && actual.getCancion() != cancion) {
            actual = actual.getSiguiente();
        }
        return actual != null;
    }
}
