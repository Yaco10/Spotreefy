class ListaAutores {
    NodoAutor listaAutores;

    // Constructor innecesario.
    public ListaAutores() {
        this.listaAutores = null;
    }

    // Inserción con orden alfabético.
    public NodoAutor insertarAutor(String nombre) {
        NodoAutor nuevo = new NodoAutor(nombre);
        NodoAutor anterior = null, actual = this.listaAutores;
        while (actual != null && actual.getNombre().compareToIgnoreCase(nombre) < 0) {
            anterior = actual;
            actual = actual.getSiguiente();
        }
        nuevo.setSiguiente(actual);
        if (anterior == null) {
            this.listaAutores = nuevo;
        } else {
            anterior.setSiguiente(nuevo);
        }
        return nuevo;
    }
    
    public NodoAutor buscarAutor(String nombre) {
        NodoAutor autor = this.listaAutores;
        while (autor != null && !autor.getNombre().equalsIgnoreCase(nombre)) {
            autor = autor.getSiguiente();
        }
        return autor;
    }
}