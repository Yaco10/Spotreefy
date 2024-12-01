import java.io.Serializable;

public class ArchListaSeguida implements Serializable {
    private String nombreUser;
    private String nombreUserSeguido;
    private String nombrePlaylist;

    public ArchListaSeguida(String nombreUser, String nombreUserSeguido, String nombrePlaylist) {
        this.nombreUser = nombreUser;
        this.nombreUserSeguido = nombreUserSeguido;
        this.nombrePlaylist = nombrePlaylist;

    }

    public String getNombrePlaylist() {
        return nombrePlaylist;
    }

    public void setNombrePlaylist(String nombrePlaylist) {
        this.nombrePlaylist = nombrePlaylist;
    }

    public String getNombreUser() {
        return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public String getNombreUserSeguido() {
        return nombreUserSeguido;
    }

    public void setNombreUserSeguido(String nombreUserSeguido) {
        this.nombreUserSeguido = nombreUserSeguido;
    }
}
