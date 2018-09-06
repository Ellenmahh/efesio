package efesio.com.br.app;

import android.hardware.Camera;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Util {

	public static String toMD5(String s){
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			m.update(s.getBytes(),0,s.length());
			return new BigInteger(1,m.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String removerEspeciaisEspaco(String valor) {
		if (valor == null || valor.isEmpty())
			return "";

		return valor.replace(".", "").replace("/", "").replace("\\", "").replace("(", "").replace(")", "").replace(",", "").replace("-", "")
				.replace("#", "").replace("%", "").replace("_", "").replace(" ", "");
	}

	public static String formatarCPF(String cpf) {
		int l = 11 - cpf.length();
		while (l > 0) {
			cpf = "0" + cpf;
			l--;
		}

		return formatarValor(cpf, "999.999.999-99");
	}

	public static String formatarMoedaReal(BigDecimal valor) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		return nf.format(valor);
	}

	public static String formatarValor(String valor, String mascara) {
		String stringToFormat = Util.removerEspeciaisEspaco(valor.toString());
		int tamString = stringToFormat.length();
		int mc = 0;
		int i = 0;
		String result = "";

		while (i < tamString) {
			if (mc >= mascara.length())
				break;

			if (mascara.charAt(mc) == '9') {
				if (!Util.isNaN(stringToFormat.charAt(i))) {
					result += stringToFormat.charAt(i);
					mc++;
					i++;
				} else
					i++;
			} else if (mascara.charAt(mc) == '#') {
				result += stringToFormat.charAt(i);
				mc++;
				i++;
			} else {
				result += mascara.charAt(mc);
				mc++;
			}
		}

		return result;
	}

	public static Boolean isNaN(String valor) {
		try {
			Double.parseDouble(valor);
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	public static Boolean isNaN(char valor) {
		return isNaN("" + valor);
	}

	public static void logError(Object instance, Exception error) {
		Log.e(instance.getClass().getName(), error.getMessage(), error);

	}

	public static Camera.Size getBestPreviewSize(Camera camera, int width, int height) {
		Camera.Size result = null;
		Camera.Parameters p = camera.getParameters();

		if (p.getSupportedPreviewSizes() == null)
			return null;

		for (Camera.Size size : p.getSupportedPreviewSizes()) {
			if (size.width <= width && size.height <= height) {
				if (result == null) {
					result = size;
				} else {
					int resultArea = result.width * result.height;
					int newArea = size.width * size.height;

					if (newArea > resultArea) {
						result = size;
					}
				}
			}
		}
		return result;

	}

	public static Date createDate(int dia, int mes, int ano) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(ano, mes - 1, dia);
		return cal.getTime();
	}

	public static Date intToDate(int data) {
		String d = String.valueOf(data);

		int ano = Integer.parseInt(d.substring(0, 4));
		int mes = Integer.parseInt(d.substring(4, 6));
		int dia = Integer.parseInt(d.substring(6, 8));

		GregorianCalendar cal = new GregorianCalendar();
		cal.set(ano, mes - 1, dia);
		return cal.getTime();
	}

	public static int dateToInt(Date date) {

		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);

		int d = cal.get(Calendar.YEAR) * 100;
		d += (cal.get(Calendar.MONTH) + 1);
		d *= 100;
		d += cal.get(Calendar.DAY_OF_MONTH);

		return d;
	}

	public static String recuperarDiaAbreviado(Date data) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(data);

		switch ((cal.get(Calendar.DAY_OF_WEEK))) {
		case 1:
			return "DOM";
		case 2:
			return "SEG";
		case 3:
			return "TER";
		case 4:
			return "QUA";
		case 5:
			return "QUI";
		case 6:
			return "SEX";
		case 7:
			return "SAB";
		}

		return "";
	}

	public static String recuperarDiaSemana(Date data) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(data);

		switch ((cal.get(Calendar.DAY_OF_WEEK))) {
		case 1:
			return "Domingo";
		case 2:
			return "Segunda-Feira";
		case 3:
			return "Terça-Feira";
		case 4:
			return "Quarta-Feira";
		case 5:
			return "Quinta-Feira";
		case 6:
			return "Sexta-Feira";
		case 7:
			return "Sabádo";
		}

		return "";
	}

	public static String recuperarMesAbreviado(Date data) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(data);

		switch ((cal.get(Calendar.MONTH) + 1)) {
		case 1:
			return "JAN";
		case 2:
			return "FEV";
		case 3:
			return "MAR";
		case 4:
			return "ABR";
		case 5:
			return "MAI";
		case 6:
			return "JUN";
		case 7:
			return "JUL";
		case 8:
			return "AGO";
		case 9:
			return "SET";
		case 10:
			return "OUT";
		case 11:
			return "NOV";
		case 12:
			return "DEZ";
		}

		return "";
	}

	public static String recuperarMesExtenso(Date data) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(data);

		switch ((cal.get(Calendar.MONTH) + 1)) {
		case 1:
			return "Janeiro";
		case 2:
			return "Fevereiro";
		case 3:
			return "Março";
		case 4:
			return "Abril";
		case 5:
			return "Maio";
		case 6:
			return "Junho";
		case 7:
			return "Julho";
		case 8:
			return "Agosto";
		case 9:
			return "Setembro";
		case 10:
			return "Outubro";
		case 11:
			return "Novembro";
		case 12:
			return "Dezembro";
		}

		return "";
	}

	public static int recuperarAno(Date data) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(data);
		return cal.get(Calendar.YEAR);
	}

	public static int recuperarDia(Date data) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(data);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static Boolean datesEqual(Date date1, Date date2) {
		GregorianCalendar cal1 = new GregorianCalendar();
		cal1.setTime(date1);

		GregorianCalendar cal2 = new GregorianCalendar();
		cal2.setTime(date2);

		return (cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH)) && (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH))
				&& (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR));

	}

	public static String makeSHA1Hash(String input) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA1");
		md.reset();
		byte[] buffer = input.getBytes();
		md.update(buffer);
		byte[] digest = md.digest();

		String hexStr = "";
		for (int i = 0; i < digest.length; i++) {
			hexStr += Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1);
		}
		return hexStr;
	}

	public static String retornaDatacomT(Date data) {
		if (data == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(data);
		sdf = new SimpleDateFormat("HH:mm:ss");
		return date + "T" + sdf.format(data);
	}

	public static String retornaData(Date data) {
		if (data == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(data);
	}

	public static String retornaDataSemBarra(Date data) {
		if (data == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		return sdf.format(data);
	}

	public static String retornaDataComBarra(Date data) {
		if (data == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(data);
	}

	public static String retornaHoraFormatada(Date data) {
		if (data == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(data);
	}

	public static String retornaDataHoraFormatada(Date data) {
		if (data == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return sdf.format(data);
	}

	public static Date retornaDataCorrente() {
		Calendar calendar = new GregorianCalendar();
		Date date = new Date();
		calendar.setTime(date);
		return calendar.getTime();

	}

	public static Date retornaDataCorrenteMenos1() {
		Calendar dataAtual = Calendar.getInstance();
		dataAtual.add(Calendar.DAY_OF_MONTH, -1);
		return dataAtual.getTime();

	}
    public static Date retornaDataCorrenteMenos2() {
        Calendar dataAtual = Calendar.getInstance();
        dataAtual.add(Calendar.DAY_OF_MONTH, -2);
        return dataAtual.getTime();

    }

	public static Date retornaDataCorrenteMais1() {
		Calendar dataAtual = Calendar.getInstance();
		dataAtual.add(Calendar.DAY_OF_MONTH, +1);
		return dataAtual.getTime();

	}

	public static String retornaDatasemT(String data) {
		if (data == null || data.equals("")) {
			return null;
		}
		String[] datas = data.split("T");
		data = datas[0];
		datas = data.split("-");
		return datas[2] + "/" + datas[1] + "/" + datas[0];
	}

	public static String getStack(Throwable exception) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		return (sw.toString());
	}

	public static Date StringtoDate(String data) {
		if (data == null || data.equals("")) {
			return null;
		}
		try {
			data = data.replace("T", "");
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
			return formatter.parse(data);
		} catch (ParseException e) {
			//
			e.printStackTrace();
			return null;
		}

	}

	public static Date StringtoDate(String data, String formato) {
		if (data == null || data.equals("") || data.equals("null")) {
			return null;
		}
		try {
			data = data.replace("T", "");
			DateFormat formatter = new SimpleDateFormat(formato);
			return formatter.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static String converteDataporExtenso(Date date) {
		String data;
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		data = recuperarDiaSemana(date);
		data += " ,";
		data += cal.get(Calendar.DAY_OF_MONTH);
		data += " de ";
		data += recuperarMesExtenso(date);
		data += " de ";
		data += cal.get(Calendar.YEAR);
		data += " ás ";
		DateFormat f24h = new SimpleDateFormat("HH:mm");
		data += f24h.format(date);
		return data;
	}

	public static String retornaDataCorrenteSemHora() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(retornaDataCorrente());
		return date + "T00:00:00";
	}

	public static String retornaDataSemHoraComT(Date data) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(data);
		return date + "T00:00:00";
	}

	public static boolean intToBoolean(int bool) {
		if (bool == 1) {
			return true;
		} else {
			return false;
		}
	}

	public static int booleanToInt(boolean bool) {
		if (bool) {
			return 1;
		} else {
			return 0;
		}
	}

	public static String formataTelefone(String telefone) {
		String dd = telefone.substring(0, 2);
		String p1 = telefone.substring(2, 6);
		String p2 = telefone.substring(6);
		return String.format("(%s) %s-%s", dd, p1, p2);
	}
    public static String removeAcento (String string){
        string = string.replaceAll("[ÂÀÁÄÃ]","A");
        string = string.replaceAll("[âãàáä]","a");
        string = string.replaceAll("[ÊÈÉË]","E");
        string = string.replaceAll("[êèéë]","e");
        string = string.replaceAll("ÎÍÌÏ","I");
        string = string.replaceAll("îíìï","i");
        string = string.replaceAll("[ÔÕÒÓÖ]","O");
        string = string.replaceAll("[ôõòóö]","o");
        string = string.replaceAll("[ÛÙÚÜ]","U");
        string = string.replaceAll("[ûúùü]","u");
        string = string.replaceAll("Ç","C");
        string = string.replaceAll("ç","c");
        string = string.replaceAll("[ýÿ]","y");
        string = string.replaceAll("Ý","Y");
        string = string.replaceAll("ñ","n");
        string = string.replaceAll("Ñ","N");
        return string;
    }
    public static void sleepMinuto(int minuto) throws Exception {
        try{
            int tempo = minuto * 60000;
            Thread.sleep(tempo);
        }catch (Exception e){
            throw e;
        }
    }
    public static void sleepSegundos(int segundo) throws Exception {
        try{
            int tempo = segundo * 1000;
            Thread.sleep(tempo);
        }catch (Exception e){
            throw e;
        }
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static boolean isNullOrWhitespace(String s) {
        return s == null || isWhitespace(s);

    }
    private static boolean isWhitespace(String s) {
        int length = s.length();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(s.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
