/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.quality.modelos;

import org.json.simple.JSONObject;

/**
 *
 * @author dgranada
 */
public class Talle {
    private int id_talle;
    private String nombre_talle;
    private String codigo_talle;
    
    
    public Talle() {
    }
   
    public Talle (int id_talle, String nombre_talle, String codigo_talle) {
        this.id_talle= id_talle;
        this.nombre_talle= nombre_talle;
        this.codigo_talle= codigo_talle;
         }

    public int getId_talle() {
        return id_talle;
    }

    public void setId_talle(int id_talle) {
        this.id_talle = id_talle;
    }

    public String getNombre_talle() {
        return nombre_talle;
    }

    public void setNombre_talle(String nombre_talle) {
        this.nombre_talle = nombre_talle;
    }

    public String getCodigo_talle() {
        return codigo_talle;
    }

    public void setCodigo_talle(String codigo_talle) {
        this.codigo_talle = codigo_talle;
    }

 
    
    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
         obj.put("id_talle", this.id_talle);
         obj.put("nombre_talle", this.id_talle);
         obj.put("codigo_talle", this.id_talle);
         return obj;
        }
    
}
