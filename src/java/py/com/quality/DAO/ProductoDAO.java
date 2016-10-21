package py.com.quality.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import py.com.quality.modelos.Dependencia;
import py.com.quality.modelos.Grupo;
import py.com.quality.modelos.Producto;
import py.com.quality.modelos.Talle;
import py.com.quality.modelos.Temporada;
import py.com.quality.utiles.Conexion;
import py.com.quality.utiles.Util;

public class ProductoDAO {

    public Map agregar(Producto producto) {
        Map valor = new HashMap();
        boolean ok = false;
        int id_producto = 0;

        if (Conexion.conectar()) {
            try {
                String sql ="insert into productos("
                        + "nombre_producto, codigo_producto, id_grupo, id_marca, id_temporada, "
                        + "id_talle, id_categoria, id_dependencia,id_sexo, id_edad)"
                        + "values ('" + producto.getNombre_producto() + "',"
                        + "'" + producto.getCodigo_producto() + "',"
                        + "'" + producto.getId_grupo() + "',"
                        +"'" + producto.getId_temporada() + "',"
                        + "'" + producto.getId_talle() + "',"
                        + "'" + producto.getId_categoria() + "',"
                        + "'" + producto.getId_dependencia() + "',"
                        +"'" + producto.getId_sexo() + "',"
                        + "'" + producto.getId_edad() + "')";


                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

                try (ResultSet keyset = Conexion.getSt().getGeneratedKeys()) {
                    if (keyset.next()) {
                        id_producto = keyset.getInt("id_producto");
                        ok = true;
                    }
                    
                    Conexion.getCon().commit();
                    valor.put("ok", ok);
                    valor.put("id_producto", id_producto);
                }

            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                String mensaje = ex.getLocalizedMessage();
                valor.put("mensaje", mensaje);
                try {
                    Conexion.getCon().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public Map modificar(Producto producto) {
        Map valor = new HashMap();
        boolean ok = false;

        if (Conexion.conectar()) {
            try {
                String sql = "update productos set "        
                + "nombre_producto=?, codigo_producto=?, id_grupo=?, id_temporada=?, "
                        + "id_talle=?, id_=?, id_categoria=?, id_dependencia=?, id_sexo=?, "
                        + "id_edad";

                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setString(1, producto.getNombre_producto());
                    ps.setString(2, producto.getCodigo_producto());
                    ps.setInt(3,producto.getId_grupo().getId_grupo());
                    ps.setInt(4, producto.getId_temporada().getId_temporada());
                    ps.setInt(4, producto.getId_talle().getId_talle());
                    ps.setInt(4, producto.getId_categoria());
                    ps.setInt(4, producto.getId_dependencia().getId_dependencia());
                    ps.setInt(4, producto.getId_sexo());
                    ps.setInt(4, producto.getId_edad());                   

                    int cr = ps.executeUpdate();
                    Conexion.getCon().commit();
                    if (cr > 0) {
                        ok = true;
                    }
                    valor.put("ok", ok);
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                String mensaje = ex.getLocalizedMessage();
                valor.put("mensaje", mensaje);
                try {
                    Conexion.getCon().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public Producto buscarId(int id_producto) {
       Producto producto = new Producto();
       producto.setId_producto(0);
       producto.setCodigo_producto("");
       producto.setId_grupo(null);
       producto.setId_temporada(null);
       producto.setId_talle(null);
       producto.setId_categoria(0);
       producto.setId_talle(null);
       producto.setId_sexo(0);
       producto.setId_edad(0);

        if (Conexion.conectar()) {
            try {
                String sql = "select "
                        + "	nombre_producto, "
                        + "	codigo_prodcuto, "
                        + "	gr.id_grupo, "
                        + "	gr.nombre_grupo, "
                        + "	tm.id_temporada, "
                        + "	tm.nombre_temporada, "
                        + "	ta.id_talle, "
                        + "	ta.nombre_talle, "
                        + "	id_categoria "
                        + "	de.id_dependencia "
                        + "     de.nombre_dependencia,  "
                        + "     id_sexo, "
                        + "     id_edad,    "
                        + "from productos pr "
                        + "left join grupo gr on pr.id_grupo= gr.id_grupo "
                        + "left join temporadas tm on pr.id_temporada= tm.id_temporada "
                        + "left join talle ta on pr.id_talle= ta.id_talle "
                        + "left join dependencias de on pr.id_dependencia= de.id_dependencia"
                        + "where id_producto=?";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, id_producto);

                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        producto.setId_producto(rs.getInt("id_producto"));
                        producto.setNombre_producto(rs.getString("nombre_producto"));
                        producto.getId_grupo().setId_grupo(rs.getInt("id_grupo"));
                        producto.getId_temporada().setId_temporada(rs.getInt("id_temporada"));
                        producto.getId_talle().setId_talle(rs.getInt("id_talle"));
                        producto.setId_categoria(rs.getInt("id_dependencia"));                         
                        producto.getId_dependencia().setId_dependencia(rs.getInt("id_dependencia"));
                        producto.setId_sexo(rs.getInt("id_sexo")); 
                        producto.setId_edad(rs.getInt("id_edad")); 
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return producto;
    }

    public ArrayList buscarNombre(String bnombre, int pagina) {
        ArrayList datos = new ArrayList();
        int offset = (pagina - 1) * Util.REGISTROS_PAGINA;

        if (Conexion.conectar()) {
            try {
                String sql = "select * from productos pr "
                        + "left join grupo gr on pr.id_grupo= gr.id_grupo "
                        + "left join temporadas tm on pr.id_temporada= tm.id_temporada "
                        + "left join talle ta on pr.id_talle= ta.id_talle "
                        + "left join dependencias de on pr.id_dependencia= de.id_dependencia"
                        + "where upper(pr.nombre_producto) || ' ' || COALESCE(pr.codigo_producto,'') like '%" + bnombre.toUpperCase() + "%'"
                        + " order by id_producto "
                        + "offset " + offset + " limit " + Util.REGISTROS_PAGINA;
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        
                        Grupo grupo= new Grupo();
                        grupo.setId_grupo(rs.getInt("id_grupo"));
                        grupo.setNombre_grupo("nombre_grupo");
                        
                        Temporada temporada = new Temporada();
                        temporada.setId_temporada(rs.getInt("id_temporada"));
                        temporada.setNombre_temporada(rs.getString("nombre_temporada"));
                        
                        Talle talle = new Talle();
                        talle.setId_talle(rs.getInt("id_talle"));
                        talle.setNombre_talle(rs.getString("nombre_talle"));
                        
                        Dependencia dependencia= new Dependencia();
                        dependencia.setId_dependencia(rs.getInt("id_dependencia"));
                        dependencia.setNombre_dependencia(rs.getString("nombre_dependencia"));
                        
                        Producto producto = new Producto();
                        producto.setId_producto(rs.getInt("id_producto"));
                        producto.setNombre_producto(rs.getString("nombre_producto"));
                        producto.setId_producto(rs.getInt("codigo_producto"));
                        producto.setId_grupo(grupo);
                        producto.setId_temporada(temporada);
                        producto.setId_talle(talle);
                        producto.setId_categoria(rs.getInt("id_categoria"));
                        producto.setId_dependencia(dependencia);
                        producto.setId_sexo(rs.getInt("id_sexo"));
                        producto.setId_edad(rs.getInt("id_edad"));                  
                        datos.add(producto);
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return datos;
    }
}
