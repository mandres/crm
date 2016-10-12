package py.com.quality.modelos;

import org.json.simple.JSONObject;

public class Ciudad {
    private int id_ciudad;
    private String nombre_ciudad;
    private String codigo_ciudad;

    public Ciudad() {
    }

    public Ciudad(int id_ciudad, String nombre_ciudad, String codigo_ciudad) {
        this.id_ciudad = id_ciudad;
        this.nombre_ciudad = nombre_ciudad;
        this.codigo_ciudad = codigo_ciudad;
    }

    public int getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(int id_ciudad) {
        this.id_ciudad = id_ciudad;
    }

    public String getNombre_ciudad() {
        return nombre_ciudad;
    }

    public void setNombre_ciudad(String nombre_ciudad) {
        this.nombre_ciudad = nombre_ciudad;
    }

    public String getCodigo_ciudad() {
        return codigo_ciudad;
    }

    public void setCodigo_ciudad(String codigo_ciudad) {
        this.codigo_ciudad = codigo_ciudad;
    }

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("id_ciudad", this.id_ciudad);
        obj.put("nombre_ciudad", this.nombre_ciudad);
        obj.put("codigo_ciudad", this.codigo_ciudad);
        return obj;
    }

}
