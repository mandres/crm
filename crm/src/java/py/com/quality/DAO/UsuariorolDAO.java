package py.com.quality.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import py.com.quality.modelos.Usuariorol;
import py.com.quality.utiles.Conexion;

public class UsuariorolDAO {

    public String buscarIdUsuario(int id_usuario) {
        String detalle = "";

        if (Conexion.conectar()) {
            try {
                String sql = "select * from usuariosroles ur "
                        + "left join roles r on ur.id_rol=r.id_rol "
                        + "where id_usuario=?";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, id_usuario);

                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        detalle += "<tr>";
                        detalle += "    <td>" + rs.getString("id_usuariorol") + "</td>";
                        detalle += "    <td>" + rs.getString("id_rol") + "</td>";
                        detalle += "    <td>" + rs.getString("nombre_rol") + "</td>";
                        detalle += "    <td class='centrado'>";
                        detalle += "        <button onclick='editar_linea($(this))' id='boton-editar-linea' type='button' class='btn btn-primary btn-sm'>";
                        detalle += "            <span class='glyphicon glyphicon-pencil' aria-hidden='true'></span>";
                        detalle += "        </button>";
                        detalle += "        <button onclick='eliminar_linea($(this))' id='boton-eliminar-linea' type='button' class='btn btn-primary btn-sm'>";
                        detalle += "            <span class='glyphicon glyphicon-trash' aria-hidden='true'></span>";
                        detalle += "        </button>";
                        detalle += "    </td>";
                        detalle += "</tr>";
                    }

                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detalle;
    }

    public String buscarRolNoAsignado(int id_usuario) {
        String detalle = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from roles r "
                        + "where not EXISTS(select * from usuariosroles ur "
                        + "where ur.id_rol = r.id_rol and ur.id_usuario=?)";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, id_usuario);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        detalle += "<tr onclick='seleccionar_rol($(this))'>";
                        detalle += "    <td>" + rs.getString("id_rol") + "</td>";
                        detalle += "    <td>" + rs.getString("nombre_rol") + "</td>";
                        detalle += "</tr>";
                    }
                    if (detalle.equals("")) {
                        detalle = "<tr><td  colspan=2>Este usuario ya tiene asignado todos los roles Habilitados ...</td></tr>";
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detalle;
    }

    public String buscarNombre() {
        String datos = "";

        if (Conexion.conectar()) {
            try {
                String sql = "select * from usuarioroles ur "
                        + "left join usuarios u on ur.id_usuario=u.id_usuario "
                        + "left join roles r on ur.id_rol=r.id_rol "
                        + "order by id_usuariorol";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        datos += "<tr  onclick = 'seleccionar_usuariorol($(this))'>";
                        datos += "   <td>" + rs.getString("id_usuariorol") + "</td>";
                        datos += "   <td>" + rs.getString("id_usuario") + "</td>";
                        datos += "   <td>" + rs.getString("nombre_usuario") + "</td>";
                        datos += "   <td>" + rs.getString("usuario_usuario") + "</td>";
                        datos += "   <td>" + rs.getString("id_rol") + "</td>";
                        datos += "   <td>" + rs.getString("nombre_rol") + "</td>";
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
            datos += "<tr><td colspan='6'>No existen datos ...</td></tr>";
        }
        return datos;
    }

    public boolean agregar(Usuariorol usuariorol) {
        boolean ok = false;

        if (Conexion.conectar()) {
            try {
                String sql = "insert into usuariosroles("
                        + "id_usuario,"
                        + "id_rol"
                        + ") "
                        + "values(?,?)";

                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, usuariorol.getUsuario().getId_usuario());
                    ps.setInt(2, usuariorol.getRol().getId_rol());

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

    public boolean modificar(Usuariorol usuariorol) {
        boolean ok = false;

        if (Conexion.conectar()) {
            try {
                String sql = "update usuariosroles set "
                        + "id_usuario=?, "
                        + "id_rol=? "
                        + "where id_usuariorol=?";

                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, usuariorol.getUsuario().getId_usuario());
                    ps.setInt(2, usuariorol.getRol().getId_rol());
                    ps.setInt(3, usuariorol.getId_usuariorol());

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

    public boolean eliminar(int id_usuariorol) {
        boolean ok = false;

        if (Conexion.conectar()) {
            try {
                String sql = "delete from usuariosroles "
                        + "where id_usuariorol=?";

                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, id_usuariorol);

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
}
