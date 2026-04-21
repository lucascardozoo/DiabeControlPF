package Entidad;
public class Comidas {
    private String emailUsuario;
    private String descripcion;
    private String cantCarbohidratos;
    private long idGlucemia;

    public Comidas(){
    }

    public Comidas(String emailUsuario, String descripcion, String cantCarbohidratos, Integer idGlucemia) {
        this.emailUsuario = emailUsuario;
        this.descripcion = descripcion;
        this.cantCarbohidratos = cantCarbohidratos;
        this.idGlucemia = idGlucemia;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

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

    public void setCantCarbohidratos(String cantCarbohidratos) {
        this.cantCarbohidratos = cantCarbohidratos;
    }

    public long getIdGlucemia() {
        return idGlucemia;
    }

    public void setIdGlucemia(long idGlucemia) {
        this.idGlucemia = idGlucemia;
    }

    @Override
    public String toString() {
        return "Comidas{" +
                "emailUsuario='" + emailUsuario + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", cantCarbohidratos='" + cantCarbohidratos + '\'' +
                ", idGlucemia=" + idGlucemia +
                '}';
    }
}
