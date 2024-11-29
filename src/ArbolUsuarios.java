import java.io.Serializable;
import java.io.*;

class ArbolUsuarios implements Serializable {
    NodoUsuario usuarios;
    public ArbolUsuarios() {
        usuarios = null;
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

    public NodoUsuario buscarUsuario(String nombre, String contraseña) {
        return buscarUsuarioRecursivo(usuarios, nombre, contraseña);
        // ¿boolean?
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


    public void imprimirOrdenado() {
        imprimirOrdenadoRec(this.usuarios);
    }

    private void imprimirOrdenadoRec(NodoUsuario actual) {
        imprimirOrdenadoRec(actual.getMenores());
        System.out.println("* " + actual.getNombre());
        imprimirOrdenadoRec(actual.getMayores());
    }


    public void guardarArchivo(String nombreArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(this); // Serializamos todo el árbol
            System.out.println("El árbol se ha guardado correctamente en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }



}