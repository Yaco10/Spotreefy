import java.io.*;

// lista
class ListaAutores {
    NodoAutor listaAutores; // (lista ordenada)

    // Constructor innecesario.
    public ListaAutores() {
        this.listaAutores = null;
    }

    // Inserción con orden alfabético.
    public void insertarAutor(String nombre) {
        NodoAutor nuevo = new NodoAutor(nombre);

        // Si la lista está vacía o el nuevo autor debe ir al principio
        if (this.listaAutores == null || nombre.compareToIgnoreCase(this.listaAutores.getNombre()) < 0) {
            nuevo.setSiguiente(listaAutores);
            this.listaAutores = nuevo;
        } else {
            // Si no es al principio, se recorre la lista
            NodoAutor actual = this.listaAutores;
            while (actual.getSiguiente() != null && actual.getSiguiente().getNombre().compareToIgnoreCase(nuevo.getNombre()) < 0) {
                actual = actual.getSiguiente();
            }
            // Insertar al final o entre elementos
            nuevo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nuevo);
        }
    }


    // TO DO

    public NodoAutor buscarAutor(String nombre) {
        NodoAutor autor = this.listaAutores;
        while (autor != null && !autor.getNombre().equals(nombre)) {
            autor = autor.getSiguiente();
        }
        return autor;
    }

    //archivos

    public void guardarEnArchivoCanciones(String archivo) {
        NodoAutor actual = this.listaAutores;
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
            while (actual != null) {
                actual.getListaCanciones().guardarCanciones(actual.getNombre(),out);
                actual = actual.getSiguiente();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarAutores(String nombreArchivo, ArbolCanciones arbolCanciones) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            ArchCanciones actual;

            while ((actual = (ArchCanciones) ois.readObject()) != null) {
                insertarAutor(actual.getNombreAutor());
                NodoAutor autor = buscarAutor(actual.getNombreAutor());
                NodoCancion cancion = arbolCanciones.buscarCancion(actual.getNombreCancion());
                autor.getListaCanciones().insertarCancionOrdenadoCircular(cancion);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Archivo autores no encontrado. Creando archivo nuevo.");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        } catch (EOFException e) {
            System.out.println("Autores Cargados Correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}