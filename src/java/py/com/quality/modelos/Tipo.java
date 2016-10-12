
package py.com.quality.modelos;

import org.json.simple.JSONObject;

/**
 *
 * @author dgranada
 */
public class Tipo {
    private int id_tipo;
    private String nombre_tipo;
    private String codigo_tipo;
    
    
    public Tipo() {
    }
   
    public Tipo (int id_tipo, String nombre_tipo, String codigo_tipo) {
        this.id_tipo= id_tipo;
        this.nombre_tipo= nombre_tipo;
        this.codigo_tipo= codigo_tipo;
         }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getNombre_tipo() {
        return nombre_tipo;
    }

    public void setNombre_tipo(String nombre_tipo) {
        this.nombre_tipo = nombre_tipo;
    }

    public String getCodigo_tipo() {
        return codigo_tipo;
    }

    public void setCodigo_tipo(String codigo_tipo) {
        this.codigo_tipo = codigo_tipo;
    }
    
    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
         obj.put("id_tipo", this.id_tipo);
         obj.put("nombre_tipo", this.id_tipo);
         obj.put("codigo_tipo", this.id_tipo);
         return obj;
        }
    
}