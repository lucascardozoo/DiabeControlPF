package Entidad;

public class ParametrosConfig {

    private String emailUsuario;
    private String tipoInsulinaRapida;
    private String tipoInsulinaBasal;
    private String factorCorreccion;
    private String umbralMinCorreccion;
    private String umbralMaxCorreccion;
    private String relacionInsulinaHidratos;

    public ParametrosConfig()
    {
    }

    public ParametrosConfig(String emailUsuario, String tipoInsulinaRapida, String tipoInsulinaBasal, String factorCorreccion, String umbralMinCorreccion, String umbralMaxCorreccion, String relacionInsulinaHidratos)
    {
        this.emailUsuario = emailUsuario;
        this.tipoInsulinaRapida = tipoInsulinaRapida;
        this.tipoInsulinaBasal = tipoInsulinaBasal;
        this.factorCorreccion = factorCorreccion;
        this.umbralMinCorreccion = umbralMinCorreccion;
        this.umbralMaxCorreccion = umbralMaxCorreccion;
        this.relacionInsulinaHidratos = relacionInsulinaHidratos;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getTipoInsulinaRapida() {
        return tipoInsulinaRapida;
    }

    public void setTipoInsulinaRapida(String tipoInsulinaRapida) {
        this.tipoInsulinaRapida = tipoInsulinaRapida;
    }

    public String getTipoInsulinaBasal() {
        return tipoInsulinaBasal;
    }

    public void setTipoInsulinaBasal(String tipoInsulinaBasal) {
        this.tipoInsulinaBasal = tipoInsulinaBasal;
    }

    public String getFactorCorreccion() {
        return factorCorreccion;
    }

    public void setFactorCorreccion(String factorCorreccion) {
        this.factorCorreccion = factorCorreccion;
    }

    public String getUmbralMaxCorreccion() {
        return umbralMaxCorreccion;
    }

    public void setUmbralMaxCorreccion(String umbralMaxCorreccion) {
        this.umbralMaxCorreccion = umbralMaxCorreccion;
    }

    public String getUmbralMinCorreccion() {
        return umbralMinCorreccion;
    }

    public void setUmbralMinCorreccion(String umbralMinCorreccion) {
        this.umbralMinCorreccion = umbralMinCorreccion;
    }

    public String getRelacionInsulinaHidratos() {
        return relacionInsulinaHidratos;
    }

    public void setRelacionInsulinaHidratos(String relacionInsulinaHidratos) {
        this.relacionInsulinaHidratos = relacionInsulinaHidratos;
    }

    @Override
    public String toString() {
        return "ParametrosConfig{" +
                "emailUsuario='" + emailUsuario + '\'' +
                ", tipoInsulinaRapida='" + tipoInsulinaRapida + '\'' +
                ", tipoInsulinaBasal='" + tipoInsulinaBasal + '\'' +
                ", factorCorreccion='" + factorCorreccion + '\'' +
                ", umbralMinCorreccion='" + umbralMinCorreccion + '\'' +
                ", umbralMaxCorreccion='" + umbralMaxCorreccion + '\'' +
                ", relacionInsulinaHidratos='" + relacionInsulinaHidratos + '\'' +
                '}';
    }
}
