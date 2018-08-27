package efesio.com.br.app;

import java.util.List;

import efesio.com.br.app.enums.UF;

public class Estado {
    private UF uf;
    private List<String> cities;

    public Estado(UF stateName, List<String> cities) {
        this.uf = stateName;
        this.cities = cities;
    }



    public UF getUf() {
        return uf;
    }

    public List<String> getCities() {
        return cities;
    }

    @Override
    public String toString() {
        return "Estado{" +
                "uf=" + uf +
                '}';
    }
}
