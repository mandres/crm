package py.com.quality.modelos;

public class EstadoVendedor {
    private int id_estadovendedor;
    private String descripcion_estadovendedor;

    public EstadoVendedor() {
    }

    public EstadoVendedor(int id_estadovendedor, String descripcion_estadovendedor) {
        this.id_estadovendedor = id_estadovendedor;
        this.descripcion_estadovendedor = descripcion_estadovendedor;
    }

    public int getId_estadovendedor() {
        return id_estadovendedor;
    }

    public void setId_estadovendedor(int id_estadovendedor) {
        this.id_estadovendedor = id_estadovendedor;
    }

    public String getDescripcion_estadovendedor() {
        return descripcion_estadovendedor;
    }

    public void setDescripcion_estadovendedor(String descripcion_estadovendedor) {
        this.descripcion_estadovendedor = descripcion_estadovendedor;
    }

   
    
}
