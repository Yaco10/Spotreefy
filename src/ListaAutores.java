// lista
class ListaAutores {
    NodoAutor listaAutores; // (lista ordenada)

    // Constructor innecesario.
    public ListaAutores() {
        this.listaAutores = null;
    }

    // Inserción con orden alfabético.
    public void insertarAutor(String nombre) {
        NodoAutor nuevo = new NodoAutor(nombre);
        NodoAutor anterior = null, actual = this.listaAutores;
        while (actual != null && actual.getNombre().compareToIgnoreCase(nuevo.getNombre()) < 0) {
            anterior = actual;
            actual = actual.getSiguiente();
        }
        nuevo.setSiguiente(actual);
        if (anterior != null) {
            anterior.setSiguiente(nuevo);
        } else {
            this.listaAutores = nuevo;
        }
    }

    // TO DO

    public NodoAutor buscarAutor(String nombre) {
        NodoAutor autor = this.listaAutores;
        while (autor != null && !autor.getNombre().equals(nombre)) {
            autor = autor.getSiguiente();
        }
        return autor;
    }
}