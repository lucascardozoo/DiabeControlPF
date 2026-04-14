package Entidad;

public class EstacionAlimenticia {
    private int id;
    private String estacionAlimenticia;

    public EstacionAlimenticia() {
    }
    public EstacionAlimenticia(int id, String estacionAlimenticia) {
        this.id = id;
        this.estacionAlimenticia = estacionAlimenticia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstacionAlimenticia() {
        return estacionAlimenticia;
    }

    public void setEstacionAlimenticia(String estacionAlimenticia) {
        this.estacionAlimenticia = estacionAlimenticia;
    }

    @Override
    public String toString() {
        return "EstacionAlimenticia{" +
                "id=" + id +
                ", estacionAlimenticia='" + estacionAlimenticia + '\'' +
                '}';
    }
}
