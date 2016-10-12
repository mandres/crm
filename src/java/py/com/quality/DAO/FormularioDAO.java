package py.com.quality.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import py.com.quality.modelos.Formulario;
import py.com.quality.utiles.Conexion;
import py.com.quality.utiles.Util;

public class FormularioDAO {

    public Formulario buscarId(int id) {
        Formulario formularios = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from formularios where id_formulario=?";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        formularios = new Formulario();
                        formularios.setId_formulario(rs.getInt("id_formulario"));
                        formularios.setNombre_formulario(rs.getString("nombre_formulario"));
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return formularios;
    }

    public String buscarNombre(String nombre, int pagina) {
        int offset=(pagina-1)*Util.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from formularios where upper(nombre_formulario) like '%" +
                        nombre.toUpperCase() +
                        "%' "+
                        "order by id_formulario "+
                        "offset "+ offset + " limit "+ Util.REGISTROS_PAGINA;
                System.out.println("--> "+sql);
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                               + "<td>" + rs.getString("id_formulario") + "</td>"
                               + "<td>" + rs.getString("nombre_formulario") + "</td>"
                               + "</tr>";
                    }
                    if(tabla.equals("")){
                        tabla = "<tr><td  colspan=2>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public boolean agregar(Formulario formulario) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into formularios "
                    + "(nombre_formulario) "
                    + "values (?)";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setString(1, formulario.getNombre_formulario());
                ps.executeUpdate();
                ps.close();
                Conexion.getCon().commit();
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
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

    public boolean modificar(Formulario formulario) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update formularios set nombre_formulario=? "
                    + "where id_formulario=?";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setString(1, formulario.getNombre_formulario());
                ps.setInt(2, formulario.getId_formulario());
                ps.executeUpdate();
                ps.close();
                Conexion.getCon().commit();
                System.out.println("--> Grabado");
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
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

    public boolean eliminar(Formulario formulario) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from formularios where id_formulario=?";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setInt(1, formulario.getId_formulario());
                ps.executeUpdate();
                ps.close();
                Conexion.getCon().commit();
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
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
}
