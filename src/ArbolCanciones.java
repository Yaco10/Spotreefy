import java.io.*;

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