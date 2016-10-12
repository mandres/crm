package py.com.quality.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import py.com.quality.modelos.Tipocliente;
import py.com.quality.utiles.Conexion;

public class TipoclienteDAO {

    public Tipocliente buscarId(int id_tipocliente) {
        Tipocliente tipocliente = new Tipocliente();
        tipocliente.setId_tipocliente(0);
        tipocliente.setNombre_tipocliente("");

        if (Conexion.conectar()) {
            try {
                String sql = "select * from tiposclientes "
                        + "where id_tipocliente=?";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, id_tipocliente);

                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {

                        tipocliente.setId_tipocliente(rs.getInt("id_tipocliente"));
                        tipocliente.setNombre_tipocliente(rs.getString("nombre_tipocliente"));

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return tipocliente;
    }

    public boolean agregar(Tipocliente tipocliente) {
        boolean ok = false;

        if (Conexion.conectar()) {
            try {
                String sql = "insert into tiposclientes("
                        + "nombre_tipocliente,"
                        + "id_usuario_auditoria"
                        + ") "
                        + "values(?,?)";

                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setString(1, tipocliente.getNombre_tipocliente());
                    ps.setInt(2, tipocliente.getUsuario_auditoria().getId_usuario());

                    int cr = ps.executeUpdate();
                    Conexion.getCon().commit();
                    if (cr > 0) {
                        ok = true;
                    }
                    ps.close();
                }
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
        return ok;
    }

    public boolean modificar(Tipocliente tipocliente) {
        boolean ok = false;

        if (Conexion.conectar()) {
            try {
                String sql = "update tiposclientes set "
                        + "nombre_tipocliente=?,"
                        + "id_usuario_auditoria=? "
                        + "where id_tipocliente=?";

                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setString(1, tipocliente.getNombre_tipocliente());
                    ps.setInt(2, tipocliente.getUsuario_auditoria().getId_usuario());
                    ps.setInt(3, tipocliente.getId_tipocliente());

                    int cr = ps.executeUpdate();
                    Conexion.getCon().commit();
                    if (cr > 0) {
                        ok = true;
                    }
                    ps.close();
                }
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
        return ok;
    }

    public boolean eliminar(Tipocliente tipocliente) {
        boolean ok = false;

        if (Conexion.conectar()) {
            try {
                String sql = "delete from tiposclientes "
                        + "where id_tipocliente=?";

                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, tipocliente.getId_tipocliente());

                    int cr = ps.executeUpdate();
                    Conexion.getCon().commit();
                    if (cr > 0) {
                        ok = true;
                    }
                    ps.close();
                }
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
        return ok;
    }

    public String buscarNombre() {
        String datos = "";

        if (Conexion.conectar()) {
            try {
                String sql = "select * from tiposclientes "
                        + "order by id_tipocliente";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        datos += "<tr  onclick = 'seleccionar_tipocliente($(this))'>";
                        datos += "   <td>" + rs.getString("id_tipocliente") + "</td>";
                        datos += "   <td>" + rs.getString("nombre_tipocliente") + "</td>";
                        datos += "</tr>";
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        if (datos.equals("")) {
            datos += "<tr><td colspan='4'>No existen datos ...</td></tr>";
        }
        return datos;
    }

}
