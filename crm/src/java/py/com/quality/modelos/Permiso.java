package py.com.quality.modelos;

import org.json.simple.JSONObject;

public class Permiso {
    private int id_permiso;
    private Rol rol;
    private Formulario formulario;
    private boolean agregar_permiso;
    private boolean modificar_permiso;
    private boolean eliminar_permiso;
    private boolean listar_permiso;
    private Usuario usuario_auditoria;

    public Permiso() {
    }

    public Permiso(int id_permiso, Rol rol, Formulario formulario, boolean agregar_permiso, boolean modificar_permiso, boolean eliminar_permiso, boolean listar_permiso, Usuario usuario_auditoria) {
        this.id_permiso = id_permiso;
        this.rol = rol;
        this.formulario = formulario;
        this.agregar_permiso = agregar_permiso;
        this.modificar_permiso = modificar_permiso;
        this.eliminar_permiso = eliminar_permiso;
        this.listar_permiso = listar_permiso;
        this.usuario_auditoria = usuario_auditoria;
    }

    public int getId_permiso() {
        return id_permiso;
    }

    public void setId_permiso(int id_permiso) {
        this.id_permiso = id_permiso;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Formulario getFormulario() {
        return formulario;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }

    public boolean isAgregar_permiso() {
        return agregar_permiso;
    }

    public void setAgregar_permiso(boolean agregar_permiso) {
        this.agregar_permiso = agregar_permiso;
    }

    public boolean isModificar_permiso() {
        return modificar_permiso;
    }

    public void setModificar_permiso(boolean modificar_permiso) {
        this.modificar_permiso = modificar_permiso;
    }

    public boolean isEliminar_permiso() {
        return eliminar_permiso;
    }

    public void setEliminar_permiso(boolean eliminar_permiso) {
        this.eliminar_permiso = eliminar_permiso;
    }

    public boolean isListar_permiso() {
        return listar_permiso;
    }

    public void setListar_permiso(boolean listar_permiso) {
        this.listar_permiso = listar_permiso;
    }

    public Usuario getUsuario_auditoria() {
        return usuario_auditoria;
    }

    public void setUsuario_auditoria(Usuario usuario_auditoria) {
        this.usuario_auditoria = usuario_auditoria;
    }

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("id_permiso", this.id_permiso);
        obj.put("id_rol", this.getRol().getId_rol());
        obj.put("nombre_rol", this.getRol().getNombre_rol());
        obj.put("id_formulario", this.getFormulario().getId_formulario());
        obj.put("nombre_formulario", this.getFormulario().getNombre_formulario());
        obj.put("agregar_permiso", this.agregar_permiso);
        obj.put("modificar_permiso", this.modificar_permiso);
        obj.put("eliminar_permiso", this.eliminar_permiso);
        obj.put("listar_permiso", this.listar_permiso);
        obj.put("id_usuario_auditoria", this.getUsuario_auditoria().getId_usuario());
        obj.put("nombre_usuario_auditoria", this.getUsuario_auditoria().getNombre_usuario());
        obj.put("usuario_usuario_auditoria", this.getUsuario_auditoria().getUsuario_usuario());
        return obj;
    }
}
