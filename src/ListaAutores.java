import java.io.*;

class ListaAutores {
    private NodoAutor listaAutores; // Lista ordenada de autores

    // Constructor
    public ListaAutores() {
        this.listaAutores = null;
    }

    // Inserta un autor en orden alfabético
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
                if (actual.getNombre().equalsIgnoreCase(nuevo.getNombre())) {
                    // Si ya existe un autor con el mismo nombre, no se inserta.
                    return;
                }
                actual = actual.getSiguiente();
            }

            // Verificar si el nuevo autor ya está en la lista
            if (actual.getNombre().equalsIgnoreCase(nuevo.getNombre())) {
                return; // Ya existe un autor con el mismo nombre, no se inserta.
            }

            // Insertar al final o entre elementos
            nuevo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nuevo);
        }
    }


    // Busca un autor por nombre
    public NodoAutor buscarAutor(String nombre) {
        NodoAutor autor = this.listaAutores;
        while (autor != null) {
            if (autor.getNombre().equalsIgnoreCase(nombre)) {
                return autor;
            }
            autor = autor.getSiguiente();
        }
        return null;
    }

    // Guarda las canciones de los autores en un archivo
    public void guardarAutores(String archivo) {
        NodoAutor actual = this.listaAutores;
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
            while (actual != null) {
                actual.getListaCanciones().guardarCanciones(actual.getNombre(), out);
                actual = actual.getSiguiente();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carga autores y sus canciones desde un archivo
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
            System.out.println("Archivo de autores no encontrado. Creando uno nuevo.");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
                // Archivo vacío creado
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        } catch (EOFException e) {
            System.out.println("Autores cargados correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
