package Entidad;
public class Glucemias {
    private int glucemia;
    private String estAlimenticia;
    public Glucemias(){
    }
    public Glucemias(int glucemia, String estAlimenticia) {
        this.glucemia = glucemia;
        this.estAlimenticia = estAlimenticia;
    }
    public int getGlucemia() {
        return glucemia;
    }
    public void setGlucemia(int glucemia) {
        this.glucemia = glucemia;
    }
    public String getEstAlimenticia() {
        return estAlimenticia;
    }
    public void setEstAlimenticia(String estAlimenticia) {
        this.estAlimenticia = estAlimenticia;
    }
}
