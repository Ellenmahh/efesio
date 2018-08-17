package efesio.com.br.app.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

	private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
	private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};


	//Metodos de validação de CPF e CNPJ
	private static int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
			digito = Integer.parseInt(str.substring(indice,indice+1));
			soma += digito*peso[peso.length-str.length()+indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

	public static boolean isValidCPF(String cpf) {
		if ((cpf==null)||(cpf.length()!=11)
				|| (cpf.equals("11111111111")) || (cpf.equals("22222222222")) || (cpf.equals("33333333333")) 
				|| (cpf.equals("44444444444")) || (cpf.equals("55555555555")) || (cpf.equals("66666666666"))
				|| (cpf.equals("77777777777")) || (cpf.equals("88888888888")) || (cpf.equals("99999999999")) 
				|| (cpf.equals("00000000000"))) return false;

		Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
		Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
		return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
	}

	public static boolean isValidCNPJ(String cnpj) {
		if ((cnpj==null)||(cnpj.length()!=14)
				|| (cnpj.equals("11111111111111")) || (cnpj.equals("22222222222222")) || (cnpj.equals("33333333333333")) 
				|| (cnpj.equals("44444444444444")) || (cnpj.equals("55555555555555")) || (cnpj.equals("66666666666666")) 
				|| (cnpj.equals("77777777777777")) || (cnpj.equals("88888888888888")) || (cnpj.equals("99999999999999")) 
				|| (cnpj.equals("00000000000000"))) return false;

		Integer digito1 = calcularDigito(cnpj.substring(0,12), pesoCNPJ);
		Integer digito2 = calcularDigito(cnpj.substring(0,12) + digito1, pesoCNPJ);
		return cnpj.equals(cnpj.substring(0,12) + digito1.toString() + digito2.toString());
	}





	//VALIDAÇÃO DE EMAIL
	public static boolean isValidEmail (String email){
		boolean isEmailIdValid = false;  
		if(email.length()==0){
			return false;
		}else{
			if (email != null && email.length() > 0) {  
				String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
				Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(email);
				if (matcher.matches()) {  
					isEmailIdValid = true;  
				}  
			}  
			return isEmailIdValid;  
		}
	}


	/**UNUSED*/

	//	   public static boolean isValidData (String data, String pessoa){
	//		boolean res = false;
	//		if(data.length() == 10){
	// 	    String[] dataConfirm=data.split("/");
	// 	    try{
	//			int ano = Integer.parseInt(dataConfirm[2]);
	//			int mes = Integer.parseInt(dataConfirm[1]);
	//			int dia = Integer.parseInt(dataConfirm[0]);
	//			
	//			java.sql.Timestamp timeStamp = new java.sql.Timestamp(System.currentTimeMillis()); 
	//			
	//			if(pessoa == "func"){
	//			if(anoValido(ano,mes,dia) == false || idade (ano,mes,dia) == false){
	//				res = false;
	//			}else{
	//				res = true;
	//			}
	//			}else{
	//				if(anoValido(ano,mes,dia) == false || idadeCli (ano,mes,dia) == false){
	//					res = false;
	//				}else{
	//					res = true;
	//				}
	//			}
	//			
	//			
	// 	    
	// 	    }catch(java.lang.ArrayIndexOutOfBoundsException e){
	// 	    	res = false;
	// 	    }
	//			
	//		}else{
	//			res = false;
	//		}
	// 	   
	//          return res;
	//    }
	//	private static boolean anoValido(int ano, int mes, int dia){
	//		boolean res = false;
	//		java.sql.Timestamp timeStamp = new java.sql.Timestamp(System.currentTimeMillis()); 
	//		String dataAtual[] = timeStamp.toString().substring(0,10).split("-");
	//		int anoPermitido = Integer.parseInt(dataAtual[0])-50;
	//		
	//		if((ano % 4 == 0) && ((ano % 100 != 0) || (ano % 400 == 0))){
	//			if((ano<=anoPermitido)||(mes <=0)||(mes>12)||((mes==01)&&(dia>=32))||((mes==02)&&(dia>=30))||((mes==03)&&(dia>=32))||((mes==04)&&(dia>=31))||((mes==05)&&(dia>=32))||((mes==06)&&(dia>=31))||((mes==07)&&(dia>=32))||((mes == 8)&&(dia>=32))||((mes == 9)&&(dia>=31))||((mes==10)&&(dia>=32))||((mes==11)&&(dia>=31))||((mes==12)&&(dia>=32)))
	//			res = false;
	//			else
	//			res = true;
	//		}else{  
	//			if((ano<=anoPermitido)||(mes <=0)||(mes>12)||((mes==01)&&(dia>=32))||((mes==02)&&(dia>=29))||((mes==03)&&(dia>=32))||((mes==04)&&(dia>=31))||((mes==05)&&(dia>=32))||((mes==06)&&(dia>=31))||((mes==07)&&(dia>=32))||((mes==8)&&(dia>=32))||((mes == 9)&&(dia>=31))||((mes==10)&&(dia>=32))||((mes==11)&&(dia>=31))||((mes==12)&&(dia>=32)))
	//			res = false;
	//			else
	//		    res = true;
	//		}
	//		
	//		
	//		return res;
	//		
	//	}

	//	private static boolean idade(int ano, int mes, int dia){
	//		boolean res = false;
	//		java.sql.Timestamp timeStamp = new java.sql.Timestamp(System.currentTimeMillis()); 
	//		String dataAtual[] = timeStamp.toString().substring(0,10).split("-");
	//		int anoAtual = Integer.parseInt(dataAtual[0]);
	//		int mesAtual = Integer.parseInt(dataAtual[1]);
	//		int diaAtual = Integer.parseInt(dataAtual[2]);
	//		
	//
	//		System.out.println("data atual:"+ diaAtual+"/"+mesAtual+"/"+anoAtual);
	//		System.out.println("data niver:"+ dia+"/"+mes+"/"+ano);
	//		
	//		int idade = 0;
	//		boolean niver = false;
	//		if(mes < mesAtual ||( mes == mesAtual && dia <= diaAtual)){
	//			niver = true;
	//		}else{
	//			niver = false;
	//		}
	//
	//		idade = anoAtual - ano;
	//
	//		if(idade > 14){
	//			
	//			res = true;
	//		}else{
	//			if(idade <= 13){
	//				res = false;
	//			}else{
	//				if(idade == 14 && niver == true){
	//					res = true;
	//				}else{
	//					res = false;
	//				}
	//			}
	//		}
	//		return res;
	//	}
	//	
	//	
	//	private static boolean idadeCli(int ano, int dia, int mes){
	//		boolean res = false;
	//		java.sql.Timestamp timeStamp = new java.sql.Timestamp(System.currentTimeMillis()); 
	//		String dataAtual[] = timeStamp.toString().substring(0,10).split("-");
	//		int anoPermitido = Integer.parseInt(dataAtual[0]);
	//		int mesAtual = Integer.parseInt(dataAtual[1]);
	//		int diaAtual = Integer.parseInt(dataAtual[2]);
	//		
	//		int idade = 0;
	//		
	//		if(mes < mesAtual ||( mes == mesAtual && dia < diaAtual)){
	//		   idade = (anoPermitido-1) - ano;
	//		   if(idade <= 14)
	//			   res = false;
	//		   else
	//			   res = true;		   
	//		}else{
	//		   idade = anoPermitido-ano;
	//		 
	//		   if(idade <= 14)
	//			   res = false;
	//		   else
	//			   res = true;	
	//		}
	//		return res;
	//	}


}