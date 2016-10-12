package py.com.quality.modelos;

import java.sql.Timestamp;
import org.json.simple.JSONObject;

public class Atencion {
    private int id_atencion;
    private String nombre_usuario;
    private Usuario usuario;
    private Vendedor vendedor;
    private Timestamp fechahora_recepcion;
    private Timestamp fechahora_inicioatencion;
    private Timestamp fechahora_finatencion;
    private EstadoAtencion estadoatencion;
    private Cliente cliente;

    public Atencion() {
    }

    public Atencion(int id_atencion, String nombre_usuario, Usuario usuario, Vendedor vendedor, Timestamp fechahora_recepcion, Timestamp fechahora_inicioatencion, Timestamp fechahora_finatencion, EstadoAtencion estadoatencion, Cliente cliente) {
        this.id_atencion = id_atencion;
        this.nombre_usuario = nombre_usuario;
        this.usuario = usuario;
        this.vendedor = vendedor;
        this.fechahora_recepcion = fechahora_recepcion;
        this.fechahora_inicioatencion = fechahora_inicioatencion;
        this.fechahora_finatencion = fechahora_finatencion;
        this.estadoatencion = estadoatencion;
        this.cliente = cliente;
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
    

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("id_atencion", this.id_atencion);
        obj.put("id_usuario", this.getUsuario().getId_usuario());
        obj.put("nombre_usuario", this.getUsuario().getNombre_usuario());
        obj.put("id_vendedor", this.getVendedor().getId_vendedor());
        obj.put("nombre_vendedor", this.getVendedor().getNombre_vendedor());
        obj.put("fechahora_recepcion", this.getFechahora_recepcion());
        obj.put("fechahora_inicioatencion", this.getFechahora_inicioatencion());
        obj.put("fechahora_finatencion", this.getFechahora_finatencion());
        obj.put("id_estadoatencion", this.getEstadoatencion().getId_estadoatencion());
        obj.put("descripcion_estadoatencion", this.getEstadoatencion().getDescripcion_estadoatencion());
        obj.put("id_cliente", this.getCliente().getId_cliente());
        obj.put("nombre_cliente", this.getCliente().getNombre_cliente());
        return obj;
    }
}
