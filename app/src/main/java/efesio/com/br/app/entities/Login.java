package efesio.com.br.app.entities;

public class Login {

    private String pk;
    private String email;
    private String senha;
    private int id;
    private int idAssinante;
    private String nomeIgreja;
    private String fotoIgreja;
    private String nomeUser;
    private String fotoUser;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAssinante() {
        return idAssinante;
    }

    public void setIdAssinante(int idAssinante) {
        this.idAssinante = idAssinante;
    }

    public String getNomeIgreja() {
        return nomeIgreja;
    }

    public void setNomeIgreja(String nomeIgreja) {
        this.nomeIgreja = nomeIgreja;
    }

    public String getFotoIgreja() {
        return fotoIgreja;
    }

    public void setFotoIgreja(String fotoIgreja) {
        this.fotoIgreja = fotoIgreja;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    public String getFotoUser() {
        return fotoUser;
    }

    public void setFotoUser(String fotoUser) {
        this.fotoUser = fotoUser;
    }
}
