class ArbolCanciones {
    NodoCancion canciones;

     // Constructor innecesario.
    public ArbolCanciones() {
        this.canciones = null;
    }

    public void insertarCancion(String titulo) {
        this.canciones = insertarCancionRec(this.canciones, new NodoCancion(titulo));
    }

    private NodoCancion insertarCancionRec(NodoCancion actual, NodoCancion nuevo) {
        if (actual == null) {
            actual = nuevo;
        } else if (actual.getTitulo().compareToIgnoreCase(nuevo.getTitulo()) > 0) {
            actual.setMenores(insertarCancionRec(actual.getMenores(), nuevo));
        } else if (actual.getTitulo().compareToIgnoreCase(nuevo.getTitulo()) < 0) {
            actual.setMayores(insertarCancionRec(actual.getMayores(), nuevo));
        }
        return actual;
    }
}