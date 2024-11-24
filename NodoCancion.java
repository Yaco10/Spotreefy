class NodoCancion {
    String titulo; // factor de orden en ArbolCanciones
    // String autor ¿?
    NodoCancion mayores, menores;
    NodoCancion siguiente; // siguiente cancion de NodoAutor

    // ¿agregar campos?
    public NodoCancion(String titulo) {
        this.titulo = titulo;
        this.menores = null; // Innecesario
        this.mayores = null; // Innecesario
        this.siguiente = null; // Innecesario
    }

    public String getTitulo() {
        return this.titulo;
    }

    public NodoCancion getMenores() {
        return this.menores;
    }

    public void setMenores(NodoCancion nodo) {
        this.menores = nodo;
    }

    public NodoCancion getMayores() {
        return this.mayores;
    }

    public void setMayores(NodoCancion nodo) {
        this.mayores = nodo;
    }
}

