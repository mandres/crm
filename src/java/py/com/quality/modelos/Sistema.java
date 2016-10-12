package py.com.quality.modelos;

import org.json.simple.JSONObject;

public class Sistema {
    private int id_sistema;
    private String nombre_sistema;
    private String url_sistema;
    private Usuario usuario_auditoria;

    public Sistema() {
    }

    public Sistema(int id_sistema, String nombre_sistema, String url_sistema, Usuario usuario_auditoria) {
        this.id_sistema = id_sistema;
        this.nombre_sistema = nombre_sistema;
        this.url_sistema = url_sistema;
        this.usuario_auditoria = usuario_auditoria;
    }

    public int getId_sistema() {
        return id_sistema;
    }

    public void setId_sistema(int id_sistema) {
        this.id_sistema = id_sistema;
    }

    public String getNombre_sistema() {
        return nombre_sistema;
    }

    public void setNombre_sistema(String nombre_sistema) {
        this.nombre_sistema = nombre_sistema;
    }

    public String getUrl_sistema() {
        return url_sistema;
    }

    public void setUrl_sistema(String url_sistema) {
        this.url_sistema = url_sistema;
    }

    public Usuario getUsuario_auditoria() {
        return usuario_auditoria;
    }

    public void setUsuario_auditoria(Usuario usuario_auditoria) {
        this.usuario_auditoria = usuario_auditoria;
    }

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("id_sistema", this.id_sistema);
        obj.put("nombre_sistema", this.nombre_sistema);
        obj.put("url_sistema", this.url_sistema);
        obj.put("id_usuario_auditoria", this.getUsuario_auditoria().getId_usuario());
        obj.put("nombre_usuario_auditoria", this.getUsuario_auditoria().getNombre_usuario());
        obj.put("usuario_usuario_auditoria", this.getUsuario_auditoria().getUsuario_usuario());
        return obj;
    }

}
