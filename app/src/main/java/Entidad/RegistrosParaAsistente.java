package Entidad;

public class RegistrosParaAsistente {

    private String emailUsuario;
    private String glucemia;
    private String carbohidratos;
    private String estAlimenticia;
    private String factorCorreccion;
    private String relacionInsulinaHidratos;

    public RegistrosParaAsistente(){}
    public RegistrosParaAsistente(String emailUsuario, String glucemia, String carbohidratos, String estAlimenticia, String factorCorreccion, String relacionInsulinaHidratos) {
        this.emailUsuario = emailUsuario;
        this.glucemia = glucemia;
        this.carbohidratos = carbohidratos;
        this.estAlimenticia = estAlimenticia;
        this.factorCorreccion = factorCorreccion;
        this.relacionInsulinaHidratos = relacionInsulinaHidratos;
    }
    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
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

    public String getEstAlimenticia() {
        return estAlimenticia;
    }

    public void setEstAlimenticia(String estAlimenticia) {
        this.estAlimenticia = estAlimenticia;
    }

    public String getFactorCorreccion() {
        return factorCorreccion;
    }

    public void setFactorCorreccion(String factorCorreccion) {
        this.factorCorreccion = factorCorreccion;
    }

    public String getRelacionInsulinaHidratos() {
        return relacionInsulinaHidratos;
    }

    public void setRelacionInsulinaHidratos(String relacionInsulinaHidratos) {
        this.relacionInsulinaHidratos = relacionInsulinaHidratos;
    }

    @Override
    public String toString() {
        return "RegistrosParaAsistente{" +
                "emailUsuario='" + emailUsuario + '\'' +
                ", glucemia='" + glucemia + '\'' +
                ", carbohidratos='" + carbohidratos + '\'' +
                ", estAlimenticia='" + estAlimenticia + '\'' +
                ", factorCorreccion='" + factorCorreccion + '\'' +
                ", relacionInsulinaHidratos='" + relacionInsulinaHidratos + '\'' +
                '}';
    }
}
