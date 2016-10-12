package py.com.quality.modelos;

import java.sql.Date;
import java.sql.Timestamp;
import org.json.simple.JSONObject;

public class Cliente {

    private int id_cliente;
    private int codigo_cliente;
    private String cedula_cliente;
    private String ruc_cliente;
    private String nombre_cliente;
    private String direccion_cliente;
    private Ciudad ciudad;
    private Date fecha_nacimiento_cliente;
    private String mail_cliente;
    private String telefono_cliente;
    private String contacto_cliente;
    private Timestamp actualizacion_cliente;
    private Tipocliente tipocliente;
    private Usuario usuario_auditoria;

    public Cliente() {
    }

    public Cliente(int id_cliente, int codigo_cliente, String cedula_cliente, String ruc_cliente, String nombre_cliente, String direccion_cliente, Ciudad ciudad, Date fecha_nacimiento_cliente, String mail_cliente, String telefono_cliente, String contacto_cliente, Timestamp actualizacion_cliente, Tipocliente tipocliente, Usuario usuario_auditoria) {
        this.id_cliente = id_cliente;
        this.codigo_cliente = codigo_cliente;
        this.cedula_cliente = cedula_cliente;
        this.ruc_cliente = ruc_cliente;
        this.nombre_cliente = nombre_cliente;
        this.direccion_cliente = direccion_cliente;
        this.ciudad = ciudad;
        this.fecha_nacimiento_cliente = fecha_nacimiento_cliente;
        this.mail_cliente = mail_cliente;
        this.telefono_cliente = telefono_cliente;
        this.contacto_cliente = contacto_cliente;
        this.actualizacion_cliente = actualizacion_cliente;
        this.tipocliente = tipocliente;
        this.usuario_auditoria = usuario_auditoria;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getCodigo_cliente() {
        return codigo_cliente;
    }

    public void setCodigo_cliente(int codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }

    public String getCedula_cliente() {
        return cedula_cliente;
    }

    public void setCedula_cliente(String cedula_cliente) {
        this.cedula_cliente = cedula_cliente;
    }

    public String getRuc_cliente() {
        return ruc_cliente;
    }

    public void setRuc_cliente(String ruc_cliente) {
        this.ruc_cliente = ruc_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getDireccion_cliente() {
        return direccion_cliente;
    }

    public void setDireccion_cliente(String direccion_cliente) {
        this.direccion_cliente = direccion_cliente;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Date getFecha_nacimiento_cliente() {
        return fecha_nacimiento_cliente;
    }

    public void setFecha_nacimiento_cliente(Date fecha_nacimiento_cliente) {
        this.fecha_nacimiento_cliente = fecha_nacimiento_cliente;
    }

    public String getMail_cliente() {
        return mail_cliente;
    }

    public void setMail_cliente(String mail_cliente) {
        this.mail_cliente = mail_cliente;
    }

    public String getTelefono_cliente() {
        return telefono_cliente;
    }

    public void setTelefono_cliente(String telefono_cliente) {
        this.telefono_cliente = telefono_cliente;
    }

    public String getContacto_cliente() {
        return contacto_cliente;
    }

    public void setContacto_cliente(String contacto_cliente) {
        this.contacto_cliente = contacto_cliente;
    }

    public Timestamp getActualizacion_cliente() {
        return actualizacion_cliente;
    }

    public void setActualizacion_cliente(Timestamp actualizacion_cliente) {
        this.actualizacion_cliente = actualizacion_cliente;
    }

    public Tipocliente getTipocliente() {
        return tipocliente;
    }

    public void setTipocliente(Tipocliente tipocliente) {
        this.tipocliente = tipocliente;
    }

    public Usuario getUsuario_auditoria() {
        return usuario_auditoria;
    }

    public void setUsuario_auditoria(Usuario usuario_auditoria) {
        this.usuario_auditoria = usuario_auditoria;
    }

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("id_cliente", this.id_cliente);
        obj.put("codigo_cliente", this.codigo_cliente);
        obj.put("cedula_cliente", this.cedula_cliente);
        obj.put("ruc_cliente", this.ruc_cliente);
        obj.put("nombre_cliente", this.nombre_cliente);
        obj.put("direccion_cliente", this.direccion_cliente);
        obj.put("id_ciudad", this.getCiudad().getId_ciudad());
        obj.put("nombre_ciudad", this.getCiudad().getNombre_ciudad());
        if (this.getFecha_nacimiento_cliente() != null) {
            obj.put("fecha_nacimiento_cliente", this.getFecha_nacimiento_cliente().toString());
        }
        obj.put("mail_cliente", this.mail_cliente);
        obj.put("telefono_cliente", this.telefono_cliente);
        obj.put("contacto_cliente", this.contacto_cliente);
        if (this.getActualizacion_cliente() != null) {
            obj.put("actualizacion_cliente", this.actualizacion_cliente.toString());
        }
        obj.put("id_tipocliente", this.getTipocliente().getId_tipocliente());
        obj.put("nombre_tipocliente", this.getTipocliente().getNombre_tipocliente());
        obj.put("id_usuario_auditoria", this.getUsuario_auditoria().getId_usuario());
        obj.put("nombre_usuario_auditoria", this.getUsuario_auditoria().getNombre_usuario());
        obj.put("usuario_usuario_auditoria", this.getUsuario_auditoria().getUsuario_usuario());
        return obj;
    }

}
