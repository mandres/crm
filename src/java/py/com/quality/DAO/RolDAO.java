package py.com.quality.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import py.com.quality.modelos.Rol;
import py.com.quality.utiles.Conexion;
import py.com.quality.utiles.Util;

public class RolDAO {

    public Rol buscarId(int id) {
        Rol rol = new Rol();
        rol.setId_rol(0);
        rol.setNombre_rol("");
        if (Conexion.conectar()) {
            try {
                String sql = "select * from roles where id_rol=?";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        rol.setId_rol(rs.getInt("id_rol"));
                        rol.setNombre_rol(rs.getString("nombre_rol"));
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return rol;
    }

    public String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Util.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from roles where upper(nombre_rol) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_rol "
                        + "offset " + offset + " limit " + Util.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr onclick='seleccionarRol($(this))'>"
                                + "<td>" + rs.getString("id_rol") + "</td>"
                                + "<td>" + rs.getString("nombre_rol") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
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

    public boolean agregar(Rol rol) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into roles "
                    + "(nombre_rol) "
                    + "values (?)";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setString(1, rol.getNombre_rol());
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

    public boolean modificar(Rol rol) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update roles set nombre_rol=? "
                    + "where id_rol=?";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setString(1, rol.getNombre_rol());
                ps.setInt(2, rol.getId_rol());
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

    public boolean eliminar(Rol rol) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from roles where id_rol=?";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setInt(1, rol.getId_rol());
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
