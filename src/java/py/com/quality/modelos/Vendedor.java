
package py.com.quality.modelos;

import java.sql.Timestamp;

public class Vendedor {
    private int id_vendedor;
    private String nombre_vendedor;
    private Seccion seccion;
    private Timestamp fecha_inicioatencion;
    private Timestamp fecha_finatencion;
    private EstadoVendedor estadovendedor;

    public Vendedor() {
    }

    public Vendedor(int id_vendedor, String nombre_vendedor, Seccion seccion, Timestamp fecha_inicioatencion, Timestamp fecha_finatencion, EstadoVendedor estadovendedor) {
        this.id_vendedor = id_vendedor;
        this.nombre_vendedor = nombre_vendedor;
        this.seccion = seccion;
        this.fecha_inicioatencion = fecha_inicioatencion;
        this.fecha_finatencion = fecha_finatencion;
        this.estadovendedor = estadovendedor;
    }

    public int getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public String getNombre_vendedor() {
        return nombre_vendedor;
    }

    public void setNombre_vendedor(String nombre_vendedor) {
        this.nombre_vendedor = nombre_vendedor;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public Timestamp getFecha_inicioatencion() {
        return fecha_inicioatencion;
    }

    public void setFecha_inicioatencion(Timestamp fecha_inicioatencion) {
        this.fecha_inicioatencion = fecha_inicioatencion;
    }

    public Timestamp getFecha_finatencion() {
        return fecha_finatencion;
    }

    public void setFecha_finatencion(Timestamp fecha_finatencion) {
        this.fecha_finatencion = fecha_finatencion;
    }

    public EstadoVendedor getEstadovendedor() {
        return estadovendedor;
    }

    public void setEstadovendedor(EstadoVendedor estadovendedor) {
        this.estadovendedor = estadovendedor;
    }

   
}
