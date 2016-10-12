package py.com.quality.modelos;

import org.json.simple.JSONObject;
import py.com.quality.modelos.Menu;

public class Formulario {
    private int id_formulario;
    private String nombre_formulario;
    private String url_formulario;
    private Menu menu;
    private Usuario usuario_auditoria;

    public Formulario() {
    }

    public Formulario(int id_formulario, String nombre_formulario, String url_formulario, Menu menu, Usuario usuario_auditoria) {
        this.id_formulario = id_formulario;
        this.nombre_formulario = nombre_formulario;
        this.url_formulario = url_formulario;
        this.menu = menu;
        this.usuario_auditoria = usuario_auditoria;
    }

    public int getId_formulario() {
        return id_formulario;
    }

    public void setId_formulario(int id_formulario) {
        this.id_formulario = id_formulario;
    }

    public String getNombre_formulario() {
        return nombre_formulario;
    }

    public void setNombre_formulario(String nombre_formulario) {
        this.nombre_formulario = nombre_formulario;
    }

    public String getUrl_formulario() {
        return url_formulario;
    }

    public void setUrl_formulario(String url_formulario) {
        this.url_formulario = url_formulario;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Usuario getUsuario_auditoria() {
        return usuario_auditoria;
    }

    public void setUsuario_auditoria(Usuario usuario_auditoria) {
        this.usuario_auditoria = usuario_auditoria;
    }

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("id_formulario", this.id_formulario);
        obj.put("nombre_formulario", this.nombre_formulario);
        obj.put("url_formulario", this.url_formulario);
        obj.put("id_menu", this.getMenu().getId_menul());
        obj.put("nombre_menu", this.getMenu().getNombre_menu());
        obj.put("url_menu", this.getMenu().getUrl_menu());
        obj.put("id_usuario_auditoria", this.getUsuario_auditoria().getId_usuario());
        obj.put("nombre_usuario_auditoria", this.getUsuario_auditoria().getNombre_usuario());
        obj.put("usuario_usuario_auditoria", this.getUsuario_auditoria().getUsuario_usuario());
        return obj;
    }
}
