class ArbolCanciones {
    NodoCancion canciones;

     // Constructor innecesario.
    public ArbolCanciones() {
        this.canciones = null;
    }

    public void insertarCancion(String titulo) {
        this.canciones = insertarCancionRec(this.canciones, new NodoCancion(titulo));
    }

    // Inserción con orden alfabético.
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

    public NodoCancion buscarCancion(String titulo) {
        return buscarCancionRec(this.canciones, titulo);
    }

    private NodoCancion buscarCancionRec(NodoCancion actual, String titulo) {
        if (actual != null && !actual.getTitulo().equals(titulo)) {
            if (actual.getTitulo().compareToIgnoreCase(titulo) < 0) {
                return buscarCancionRec(actual.getMenores(), titulo);
            } else if (actual.getTitulo().compareToIgnoreCase(titulo) > 0) {
                return buscarCancionRec(actual.getMayores(), titulo);
            }
        }
        return actual;
    }
}