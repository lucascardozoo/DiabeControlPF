package Entidad;

import java.sql.Time;

public class DiayHora {
    private String dia;
    private Time hora;

    public DiayHora(){
    }
    public DiayHora(String dia, Time hora){
        this.dia = dia;
        this.hora = hora;
    }
    public String getDia() {
        return dia;
    }
    public void setDia(String dia) {
        this.dia = dia;
    }
    public Time getHora() {
        return hora;
    }
    public void setHora(Time hora) {
        this.hora = hora;
    }
}
