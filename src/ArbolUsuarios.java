class ArbolUsuarios {
    NodoUsuario usuarios;

    // Constructor innecesario.
    public ArbolUsuarios() {
        this.usuarios = null;
    }

    public void insertarUsuario(String nombre, String contraseña) {
        this.usuarios = insertarUsuarioRec(this.usuarios, new NodoUsuario(nombre, contraseña));
    }

    // Inserción con orden alfabético.
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

    public NodoUsuario buscarUsuario(String nombre, String contraseña) {
        return buscarUsuarioRec(this.usuarios, nombre, contraseña);
    }

    private NodoUsuario buscarUsuarioRec(NodoUsuario actual, String nombre, String contraseña) {
        if (actual != null) {
            if (actual.getNombre().equalsIgnoreCase(nombre) && actual.getContraseña().equals(contraseña)) {
                return actual;
            } else if (actual.getNombre().compareToIgnoreCase(nombre) < 0) {
                return buscarUsuarioRec(actual.getMayores(), nombre, contraseña);
            } else if (actual.getNombre().compareToIgnoreCase(nombre) > 0) {
                return buscarUsuarioRec(actual.getMenores(), nombre, contraseña);
            }
        }
        return null;
    }

    public void mostrar() {
        mostrarOrdenadoRec(this.usuarios);    
    }

    private void mostrarOrdenadoRec(NodoUsuario actual) {
        if (actual != null) {
            mostrarOrdenadoRec(actual.getMenores());
            System.out.println("* " + actual.getNombre());
            mostrarOrdenadoRec(actual.getMayores());
        }
    }
}