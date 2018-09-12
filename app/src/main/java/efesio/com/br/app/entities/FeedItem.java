package efesio.com.br.app.entities;

public class FeedItem {

    public enum Tipo{
        AGENDA(1), EVENTOS(2), GALERIA(3), ANIVERSARIANTE(4);

        int type;
        Tipo(int type){
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public static Tipo getByType(int type){
            for(Tipo t : values()){
                if (t.getType() == type)
                    return t;
            }
            return null;
        }
    }

    public FeedItem(){
    }

    public FeedItem(int photoId, String legenda, Tipo tipo, int position){
        setPhotoId(photoId);
        setLegenda(legenda);
        setTipo(tipo);
        setPosition(position);
    }

    private Tipo tipo;
    private int photoId;
    private String legenda;
    private int position;

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getLegenda() {
        return legenda;
    }

    public void setLegenda(String legenda) {
        this.legenda = legenda;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
