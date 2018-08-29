package efesio.com.br.app.entities;

public class IgrejaMembro {

    private String pk;
    private String nome;
    private String email;
    private String cpf;
    private String foto;
    private String nomeIgreja;
    private Endereco enderecoIgreja;
    private int id;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNomeIgreja() {
        return nomeIgreja;
    }

    public void setNomeIgreja(String nomeIgreja) {
        this.nomeIgreja = nomeIgreja;
    }

    public Endereco getEnderecoIgreja() {
        return enderecoIgreja;
    }

    public void setEnderecoIgreja(Endereco enderecoIgreja) {
        this.enderecoIgreja = enderecoIgreja;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

}
