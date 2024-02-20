package modelo;

public class Conexion {

    private Integer origen;
    private Integer destino;

    public Conexion(Integer origen, Integer destino) {
        this.origen = origen;
        this.destino = destino;
    }

    public Integer getOrigen() {
        return origen;
    }

    public void setOrigen(Integer origen) {
        this.origen = origen;
    }

    public Integer getDestino() {
        return destino;
    }

    public void setDestino(Integer destino) {
        this.destino = destino;
    }

}
