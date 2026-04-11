package Entidad;

public class Comidas {
    private String descripcion;
    private int cantCarbohidratos;

    public Comidas(){
    }
    public Comidas(String descripcion, int cantCarbohidratos) {
        this.descripcion = descripcion;
        this.cantCarbohidratos = cantCarbohidratos;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getCantCarbohidratos() {
        return cantCarbohidratos;
    }
    public void setCantCarbohidratos(int cantCarbohidratos) {
        this.cantCarbohidratos = cantCarbohidratos;
    }
}
