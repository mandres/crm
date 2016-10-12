package py.com.quality.modelos;

import org.json.simple.JSONObject;

public class Menu {
    private int id_menu;
    private String nombre_menu;
    private String url_menu;
    private Sistema sistema;
    private Usuario usuario_auditoria;

    public Menu() {
    }

    public Menu(int id_menu, String nombre_menu, String url_menu, Sistema sistema, Usuario usuario_auditoria) {
        this.id_menu = id_menu;
        this.nombre_menu = nombre_menu;
        this.url_menu = url_menu;
        this.sistema = sistema;
        this.usuario_auditoria = usuario_auditoria;
    }

    public int getId_menul() {
        return id_menu;
    }

    public void setId_menul(int id_menu) {
        this.id_menu = id_menu;
    }

    public String getNombre_menu() {
        return nombre_menu;
    }

    public void setNombre_menu(String nombre_menu) {
        this.nombre_menu = nombre_menu;
    }

    public String getUrl_menu() {
        return url_menu;
    }

    public void setUrl_menu(String url_menu) {
        this.url_menu = url_menu;
    }

    public Sistema getSistema() {
        return sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    public Usuario getUsuario_auditoria() {
        return usuario_auditoria;
    }

    public void setUsuario_auditoria(Usuario usuario_auditoria) {
        this.usuario_auditoria = usuario_auditoria;
    }

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("id_menu", this.id_menu);
        obj.put("nombre_menu", this.nombre_menu);
        obj.put("url_menu", this.url_menu);
        obj.put("id_sistema", this.getSistema().getId_sistema());
        obj.put("nombre_sistema", this.getSistema().getNombre_sistema());
        obj.put("url_sistema", this.getSistema().getUrl_sistema());
        obj.put("id_usuario_auditoria", this.getUsuario_auditoria().getId_usuario());
        obj.put("nombre_usuario_auditoria", this.getUsuario_auditoria().getNombre_usuario());
        obj.put("usuario_usuario_auditoria", this.getUsuario_auditoria().getUsuario_usuario());
        return obj;
    }

}
