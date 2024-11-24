// lista
class ListaAutores {
    NodoAutor listaAutores; // (lista ordenada)

    // Constructor innecesario
    public ListaAutores() {
        this.listaAutores = null;
    }

    public void insertarAutorOrdenado(String nombre) {
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
}