package Entidad;

public class TipoInsulina {

    private int id;
    private String nomInsulina;
    private String tipo;
    private int cantInsulina;
    public TipoInsulina()
    {
    }
    public TipoInsulina(int id, String nomInsulina, String tipo) {
        this.nomInsulina = nomInsulina;
        this.tipo = tipo;
        this.id = id;
    }

    public void setNomInsulina(String nomInsulina){
        this.nomInsulina = nomInsulina;
    }
    public void setTipoInsulina(String tipo){
        this.tipo = tipo;
    }
    public int setCantInsulina(int cantInsulina) {this.cantInsulina = cantInsulina;
        return cantInsulina;
    }
    public String getNomInsulina(){
        return nomInsulina;
    }
    public String getTipoInsulina(){
        return tipo;
    }
    public int getCantInsulina() {return cantInsulina;}
}
