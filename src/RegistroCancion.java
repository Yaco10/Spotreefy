import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;

class RegistroCancion {
    private final static String nombreArchivo = "ArchCanciones";

    // TO DO
    public static void cargarArbol(ArbolCanciones canciones) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            ArchCanciones actual;
            while ((actual = (ArchCanciones) ois.readObject()) != null) {
                insertarCancion(actual.getNombreCancion());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de Canciones no encontrado, Creando uno nuevo.");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        } catch (EOFException e) {
            System.out.println("Canciones Cargadas Correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
