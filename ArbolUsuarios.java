class ArbolUsuarios {
    NodoUsuario usuarios; // factor de orden: campo 'nombre'

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

    // revisar
    private NodoUsuario buscarUsuarioRec(NodoUsuario actual, String nombre, String contraseña) {
        if (actual != null && !actual.getNombre().equals(nombre) && !actual.getContraseña().equals(contraseña)) {
            if (actual.getNombre().compareToIgnoreCase(nombre) > 0) {
                actual = buscarUsuarioRec(actual.getMenores(), nombre, contraseña);
            } else if (actual.getNombre().compareToIgnoreCase(nombre) < 0) {
                actual = buscarUsuarioRec(actual.getMayores(), nombre, contraseña);
            }
        }
        return actual;
    }
}