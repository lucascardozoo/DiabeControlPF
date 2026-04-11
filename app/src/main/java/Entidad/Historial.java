package Entidad;

import java.sql.Time;

public class Historial {
    private String fecha;
    private String hora;
    private String estAlimenticia;
    private int glucemia;
    private int carbohidratos;
    private int insulina;
    private String descripcion;
    public Historial() {
    }
    public Historial(String fecha, String hora, String estAlimenticia,
                     int glucemia, int carbohidratos,
                     int insulina, String descripcion) {
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

    public int getGlucemia() {
        return glucemia;
    }

    public void setGlucemia(int glucemia) {
        this.glucemia = glucemia;
    }

    public int getCarbohidratos() {
        return carbohidratos;
    }

    public void setCarbohidratos(int carbohidratos) {
        this.carbohidratos = carbohidratos;
    }

    public int getInsulina() {
        return insulina;
    }

    public void setInsulina(int insulina) {
        this.insulina = insulina;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
