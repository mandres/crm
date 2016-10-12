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
public class Producto {
    
    private int id_producto;
    private String nombre_producto;
    private String codigo_producto;
    private Grupo id_grupo;
    private Temporada id_temporada;
    private Talle id_talle;
    private int id_categoria;
    private Dependencia id_dependencia;
    private int id_sexo;
    private int id_edad;
    
    public Producto() {        
        
    }
    
    public Producto(int id_producto, String codigo_producto, Grupo id_grupo, Temporada id_temporada, 
    Talle id_talle, int id_categoria,Dependencia id_dependencia,int id_sexo,int id_edad) {
        this.id_grupo= id_grupo; 
        this.nombre_producto= nombre_producto;
        this.codigo_producto= codigo_producto;
        this.id_grupo= id_grupo;
        this.id_temporada= id_temporada;
        this.id_talle= id_talle;
        this.id_categoria= id_categoria;
        this.id_dependencia= id_dependencia;
        this.id_sexo= id_sexo;
        this.id_edad= id_edad;      
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(String codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public Grupo getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(Grupo id_grupo) {
        this.id_grupo = id_grupo;
    }

    public Temporada getId_temporada() {
        return id_temporada;
    }

    public void setId_temporada(Temporada id_temporada) {
        this.id_temporada = id_temporada;
    }

    public Talle getId_talle() {
        return id_talle;
    }

    public void setId_talle(Talle id_talle) {
        this.id_talle = id_talle;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public Dependencia getId_dependencia() {
        return id_dependencia;
    }

    public void setId_dependencia(Dependencia id_dependencia) {
        this.id_dependencia = id_dependencia;
    }

    public int getId_sexo() {
        return id_sexo;
    }

    public void setId_sexo(int id_sexo) {
        this.id_sexo = id_sexo;
    }

    public int getId_edad() {
        return id_edad;
    }

    public void setId_edad(int id_edad) {
        this.id_edad = id_edad;
    }
         
    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("id_producto", this.id_producto);
        obj.put("nombre_producto", this.nombre_producto);
        obj.put("codigo_producto", this.codigo_producto);
        obj.put("id_grupo", this.id_grupo);
        obj.put("id_temporada", this.id_temporada);
        obj.put("id_talle", this.id_talle);
        obj.put("id_categoria", this.id_categoria);
        obj.put("id_dependencia", this.id_dependencia);
        obj.put("id_sexo", this.id_sexo);
        obj.put("id_edad", this.id_edad);
                
        return obj;
    }
    }