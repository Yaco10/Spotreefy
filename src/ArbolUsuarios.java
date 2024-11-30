
import java.io.*;

class ArbolUsuarios {
    NodoUsuario usuarios;
    public ArbolUsuarios() {
        usuarios = null;
        cargarArbol("archUsuarios");
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

    // Guardar el árbol en un archivo, creando el archivo si no existe
    public void guardarArbol(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        try {
            if (!archivo.exists()) {
                // Crear el archivo si no existe
                archivo.createNewFile();
                System.out.println("Archivo creado: " + archivo.getAbsolutePath());
            }

            // Escribir en el archivo
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
                guardarArbolPreorden(bw, usuarios);
            }
            } catch (IOException e) {
                System.err.println("Error al guardar el archivo: " + e.getMessage());
            }
        }


    // Recorrido preorden para guardar los nodos
        private void guardarArbolPreorden(BufferedWriter bw, NodoUsuario nodo) throws IOException {
            if (nodo == null) return;

            // Escribe el nombre y la contraseña separados por un espacio
            bw.write(nodo.getNombre() + " " + nodo.getContraseña());
            bw.newLine();

            // Llama recursivamente para las subramas
            guardarArbolPreorden(bw, nodo.getMenores());
            guardarArbolPreorden(bw, nodo.getMayores());
        }

    public void cargarArbol(String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(" ");
                if (datos.length == 2) {
                    String nombre = datos[0];
                    String password = datos[1];
                    insertarUsuario(nombre,password);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
        }
    }
}





