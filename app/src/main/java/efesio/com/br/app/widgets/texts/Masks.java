package efesio.com.br.app.widgets.texts;

/**
 * Created by otavi on 23/08/2017.
 */

public enum Masks {
    CPF("999.999.999-99"),
    CNPJ("99.999.999/9999-99"),
    PHONE("(99) 99999-9999");


    private Mask mask;
    Masks(String mask){
        this.mask = new Mask(mask);
    }

    public Mask getMask() {
        return mask;
    }
}
