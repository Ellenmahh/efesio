package efesio.com.br.app.util;


/**
 * Created by otavi on 14/07/2017.
 */

public class RuntimeValues {
    private static String email;
    private static String nomeUser;
    private static String imagem ;
    private static String token;
    private static String nomeIgreja;
    private static String fotoIgreja;
    private static int idEmpresa;
    private static int idAssinante;

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        RuntimeValues.email = email;
    }

    public static String getImagem() {
        return imagem;
    }

    public static void setImagem(String imagem) {
        RuntimeValues.imagem = imagem;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        RuntimeValues.token = token;
    }

    public static int getIdEmpresa() {
        return idEmpresa;
    }

    public static void setIdEmpresa(int idEmpresa) {
        RuntimeValues.idEmpresa = idEmpresa;
    }

    public static String getNomeIgreja() {
        return nomeIgreja;
    }

    public static void setNomeIgreja(String nomeIgreja) {
        RuntimeValues.nomeIgreja = nomeIgreja;
    }

    public static String getFotoIgreja() {
        return fotoIgreja;
    }

    public static void setFotoIgreja(String fotoIgreja) {
        RuntimeValues.fotoIgreja = fotoIgreja;
    }

    public static String getNomeUser() {
        return nomeUser;
    }

    public static void setNomeUser(String nomeUser) {
        RuntimeValues.nomeUser = nomeUser;
    }

    public static int getIdAssinante() {
        return idAssinante;
    }

    public static void setIdAssinante(int idAssinante) {
        RuntimeValues.idAssinante = idAssinante;
    }
}
