package py.com.quality.modelos;

import org.json.simple.JSONObject;

public class Rol {
    private int id_rol;
    private String nombre_rol;
    private Usuario usuario_auditoria;

    public Rol() {
    }

    public Rol(int id_rol, String nombre_rol, Usuario usuario_auditoria) {
        this.id_rol = id_rol;
        this.nombre_rol = nombre_rol;
        this.usuario_auditoria = usuario_auditoria;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }

    public Usuario getUsuario_auditoria() {
        return usuario_auditoria;
    }

    public void setUsuario_auditoria(Usuario usuario_auditoria) {
        this.usuario_auditoria = usuario_auditoria;
    }

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("id_rol", this.id_rol);
        obj.put("nombre_rol", this.nombre_rol);
        obj.put("id_usuario_auditoria", this.getUsuario_auditoria().getId_usuario());
        obj.put("nombre_usuario_auditoria", this.getUsuario_auditoria().getNombre_usuario());
        obj.put("usuario_usuario_auditoria", this.getUsuario_auditoria().getUsuario_usuario());
        return obj;
    }
}
