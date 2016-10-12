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
public class Temporada {
    private int id_temporada;
    private String nombre_temporada;
    private String codigo_temporada;
    
    
    public Temporada() {
    }
   
    public Temporada (int id_temporada, String nombre_temporada, String codigo_temporada) {
        this.id_temporada= id_temporada;
        this.nombre_temporada= nombre_temporada;
        this.codigo_temporada= codigo_temporada;
         }

    public int getId_temporada() {
        return id_temporada;
    }

    public void setId_temporada(int id_temporada) {
        this.id_temporada = id_temporada;
    }

    public String getNombre_temporada() {
        return nombre_temporada;
    }

    public void setNombre_temporada(String nombre_temporada) {
        this.nombre_temporada = nombre_temporada;
    }

    public String getCodigo_temporada() {
        return codigo_temporada;
    }

    public void setCodigo_temporada(String codigo_temporada) {
        this.codigo_temporada = codigo_temporada;
    }

 
    
    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
         obj.put("id_temporada", this.id_temporada);
         obj.put("nombre_temporada", this.id_temporada);
         obj.put("codigo_temporada", this.id_temporada);
         return obj;
        }
    
}
