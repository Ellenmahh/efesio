package efesio.com.br.app.widgets.texts;

import efesio.com.br.app.util.Util;

/**
 * Created by otavi on 23/08/2017.
 */

public class Mask {
    private String mask;

    public Mask(String mask){
        this.mask = mask;
    }

    public String mask(String s){
        String stringToFormat = s;
        int tamString = stringToFormat.length();
        int mc = 0;
        int i = 0;
        String result = "";

        while (i < tamString) {
            if (mc >= getMask().length())
                break;

            if (getMask().charAt(mc) == '9') {
                if (!Util.isNaN(stringToFormat.charAt(i))) {
                    result += stringToFormat.charAt(i);
                    mc++;
                    i++;
                } else
                    i++;
            } else if (getMask().charAt(mc) == '#') {
                result += stringToFormat.charAt(i);
                mc++;
                i++;
            } else {
                result += getMask().charAt(mc);
                mc++;
            }
        }

        return result;
    }

//    public String unmask(){
//
//    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }
}
