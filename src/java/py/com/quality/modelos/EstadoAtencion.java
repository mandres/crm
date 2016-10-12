package py.com.quality.modelos;

public class EstadoAtencion {
    private int id_estadoatencion;
    private String descripcion_estadoatencion;

    public EstadoAtencion() {
    }

    public EstadoAtencion(int id_estadoatencion, String descripcion_estadoatencion) {
        this.id_estadoatencion = id_estadoatencion;
        this.descripcion_estadoatencion = descripcion_estadoatencion;
    }

    public int getId_estadoatencion() {
        return id_estadoatencion;
    }

    public void setId_estadoatencion(int id_estadoatencion) {
        this.id_estadoatencion = id_estadoatencion;
    }

    public String getDescripcion_estadoatencion() {
        return descripcion_estadoatencion;
    }

    public void setDescripcion_estadoatencion(String descripcion_estadoatencion) {
        this.descripcion_estadoatencion = descripcion_estadoatencion;
    }
    
}
