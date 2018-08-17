package efesio.com.br.app;

public class Photo {

    public Photo(){
    }

    public Photo(int photoId, String legenda){
        setPhotoId(photoId);
        setLegenda(legenda);
    }

    public int photoId;
    public String legenda;

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
}
