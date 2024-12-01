import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;

class RegistroUsuario {
    final static String archivo = "ArchUsuarios";
    
    // TO DO
    public static void cargarArbol(ArbolUsuarios usuarios) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            NodoUsuario actual;
            while ((actual = (NodoUsuario) ois.readObject()) != null) {
                usuarios.insertarUsuario(actual.getNombre(), actual.getContraseña());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Creando archivo nuevo.");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        } catch (EOFException e) {
            System.out.println("Usuarios cargados correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
