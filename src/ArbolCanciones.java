import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class ArbolCanciones {
    NodoCancion canciones;

     // Constructor innecesario.
    public ArbolCanciones() {
        this.canciones = null;
    }

    public void insertarCancion(String titulo) {
        this.canciones = insertarCancionRec(this.canciones, new NodoCancion(titulo));
    }

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

    // TO DO

    private NodoCancion buscarCancionRec(NodoCancion actual, String titulo) {
        if (actual != null && !actual.getTitulo().equals(titulo)) {
            if (actual.getTitulo().compareToIgnoreCase(titulo) > 0) {
                return buscarCancionRec(actual.getMenores(), titulo);
            } else if (actual.getTitulo().compareToIgnoreCase(titulo) < 0) {
                return buscarCancionRec(actual.getMayores(), titulo);
            }
        }
        return actual;
    }

    //Archivos

    public void cargarArchivo(String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Separar el título de la canción y el nombre del autor
                String[] partes = linea.split(" - ");
                if (partes.length == 2) {
                    String tituloCancion = partes[0].trim();
                    insertarCancion(tituloCancion);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}