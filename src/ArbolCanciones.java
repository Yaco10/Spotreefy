import java.io.*;

class ArbolCanciones {
    private NodoCancion canciones;

    // Constructor
    public ArbolCanciones() {
        this.canciones = null;
    }

    // Inserta una canción en el árbol
    public void insertarCancion(String titulo) {
        this.canciones = insertarCancionRec(this.canciones, new NodoCancion(titulo));
    }

    private NodoCancion insertarCancionRec(NodoCancion actual, NodoCancion nueva) {
        if (actual == null) {
            return nueva;
        } else if (actual.getTitulo().compareToIgnoreCase(nueva.getTitulo()) > 0) {
            actual.setMenores(insertarCancionRec(actual.getMenores(), nueva));
        } else if (actual.getTitulo().compareToIgnoreCase(nueva.getTitulo()) < 0) {
            actual.setMayores(insertarCancionRec(actual.getMayores(), nueva));
        }
        return actual;
    }

    // Busca una canción por título
    public NodoCancion buscarCancion(String titulo) {
        return buscarCancionRec(this.canciones, titulo);
    }

    private NodoCancion buscarCancionRec(NodoCancion actual, String titulo) {
        if (actual != null) {
            if (actual.getTitulo().equalsIgnoreCase(titulo)) {
                return actual;
            } else if (actual.getTitulo().compareToIgnoreCase(titulo) > 0) {
                return buscarCancionRec(actual.getMenores(), titulo);
            } else {
                return buscarCancionRec(actual.getMayores(), titulo);
            }
        }
        return null;
    }

    // Carga las canciones desde un archivo
    public void cargarCanciones(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            ArchCanciones actual;

            while ((actual = (ArchCanciones) ois.readObject()) != null) {
                insertarCancion(actual.getNombreCancion());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Archivo de canciones no encontrado. Creando uno nuevo.");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
                // Archivo vacío creado
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        } catch (EOFException e) {
            System.out.println("Canciones cargadas correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
