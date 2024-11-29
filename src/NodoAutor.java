class NodoAutor {
    private String nombre; // factor de orden en ListaAutores
    private NodoCancion listaCanciones; // (lista circular)
    private NodoAutor siguiente;

    public NodoAutor(String nombre) {
        this.nombre = nombre;
        this.listaCanciones = null; // Innecesario
        this.siguiente = null; // Innecesario
    }

    public String getNombre() {
        return this.nombre;
    }

    public NodoAutor getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoAutor nodo) {
        this.siguiente = nodo;
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
}