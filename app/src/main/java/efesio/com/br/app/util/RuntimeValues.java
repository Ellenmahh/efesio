package efesio.com.br.app.util;


import efesio.com.br.app.entities.NixPerfil;

/**
 * Created by otavi on 14/07/2017.
 */

public class RuntimeValues {
    private static NixPerfil perfil;
    private static String token;
    private static int idEmpresa;

    public static NixPerfil getPerfil() {
        return perfil;
    }

    public static void setPerfil(NixPerfil perfil) {
        RuntimeValues.perfil = perfil;
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
