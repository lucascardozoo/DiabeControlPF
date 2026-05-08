package Entidad;

public class DosisResultado {
    public double dosisCarbo;
    public double dosisCorreccion;
    public double dosisTotal;

    public DosisResultado() {
    }

    public DosisResultado(double dosisCarbo, double dosisCorreccion, double dosisTotal) {
        this.dosisCarbo = dosisCarbo;
        this.dosisCorreccion = dosisCorreccion;
        this.dosisTotal = dosisTotal;
    }

    public double getDosisCarbo() {
        return dosisCarbo;
    }

    public void setDosisCarbo(double dosisCarbo) {
        this.dosisCarbo = dosisCarbo;
    }

    public double getDosisCorreccion() {
        return dosisCorreccion;
    }

    public void setDosisCorreccion(double dosisCorreccion) {
        this.dosisCorreccion = dosisCorreccion;
    }

    public double getDosisTotal() {
        return dosisTotal;
    }

    public void setDosisTotal(double dosisTotal) {
        this.dosisTotal = dosisTotal;
    }

    @Override
    public String toString() {
        return "DosisResultado{" +
                "dosisCarbo=" + dosisCarbo +
                ", dosisCorreccion=" + dosisCorreccion +
                ", dosisTotal=" + dosisTotal +
                '}';
    }
}
