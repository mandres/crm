package py.com.quality.modelos;

import java.sql.Timestamp;

public class Atencion {
    private int id_atencion;
    private String nombre_usuario;
    private Usuario usuario;
    private Vendedor vendedor;
    private Timestamp fechahora_recepcion;
    private Timestamp fechahora_asignacion;
    private Timestamp fechahora_inicioatencion;
    private Timestamp fechahora_finatencion;
    private EstadoAtencion estadoatencion;
    private Cliente cliente;
    private Seccion seccion;

    public Atencion() {
    }

    public Atencion(int id_atencion, String nombre_usuario, Usuario usuario, Vendedor vendedor, Timestamp fechahora_recepcion, Timestamp fechahora_asignacion, Timestamp fechahora_inicioatencion, Timestamp fechahora_finatencion, EstadoAtencion estadoatencion, Cliente cliente, Seccion seccion) {
        this.id_atencion = id_atencion;
        this.nombre_usuario = nombre_usuario;
        this.usuario = usuario;
        this.vendedor = vendedor;
        this.fechahora_recepcion = fechahora_recepcion;
        this.fechahora_asignacion = fechahora_asignacion;
        this.fechahora_inicioatencion = fechahora_inicioatencion;
        this.fechahora_finatencion = fechahora_finatencion;
        this.estadoatencion = estadoatencion;
        this.cliente = cliente;
        this.seccion = seccion;
    }

    public int getId_atencion() {
        return id_atencion;
    }

    public void setId_atencion(int id_atencion) {
        this.id_atencion = id_atencion;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Timestamp getFechahora_recepcion() {
        return fechahora_recepcion;
    }

    public void setFechahora_recepcion(Timestamp fechahora_recepcion) {
        this.fechahora_recepcion = fechahora_recepcion;
    }

    public Timestamp getFechahora_asignacion() {
        return fechahora_asignacion;
    }

    public void setFechahora_asignacion(Timestamp fechahora_asignacion) {
        this.fechahora_asignacion = fechahora_asignacion;
    }

    public Timestamp getFechahora_inicioatencion() {
        return fechahora_inicioatencion;
    }

    public void setFechahora_inicioatencion(Timestamp fechahora_inicioatencion) {
        this.fechahora_inicioatencion = fechahora_inicioatencion;
    }

    public Timestamp getFechahora_finatencion() {
        return fechahora_finatencion;
    }

    public void setFechahora_finatencion(Timestamp fechahora_finatencion) {
        this.fechahora_finatencion = fechahora_finatencion;
    }

    public EstadoAtencion getEstadoatencion() {
        return estadoatencion;
    }

    public void setEstadoatencion(EstadoAtencion estadoatencion) {
        this.estadoatencion = estadoatencion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    
}
