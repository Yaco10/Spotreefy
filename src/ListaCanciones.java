class ListaCanciones {
    NodoCancion listaCanciones;

    // Constructor innecesario
    public ListaCanciones() {
        this.listaCanciones = null;
    }

    // TO DO (NO ES ORDENADO)
    // public void insertarCancionOrdenadoCircular(NodoCancion nuevo) {
    public void insertarCancionOrdenadoCircular(String titulo) {
        // NodoCancion actual = listaCanciones;

        NodoCancion nuevo = new NodoCancion(titulo);
        if (this.listaCanciones != null) {
            nuevo.setSiguiente(this.listaCanciones);
            NodoCancion ultimo = this.listaCanciones;
            while (ultimo.getSiguiente() != this.listaCanciones) {
                ultimo = ultimo.getSiguiente();
            }
            ultimo.setSiguiente(nuevo);
        } else {
            nuevo.setSiguiente(nuevo);
        }
        this.listaCanciones = nuevo;
    }

    // TO DO
    public NodoCancion buscarCancion() {
        return null;
    }
}