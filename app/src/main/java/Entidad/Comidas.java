package Entidad;
public class Comidas {
    private String emailUsuario;
    private String descripcion;
    private String cantCarbohidratos;

    public Comidas(){
    }
    public Comidas(String descripcion, String cantCarbohidratos) {
        this.descripcion = descripcion;
        this.cantCarbohidratos = cantCarbohidratos;
    }
    public String getEmailUsuario() {return emailUsuario;}
    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getCantCarbohidratos() {
        return cantCarbohidratos;
    }
    public void setCantCarbohidratos(String cantCarbohidratos) {this.cantCarbohidratos = cantCarbohidratos;
    }
    @Override
    public String toString() {
        return "Comidas{" +
                "emailUsuario='" + emailUsuario + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", cantCarbohidratos=" + cantCarbohidratos +
                '}';
    }
}
