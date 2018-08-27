package efesio.com.br.app.enums;

public enum UF {
	AC(1,"AC","Acre"),
	AL(2,"AL","Alagoas"),
	AP(4,"AP","Amapá"),
	AM(3,"AM","Amazonas"),
	BA(5,"BA","Bahia"),
	CE(6,"CE","Ceara"),
	DF(7,"DF","Distrito Federal"),
	ES(8,"ES","Espírito Santo"),
	GO(9,"GO","Goiás"),
	MA(10,"MA","Maranhão"),
	MT(13,"MT","Mato Grosso"),
	MS(12,"MS","Mato Grosso do Sul"),
	MG(11,"MG","Minas Gerais"),
	PA(14,"PA","Pará"),
	PB(15,"PB","Paraíba"),
	PR(18,"PR","Paraná"),
	PE(16,"PE","Pernambuco"),
	PI(17,"PI","Piauí"),
	RJ(19,"RJ","Rio de Janeiro"),
	RN(20,"RN","Rio Grande do Norte"),
	RS(23,"RS","Rio Grande do Sul"),
	RO(21,"RO","Rondônia"),
	RR(22,"RR","Roraima"),
	SC(24,"SC","Santa Catarina"),
	SP(26,"SP","São Paulo"),
	SE(25,"SE","Sergipe"),
	TO(27,"TO","Tocantins");

	private int value;
	private String uf; 
	private String nome;
	
	private UF(int value, String uf, String nome){
		this.value = value;
		this.uf = uf;
		this.nome = nome;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getUf(){
		return uf;
	}
	
	public String getNome(){
		return nome;
	}
	
	@Override
	public String toString(){
		return uf;
	}
	
	public static UF getByUf(String uf) throws Exception{
		if(uf == null || uf.isEmpty())throw new Exception("UF inválido: "+uf);
		for(UF t : values()){
			if(t.getUf().equals(uf)){
				return t;
			}
		}
		throw new Exception("UF inválido: "+uf);
	}
	
	public static UF getByValue(int value) throws Exception{
		for(UF t : values()){
			if(t.getValue() == value){
				return t;
			}
		}
		throw new Exception("Número da UF inválido: "+value);
	}
}