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
public class Grupo {
  private int id_grupo;
  private String nombre_grupo;
  private String codigo_grupo; 
  private Tipo codigo_tipo; 
  private Familia familia; 
  private Seccion seccion; 
  private Marca marca;
  
  public Grupo() {
  }
  
  public Grupo (int id_grupo, String nombre_grupo, String codigo_grupo, Tipo codigo_tipo, 
          Familia familia, Seccion seccion, Marca marca){
      this.id_grupo= id_grupo;
      this.nombre_grupo= nombre_grupo;
      this.codigo_grupo= codigo_grupo;
      this.codigo_tipo= codigo_tipo;
      this.familia= familia;
      this.seccion= seccion;
      this.marca= marca;
  }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public String getNombre_grupo() {
        return nombre_grupo;
    }

    public void setNombre_grupo(String nombre_grupo) {
        this.nombre_grupo = nombre_grupo;
    }

    public String getCodigo_grupo() {
        return codigo_grupo;
    }

    public void setCodigo_grupo(String codigo_grupo) {
        this.codigo_grupo = codigo_grupo;
    }

    public Tipo getTipo() {
        return codigo_tipo;
    }

    public void setTipo(Tipo tipo) {
        this.codigo_tipo = tipo;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
  
    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put ("id_grupo", this.id_grupo);
        obj.put ("nombre_grupo",this.nombre_grupo);
        obj.put ("codigo_grupo", this.codigo_grupo);
        obj.put ("codigo_tipo", this.getTipo().getCodigo_tipo());
        obj.put ("nombre_tipo", this.getTipo().getNombre_tipo());
        obj.put ("codigo_familia", this.getFamilia().getCodigo_familia());
        obj.put ("nombre_familia", this.getFamilia().getNombre_familia());
        obj.put ("codigo_seccion" , this.getSeccion().getCodigo_seccion());
        obj.put ("nombre_familia", this.getFamilia().getCodigo_familia());
        obj.put ("codigo_marca" , this.getMarca().getCodigo_marca());
        obj.put ("nombre_marca" , this.getMarca().getNombre_marca());
        return obj;
    }
          
  
}

  