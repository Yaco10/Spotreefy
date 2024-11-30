// autor
// lista circular

class NodoAutor {
    private String nombre; // factor de orden en ListaAutores
    private ListaCircular listaCanciones; // (lista circular)
    private NodoAutor siguiente;

    public NodoAutor(String nombre) {
        this.nombre = nombre;
        this.listaCanciones = new ListaCircular(); // Innecesario
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

    public ListaCircular getListaCanciones() {
        return this.listaCanciones;
    }



}