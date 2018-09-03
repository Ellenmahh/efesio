package efesio.com.br.app.entities;

public class MembroLogin {
    private String primaryKey;
    private int idEmpresa;
    private Membro membro;
    private String email;
    private String senha;


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

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    public String toString() {
        return "MembroLogin{" +
                "email='" + email + '\'' +
                ", membro=" + membro +
                ", senha='" + senha + '\'' +
                ", idEmpresa=" + idEmpresa +
                '}';
    }
}
