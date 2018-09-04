package efesio.com.br.app.util;


/**
 * Created by otavi on 14/07/2017.
 */

public class RuntimeValues {
    private static String email;
    private static String imagem ;
    private static String token;
    private static int idEmpresa;

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
}
