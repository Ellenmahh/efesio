package efesio.com.br.app.entities;

public class MembroLoginDto {
    private String pk;
    private int id;
    private int idAssinante;
    private Membro membro;
    private String email;
    private String senha;
    private String nomeIgreja;
    private String fotoIgreja;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Membro getMembro() {
        return membro;
    }

    public void setMembro(Membro membro) {
        this.membro = membro;
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

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
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

    public int getIdAssinante() {
        return idAssinante;
    }

    public void setIdAssinante(int idAssinante) {
        this.idAssinante = idAssinante;
    }

    @Override
    public String toString() {
        return "MembroLoginDto{" +
                "email='" + email + '\'' +
                ", membro=" + membro +
                ", senha='" + senha + '\'' +
                ", id=" + id +
                '}';
    }
}
