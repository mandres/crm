package py.com.quality.modelos;

import org.json.simple.JSONObject;

public class Empresa {
    private int id_empresa;
    private String nombre_empresa;
    private Usuario usuario_auditoria;

    public Empresa() {
    }

    public Empresa(int id_empresa, String nombre_empresa, Usuario usuario_auditoria) {
        this.id_empresa = id_empresa;
        this.nombre_empresa = nombre_empresa;
        this.usuario_auditoria = usuario_auditoria;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public Usuario getUsuario_auditoria() {
        return usuario_auditoria;
    }

    public void setUsuario_auditoria(Usuario usuario_auditoria) {
        this.usuario_auditoria = usuario_auditoria;
    }

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("id_empresa", this.id_empresa);
        obj.put("nombre_empresa", this.nombre_empresa);
        obj.put("id_usuario_auditoria", this.getUsuario_auditoria().getId_usuario());
        obj.put("nombre_usuario_auditoria", this.getUsuario_auditoria().getNombre_usuario());
        obj.put("usuario_usuario_auditoria", this.getUsuario_auditoria().getUsuario_usuario());
        return obj;
    }

}
