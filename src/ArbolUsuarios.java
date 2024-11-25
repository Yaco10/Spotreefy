class ArbolUsuarios {
    NodoUsuario usuarios; // factor de orden: campo 'nombre'

    public void insertarUsuario(String nombre, String contraseña) {
        usuarios = insertarUsuarioRecursivo(usuarios,nombre,contraseña);
    }

    private NodoUsuario insertarUsuarioRecursivo(NodoUsuario actual, String nombre, String contraseña){
        if(actual == null){
            return new NodoUsuario(nombre,contraseña);
        }
        else if(nombre.compareTo(actual.getNombre()) < 0){
            actual.setMenores(insertarUsuarioRecursivo(actual.getMenores(),nombre,contraseña));
        }
        else if(nombre.compareTo(actual.getNombre()) > 0){
            actual.setMayores(insertarUsuarioRecursivo(actual.getMayores(),nombre,contraseña));
        }
        return actual;
    }

    public NodoUsuario buscarUsuario(String nombre, String contraseña){
        return buscarUsuarioRecursivo(usuarios,nombre,contraseña);
    }

    private NodoUsuario buscarUsuarioRecursivo(NodoUsuario actual, String nombre, String contraseña){
        if(actual != null){
            if(actual.getNombre().equals(nombre) && actual.getContraseña().equals(contraseña)){
                return actual;
            } else if (actual.getNombre().compareTo(nombre) < 0) {
                return buscarUsuarioRecursivo(actual.getMayores(),nombre,contraseña);
            } else if (actual.getNombre().compareTo(nombre) > 0) {
                return buscarUsuarioRecursivo(actual.getMenores(),nombre,contraseña);
            }
        }
        return null;
    }
}

