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
public class Familia {
    private int id_familia;
    private String nombre_familia;
    private String codigo_familia;
    
    
    public Familia() {
    }
   
    public Familia (int id_familia, String nombre_familia, String codigo_familia) {
        this.id_familia= id_familia;
        this.nombre_familia= nombre_familia;
        this.codigo_familia= codigo_familia;
         }

    public int getId_familia() {
        return id_familia;
    }

    public void setId_familia(int id_familia) {
        this.id_familia = id_familia;
    }

    public String getNombre_familia() {
        return nombre_familia;
    }

    public void setNombre_familia(String nombre_familia) {
        this.nombre_familia = nombre_familia;
    }

    public String getCodigo_familia() {
        return codigo_familia;
    }

    public void setCodigo_familia(String codigo_familia) {
        this.codigo_familia = codigo_familia;
    }

 
    
    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
         obj.put("id_familia", this.id_familia);
         obj.put("nombre_familia", this.id_familia);
         obj.put("codigo_familia", this.id_familia);
         return obj;
        }
    
}

