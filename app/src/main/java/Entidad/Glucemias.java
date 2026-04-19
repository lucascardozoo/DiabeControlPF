package Entidad;
public class Glucemias {
    private String emailUsuario;
    private String nivelGlucemia;
    private String estacionAlimenticia;
    private String horario;
    private String fecha;

    public Glucemias(){
    }
    public Glucemias(String emailUsuario, String nivelGlucemia, String estacionAlimenticia, String horario, String fecha) {
        this.emailUsuario = emailUsuario;
        this.nivelGlucemia = nivelGlucemia;
        this.estacionAlimenticia = estacionAlimenticia;
        this.horario = horario;
        this.fecha = fecha;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getNivelGlucemia() {
        return nivelGlucemia;
    }

    public void setNivelGlucemia(String nivelGlucemia) {
        this.nivelGlucemia = nivelGlucemia;
    }

    public String getEstacionAlimenticia() {
        return estacionAlimenticia;
    }

    public void setEstacionAlimenticia(String estacionAlimenticia) {
        this.estacionAlimenticia = estacionAlimenticia;
    }
    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Glucemias{" +
                "emailUsuario='" + emailUsuario + '\'' +
                ", nivelGlucemia='" + nivelGlucemia + '\'' +
                ", estacionAlimenticia='" + estacionAlimenticia + '\'' +
                ", horario='" + horario + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
