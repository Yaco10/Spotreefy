import java.io.*;

public class ArbUsuarios implements Serializable {
    NodoUsuario raiz;

    public ArbUsuarios() {
        raiz = null;
    }

    public void insertarUsuario(String nombre, String contraseña) {
        raiz = insertarUsuarioRec(raiz, nombre, contraseña);
    }

    private NodoUsuario insertarUsuarioRec(NodoUsuario actual, String nombre, String constraseña){
        if(actual == null){
            return new NodoUsuario(nombre,constraseña);
        }
        else if(nombre.compareTo(actual.getNombreUsuario())<0){
             actual.setMenores(insertarUsuarioRec(actual.getMenores(),nombre,constraseña));
        }
        else if(nombre.compareTo(actual.getNombreUsuario())>0){
             actual.setMayores(insertarUsuarioRec(actual.getMayores(),nombre,constraseña));
        }
        return actual;
    }

    public NodoUsuario buscarUsuario(String nombre){
        return buscarUsuario(raiz,nombre);
    }

    private NodoUsuario buscarUsuario(NodoUsuario actual, String nombre){
        if(actual != null){
            if(actual.getNombreUsuario().equals(nombre)){
                return actual;
            }
            if(actual.getNombreUsuario().compareTo(nombre)<0){
                return buscarUsuario(actual.getMayores(),nombre);
            }
            else if(actual.getNombreUsuario().compareTo(nombre)>0){
                return buscarUsuario(actual.getMenores(),nombre);
            }
        }
        return null;
    }

    public void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("juansito"))) {
            oos.writeObject(this);  // Serializamos el árbol completo
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al guardar los datos.");
        }
    }

    // Cargar el árbol desde un archivo
    public void cargarDatos() {
        File archivo = new File("juansito");
        if (!archivo.exists()) {
            try {
                archivo.createNewFile(); // Crea un archivo vacío si no existe
                System.out.println("Archivo creado.");
            } catch (IOException e) {
                System.out.println("Error al crear el archivo.");
                e.printStackTrace();
            }
        }

        // Deserializar los datos si el archivo ya existe
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                ArbUsuarios arbUsuariosRestaurado = (ArbUsuarios) ois.readObject();
                this.raiz = arbUsuariosRestaurado.raiz;  // Restaurar el árbol desde el archivo
                System.out.println("Datos cargados correctamente.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al cargar los datos.");
                e.printStackTrace();
            }
        }
    }

}

