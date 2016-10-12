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
public class Marca {
    private int id_marca;
    private String nombre_marca;
    private String codigo_marca;
    
    
    public Marca() {
    }
   
    public Marca (int id_marca, String nombre_marca, String codigo_marca) {
        this.id_marca= id_marca;
        this.nombre_marca= nombre_marca;
        this.codigo_marca= codigo_marca;
         }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public String getNombre_marca() {
        return nombre_marca;
    }

    public void setNombre_marca(String nombre_marca) {
        this.nombre_marca = nombre_marca;
    }

    public String getCodigo_marca() {
        return codigo_marca;
    }

    public void setCodigo_marca(String codigo_marca) {
        this.codigo_marca = codigo_marca;
    }

 
    
    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
         obj.put("id_marca", this.id_marca);
         obj.put("nombre_marca", this.id_marca);
         obj.put("codigo_marca", this.id_marca);
         return obj;
        }
    
}

