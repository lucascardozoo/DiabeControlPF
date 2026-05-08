package Entidad;

public class RegistrosParaAsistente {

    private String emailUsuario;
    private String glucemia;
    private String carbohidratos;
    private String estAlimenticia;
    private String factorCorreccion;
    private String relacionInsulinaHidratos;
    private String umbralMin;
    private String umbralMax;
    private String insulina;
    private String dosisCarbo;
    private String dosisCorreccion;
    private long idComida;
    private long idGlucemia;

    public RegistrosParaAsistente() {
    }

    public RegistrosParaAsistente(String emailUsuario, String glucemia, String carbohidratos, String estAlimenticia, String factorCorreccion, String relacionInsulinaHidratos, String umbralMin, String umbralMax, String insulina, String dosisCarbo, String dosisCorreccion, long idComida, long idGlucemia) {
        this.emailUsuario = emailUsuario;
        this.glucemia = glucemia;
        this.carbohidratos = carbohidratos;
        this.estAlimenticia = estAlimenticia;
        this.factorCorreccion = factorCorreccion;
        this.relacionInsulinaHidratos = relacionInsulinaHidratos;
        this.umbralMin = umbralMin;
        this.umbralMax = umbralMax;
        this.insulina = insulina;
        this.dosisCarbo = dosisCarbo;
        this.dosisCorreccion = dosisCorreccion;
        this.idComida = idComida;
        this.idGlucemia = idGlucemia;
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

    public String getUmbralMin() {
        return umbralMin;
    }

    public void setUmbralMin(String umbralMin) {
        this.umbralMin = umbralMin;
    }

    public String getUmbralMax() {
        return umbralMax;
    }

    public void setUmbralMax(String umbralMax) {
        this.umbralMax = umbralMax;
    }

    public String getInsulina() {
        return insulina;
    }

    public void setInsulina(String insulina) {
        this.insulina = insulina;
    }

    public String getDosisCarbo() {
        return dosisCarbo;
    }

    public void setDosisCarbo(String dosisCarbo) {
        this.dosisCarbo = dosisCarbo;
    }

    public String getDosisCorreccion() {
        return dosisCorreccion;
    }

    public void setDosisCorreccion(String dosisCorreccion) {
        this.dosisCorreccion = dosisCorreccion;
    }

    public long getIdComida() {
        return idComida;
    }

    public void setIdComida(long idComida) {
        this.idComida = idComida;
    }

    public long getIdGlucemia() {
        return idGlucemia;
    }

    public void setIdGlucemia(long idGlucemia) {
        this.idGlucemia = idGlucemia;
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
                ", umbralMin='" + umbralMin + '\'' +
                ", umbralMax='" + umbralMax + '\'' +
                ", insulina='" + insulina + '\'' +
                ", dosisCarbo='" + dosisCarbo + '\'' +
                ", dosisCorreccion='" + dosisCorreccion + '\'' +
                ", idComida=" + idComida +
                ", idGlucemia=" + idGlucemia +
                '}';
    }
}
