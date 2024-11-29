class ArbolUsuarios {
    NodoUsuario usuarios;

    // Constructor innecesario.
    public ArbolUsuarios() {
        this.usuarios = null;
    }

    public void insertarUsuario(String nombre, String contraseña) {
        this.usuarios = insertarUsuarioRec(this.usuarios, new NodoUsuario(nombre, contraseña));
    }

    private NodoUsuario insertarUsuarioRec(NodoUsuario actual, NodoUsuario nuevo) {
        if (actual == null) {
            actual = nuevo;
        } else if (actual.getNombre().compareToIgnoreCase(nuevo.getNombre()) > 0) {
            actual.setMenores(insertarUsuarioRec(actual.getMenores(), nuevo));
        } else if (actual.getNombre().compareToIgnoreCase(nuevo.getNombre()) < 0) {
            actual.setMayores(insertarUsuarioRec(actual.getMayores(), nuevo));
        }
        return actual;
    }

    // ¿boolean?
    
    public NodoUsuario buscarUsuario(String nombre, String contraseña) {
        return buscarUsuarioRec(this.usuarios, nombre, contraseña);
    }

    private NodoUsuario buscarUsuarioRec(NodoUsuario actual, String nombre, String contraseña) {
        if (actual == null) {
            return actual;
        }
        if (!actual.getNombre().equals(nombre)) {
            if (actual.getNombre().compareTo(nombre) > 0) {
                return buscarUsuarioRec(actual.getMenores(), nombre, contraseña);
            } else if (actual.getNombre().compareToIgnoreCase(nombre) < 0) {
                return buscarUsuarioRec(actual.getMayores(), nombre, contraseña);
            }
        }
        return actual.getContraseña().equals(contraseña) ? actual : null;
    }

    public void imprimirOrdenado() {
        imprimirOrdenadoRec(this.usuarios);    
    }

    private void imprimirOrdenadoRec(NodoUsuario actual) {
        imprimirOrdenadoRec(actual.getMenores());
        System.out.println("* " + actual.getNombre());
        imprimirOrdenadoRec(actual.getMayores());
    }
}