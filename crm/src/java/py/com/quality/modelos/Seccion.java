package py.com.quality.modelos;

import org.json.simple.JSONObject;

public class Seccion {
    private int id_seccion;
    private String nombre_seccion;
    private String codigo_seccion;

    public Seccion() {
    }

    public Seccion(int id_seccion, String nombre_seccion, String codigo_seccion) {
        this.id_seccion = id_seccion;
        this.nombre_seccion = nombre_seccion;
        this.codigo_seccion = codigo_seccion;
    }

    public int getId_seccion() {
        return id_seccion;
    }

    public void setId_seccion(int id_seccion) {
        this.id_seccion = id_seccion;
    }

    public String getNombre_seccion() {
        return nombre_seccion;
    }

    public void setNombre_seccion(String nombre_seccion) {
        this.nombre_seccion = nombre_seccion;
    }

    public String getCodigo_seccion() {
        return codigo_seccion;
    }

    public void setCodigo_seccion(String codigo_seccion) {
        this.codigo_seccion = codigo_seccion;
    }

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("id_seccion", this.id_seccion);
        obj.put("nombre_seccion", this.nombre_seccion);
        obj.put("codigo_seccion", this.codigo_seccion);
        return obj;
    }

}
