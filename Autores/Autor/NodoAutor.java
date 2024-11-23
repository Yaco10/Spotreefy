// autor
// lista circular
class NodoAutor {
    private String nombre; // factor de orden en ListaAutores
    private NodoCancion listaCanciones; // (lista circular)
    private NodoAutor siguiente;
}

public NodoAutor(String nombre) {
    this.nombre = nombre;
    this.listaCanciones = null; // Innecesario
    this.siguiente = null; // Innecesario
}

public void insertarOrdenadoCircular() {

}