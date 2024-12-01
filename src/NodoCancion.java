class NodoCancion {
    private String titulo;
    private NodoCancion mayores, menores;
    private NodoCancion siguiente;

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

    public NodoCancion getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoCancion nodo) {
        this.siguiente = nodo;
    }
}