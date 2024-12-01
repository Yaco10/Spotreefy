import java.io.Serializable;

public class ArchListasPropias implements Serializable {
        String nombreUsuario;
        String nombrePlaylist;
        NodoCancion Cancion;
        private static final long serialVersionUID = 1L;

        // Constructor
            public ArchListasPropias(String nombreUsuario, String nombrePlaylist, NodoCancion cancion) {
            this.nombreUsuario = nombreUsuario;
            this.nombrePlaylist = nombrePlaylist;
            this.Cancion = cancion;
        }

        // Métodos getter y setter
        public String getNombreUsuario() {
            return nombreUsuario;
        }

        public void setNombreUsuario(String nombreUsuario) {
            this.nombreUsuario = nombreUsuario;
        }

        public String getNombrePlaylist() {
            return nombrePlaylist;
        }

        public void setNombrePlaylist(String nombrePlaylist) {
            this.nombrePlaylist = nombrePlaylist;
        }

        public NodoCancion getCancion() {
            return Cancion;
        }

        public void setCancion(NodoCancion Cancion) {
            this.Cancion = Cancion;
        }
    }


