package py.com.quality.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import py.com.quality.modelos.Formulario;
import py.com.quality.modelos.Permiso;
import py.com.quality.modelos.Rol;
import py.com.quality.utiles.Conexion;

public class PermisoDAO {

    public Permiso buscarId(int id) {
        Permiso permiso = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from permisos p "
                        + "left join roles r on p.id_rol=r.id_rol "
                        + "left join formularios f on p.id_formulario=f.id_formulario "
                        + "where id_permiso=?";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        permiso = new Permiso();
                        permiso.setId_permiso(rs.getInt("id_permiso"));
                        Rol rol = new Rol();
                        rol.setId_rol(rs.getInt("id_rol"));
                        rol.setNombre_rol(rs.getString("nombre_rol"));
                        permiso.setRol(rol);
                        Formulario formulario = new Formulario();
                        formulario.setId_formulario(rs.getInt("id_formulario"));
                        formulario.setNombre_formulario(rs.getString("nombre_formulario"));
                        permiso.setFormulario(formulario);
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return permiso;
    }

    public String buscarNombre(int id_rol) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from permisos p "
                        + "left join roles r on p.id_rol=r.id_rol "
                        + "left join formularios f on p.id_formulario=f.id_formulario "
                        + "where p.id_rol='" + id_rol + "'"
                        + " order by id_permiso";
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        String agregar = (rs.getBoolean("agregar_permiso")) ? "checked" : "";
                        String modificar = (rs.getBoolean("modificar_permiso")) ? "checked" : "";
                        String eliminar = (rs.getBoolean("eliminar_permiso")) ? "checked" : "";
                        String listar = (rs.getBoolean("listar_permiso")) ? "checked" : "";
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_permiso") + "</td>"
                                + "<td>" + rs.getString("nombre_formulario") + "</td>"
                                + "<td><input type='checkbox' value='' " + agregar + "></td>"
                                + "<td><input type='checkbox' value='' " + modificar + "></td>"
                                + "<td><input type='checkbox' value='' " + eliminar + "></td>"
                                + "<td><input type='checkbox' value='' " + listar + "></td>"
                                + "<td>"
                                + " <button data-toggle='modal' data-target='#confirmarEliminar' onclick='recuperar_idpermiso($(this))' type='button' class='btn btn-default'><span class='icon-eliminar'></span></button> "
                                + " <button onclick='modificarPermiso($(this))' type='button' class='btn btn-default'><span class='icon-modificar'></span></button> "
                                + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr style='background-color:red;color:white;'><td  colspan=9>No existen registros ...</td></tr>";
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

    public boolean agregar(Permiso permiso) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into permisos "
                    + "(id_rol,id_formulario,agregar_permiso,modificar_permiso,eliminar_permiso,"
                    + "listar_permiso,id_usuario_auditoria) "
                    + "values (?,?,?,?,?,?,?)";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setInt(1, permiso.getRol().getId_rol());
                ps.setInt(2, permiso.getFormulario().getId_formulario());
                ps.setBoolean(3, permiso.isAgregar_permiso());
                ps.setBoolean(4, permiso.isModificar_permiso());
                ps.setBoolean(5, permiso.isEliminar_permiso());
                ps.setBoolean(6, permiso.isListar_permiso());
                ps.setInt(7, permiso.getUsuario_auditoria().getId_usuario());

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

    public boolean modificar(Permiso permiso) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update permisos set "
                    + "agregar_permiso = ?, "
                    + "modificar_permiso =?, "
                    + "eliminar_permiso =?, "
                    + "listar_permiso = ?, "
                    + "id_usuario_auditoria = ? "
                    + "where id_permiso = ?";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setBoolean(1, permiso.isAgregar_permiso());
                ps.setBoolean(2, permiso.isModificar_permiso());
                ps.setBoolean(3, permiso.isEliminar_permiso());
                ps.setBoolean(4, permiso.isListar_permiso());
                ps.setInt(5, permiso.getUsuario_auditoria().getId_usuario());
                ps.setInt(6, permiso.getId_permiso());
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

    public boolean eliminar(Permiso permiso) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from permisos where id_permiso=?";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setInt(1, permiso.getId_permiso());
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

    public String generarMenu(int id_usuarioLogueado) {
        String menu = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select "
                        + " s.id_sistema, "
                        + " s.nombre_sistema, "
                        + " s.url_sistema, "
                        + " m.id_menu, "
                        + " m.nombre_menu, "
                        + " m.url_menu, "
                        + " f.id_formulario, "
                        + " f.nombre_formulario, "
                        + " f.url_formulario "
                        + " from "
                        + " permisos p left join roles r on(p.id_rol = r.id_rol) "
                        + " inner join usuariosroles ur on (r.id_rol = ur.id_rol and id_usuario='" + id_usuarioLogueado + "') "
                        + " left join formularios f on(p.id_formulario = f.id_formulario) "
                        + " left join menus m on(f.id_menu = m.id_menu) "
                        + " left join sistemas s on(m.id_sistema = s.id_sistema) "
                        + " order by s.id_sistema, m.id_menu, f.id_formulario";
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                int gid_sistema = 0;
                int gid_menu = 0;
                int gid_formulario = 0;
                String cerrarMenu = "       </ul> "
                        + "   </li> "
                        + "</ul> ";
                String cerrarSistema = "   </li> "
                        + "</ul> ";
                while (rs.next()) {
                    int id_sistema = rs.getInt("id_sistema");
                    int id_menu = rs.getInt("id_menu");
                    int id_formulario = rs.getInt("id_formulario");
                    if (gid_sistema != id_sistema) {
                        if (gid_sistema != 0) {
                            gid_menu = 0;
                            menu += cerrarMenu + "<!--cerro menu dentro de sistema-->\n";
                            menu += cerrarSistema + "<!--cerro sistema dentro de sistema-->\n";
                        }
                        menu += "<ul class='menuv__sistema'> \n";
                        menu += "   <li> \n";
                        menu += "       " + rs.getString("url_sistema") + "\n";
                        gid_sistema = id_sistema;
                    }

                    if (gid_menu != id_menu) {
                        if (gid_menu != 0) {
                            menu += cerrarMenu + "<!--cerro menu dentro de menu--> \n";
                        }
                        menu += "   <ul class='menuv__submenu'> \n";
                        menu += "       <li> \n";
                        menu += "       " + rs.getString("url_menu") + "\n";
                        menu += "           <ul class='menuv__formulario'> \n";
                        gid_menu = id_menu;
                    }

                    if (gid_formulario != id_formulario) {
                        menu += "           " + rs.getString("url_formulario") + "\n";
                        gid_formulario = id_formulario;
                    }

                }
                if (!menu.equals("")) {
                    menu += cerrarMenu + "<!--cerro menu dentro al final de todo-->\n";
                    menu += cerrarSistema + "<!--cerro sistema al final de todo-->\n";
                }
                rs.close();
                Conexion.cerrar();
            } catch (SQLException ex) {
                Logger.getLogger(PermisoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return menu;
    }
}
