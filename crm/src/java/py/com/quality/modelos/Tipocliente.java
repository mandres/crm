package py.com.quality.modelos;

import org.json.simple.JSONObject;

public class Tipocliente {
    private int id_tipocliente;
    private String nombre_tipocliente;
    private Usuario usuario_auditoria;

    public Tipocliente() {
    }

    public Tipocliente(int id_tipocliente, String nombre_tipocliente, Usuario usuario_auditoria) {
        this.id_tipocliente = id_tipocliente;
        this.nombre_tipocliente = nombre_tipocliente;
        this.usuario_auditoria = usuario_auditoria;
    }

    public int getId_tipocliente() {
        return id_tipocliente;
    }

    public void setId_tipocliente(int id_tipocliente) {
        this.id_tipocliente = id_tipocliente;
    }

    public String getNombre_tipocliente() {
        return nombre_tipocliente;
    }

    public void setNombre_tipocliente(String nombre_tipocliente) {
        this.nombre_tipocliente = nombre_tipocliente;
    }

    public Usuario getUsuario_auditoria() {
        return usuario_auditoria;
    }

    public void setUsuario_auditoria(Usuario usuario_auditoria) {
        this.usuario_auditoria = usuario_auditoria;
    }

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("id_tipocliente", this.id_tipocliente);
        obj.put("nombre_tipocliente", this.nombre_tipocliente);
        obj.put("id_usuario_auditoria", this.getUsuario_auditoria().getId_usuario());
        obj.put("nombre_usuario_auditoria", this.getUsuario_auditoria().getNombre_usuario());
        obj.put("usuario_usuario_auditoria", this.getUsuario_auditoria().getUsuario_usuario());
        return obj;
    }
}
