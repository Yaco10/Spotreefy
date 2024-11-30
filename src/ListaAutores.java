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
        if (this.listaAutores == null || nombre.compareToIgnoreCase(this.listaAutores.getNombre()) < 0) {
            nuevo.setSiguiente(listaAutores);
            this.listaAutores = nuevo;
        } else {
            NodoAutor anterior = null, actual = this.listaAutores;
            while (actual != null && actual.getNombre().compareToIgnoreCase(nuevo.getNombre()) > 0) {
                anterior = actual;
                actual = actual.getSiguiente();
            }
            if (actual.getNombre().compareToIgnoreCase(nuevo.getNombre()) == 0) {
                return;
            }
            nuevo.setSiguiente(actual);
            anterior.setSiguiente(nuevo);
            this.listaAutores = nuevo;
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

    // Método para guardar los datos de la lista de autores en un archivo
    public void guardarArchivo(String nombreArchivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            NodoAutor autor = this.listaAutores;
            while (autor != null) {
                autor.getListaCanciones().guardarCanciones(bw, autor.getNombre()); //deriva la otra carga a la ListaCacniones
                autor = autor.getSiguiente();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
            e.printStackTrace(); // Agrega esto si quieres que se imprima la traza de la excepción
        } catch (Exception e) {
            System.out.println("Se ha producido un error inesperado: " + e.getMessage());
            e.printStackTrace(); // Imprime la traza de la excepción si es necesario
        }
    }

    // Método para cargar los datos del archivo y llenar la lista de autores
    public void cargarArchivo(String nombreArchivo, ArbolCanciones arbolCanciones) {
        File archivo = new File(nombreArchivo);
        try{
            if (!archivo.exists()) {
                // Crear el archivo si no existe
                archivo.createNewFile();
                System.out.println("Archivo creado: " + archivo.getAbsolutePath());
            }
            try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    // Separar el título de la canción y el nombre del autor
                    String[] partes = linea.split(" - ");
                    if (partes.length == 2) {
                        String tituloCancion = partes[0].trim();
                        String nombreAutor = partes[1].trim();

                        // Insertar en la lista de autores
                        insertarAutor(nombreAutor);

                        // Obtener Referencias y enlazar nodoCancion con NodoAutor
                        NodoCancion nodoCancion = arbolCanciones.buscarCancion(tituloCancion);
                        NodoAutor autor = buscarAutor(nombreAutor);
                        autor.getListaCanciones().insertarCancionOrdenadoCircular(nodoCancion);
                    }
                }
        }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

}