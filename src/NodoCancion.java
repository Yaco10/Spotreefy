import java.io.Serializable;

class NodoCancion implements Serializable {
    String titulo; // factor de orden en ArbolCanciones
    // String autor ¿?
    transient NodoCancion mayores, menores;
    transient NodoCancion siguiente; // siguiente cancion de NodoAutor
    private static final long serialVersionUID = 1L;

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

