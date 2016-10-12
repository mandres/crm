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
public class Dependencia {
    private int id_dependencia;
    private String nombre_dependencia;
    private String codigo_dependencia;
    
    
    public Dependencia() {
    }
   
    public Dependencia (int id_dependencia, String nombre_dependencia, String codigo_dependencia) {
        this.id_dependencia= id_dependencia;
        this.nombre_dependencia= nombre_dependencia;
        this.codigo_dependencia= codigo_dependencia;
         }

    public int getId_dependencia() {
        return id_dependencia;
    }

    public void setId_dependencia(int id_dependencia) {
        this.id_dependencia = id_dependencia;
    }

    public String getNombre_dependencia() {
        return nombre_dependencia;
    }

    public void setNombre_dependencia(String nombre_dependencia) {
        this.nombre_dependencia = nombre_dependencia;
    }

    public String getCodigo_dependencia() {
        return codigo_dependencia;
    }

    public void setCodigo_dependencia(String codigo_dependencia) {
        this.codigo_dependencia = codigo_dependencia;
    }

 
    
    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
         obj.put("id_dependencia", this.id_dependencia);
         obj.put("nombre_dependencia", this.id_dependencia);
         obj.put("codigo_dependencia", this.id_dependencia);
         return obj;
        }
    
}
