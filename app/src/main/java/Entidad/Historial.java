package Entidad;

import java.sql.Time;

public class Historial {
    private String fecha;
    private String hora;
    private String estAlimenticia;
    private String glucemia;
    private String carbohidratos;
    private String insulina;
    private String descripcion;

    public Historial() {
    }

    public Historial(String fecha, String hora, String estAlimenticia, String glucemia, String carbohidratos, String insulina, String descripcion) {
        this.fecha = fecha;
        this.hora = hora;
        this.estAlimenticia = estAlimenticia;
        this.glucemia = glucemia;
        this.carbohidratos = carbohidratos;
        this.insulina = insulina;
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEstAlimenticia() {
        return estAlimenticia;
    }

    public void setEstAlimenticia(String estAlimenticia) {
        this.estAlimenticia = estAlimenticia;
    }

    public String getGlucemia() {
        return glucemia;
    }

    public void setGlucemia(String glucemia) {
        this.glucemia = glucemia;
    }

    public String getCarbohidratos() {
        return carbohidratos;
    }

    public void setCarbohidratos(String carbohidratos) {
        this.carbohidratos = carbohidratos;
    }

    public String getInsulina() {
        return insulina;
    }

    public void setInsulina(String insulina) {
        this.insulina = insulina;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Historial{" +
                "fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", estAlimenticia='" + estAlimenticia + '\'' +
                ", glucemia='" + glucemia + '\'' +
                ", carbohidratos='" + carbohidratos + '\'' +
                ", insulina='" + insulina + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
