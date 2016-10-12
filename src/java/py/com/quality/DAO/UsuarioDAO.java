package py.com.quality.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import py.com.quality.modelos.Empresa;
import py.com.quality.modelos.Usuario;
import py.com.quality.utiles.Conexion;
import py.com.quality.utiles.Util;

public class UsuarioDAO {

    public boolean validarAcceso(Usuario usuario, HttpServletRequest request) {
        boolean acceso = false;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from usuarios "
                        + "where usuario_usuario=? and clave_usuario=?";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setString(1, usuario.getUsuario_usuario());
                    ps.setString(2, usuario.getClave_usuario());

                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        HttpSession sesion = request.getSession(true);

                        usuario.setId_usuario(rs.getInt("id_usuario"));
                        usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                        usuario.setUsuario_usuario(rs.getString("usuario_usuario"));
                        usuario.setClave_usuario(rs.getString("clave_usuario"));

                        sesion.setAttribute("usuarioLogueado", usuario);

                        acceso = true;
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return acceso;
    }

    public Usuario buscarId(int id_usuario) {
        Usuario usuario = new Usuario();
        usuario.setId_usuario(0);
        usuario.setNombre_usuario("");
        usuario.setUsuario_usuario("");
        usuario.setClave_usuario("");

        if (Conexion.conectar()) {
            try {
                String sql = "select * from usuarios "
                        + "where id_usuario=?";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, id_usuario);

                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {

                        usuario.setId_usuario(rs.getInt("id_usuario"));
                        usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                        usuario.setUsuario_usuario(rs.getString("usuario_usuario"));
                        usuario.setClave_usuario("");

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return usuario;
    }

    public Map agregar(Usuario usuario) {
        Map valor = new HashMap();
        boolean ok = false;
        String mensaje = "";
        if (Conexion.conectar()) {
            try {
                String sql = "insert into usuarios("
                        + "nombre_usuario,"
                        + "usuario_usuario,"
                        + "clave_usuario"
                        + ") "
                        + "values(?,?,?)";

                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setString(1, usuario.getNombre_usuario());
                    ps.setString(2, usuario.getUsuario_usuario());
                    ps.setString(3, usuario.getClave_usuario());

                    int cr = ps.executeUpdate();
                    Conexion.getCon().commit();
                    if (cr > 0) {
                        ok = true;
                    }
                    ps.close();
                    valor.put("ok", ok);
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                mensaje = ex.getLocalizedMessage();
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

    public Map modificar(Usuario usuario) {
        Map valor = new HashMap();
        boolean ok = false;
        String mensaje = "";

        if (Conexion.conectar()) {
            try {
                String sql = "update usuarios set "
                        + "nombre_usuario=?,"
                        + "usuario_usuario=?,"
                        + "clave_usuario=? "
                        + "where id_usuario=?";

                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setString(1, usuario.getNombre_usuario());
                    ps.setString(2, usuario.getUsuario_usuario());
                    ps.setString(3, usuario.getClave_usuario());
                    ps.setInt(4, usuario.getId_usuario());

                    int cr = ps.executeUpdate();
                    Conexion.getCon().commit();
                    if (cr > 0) {
                        ok = true;
                    }
                    ps.close();
                    valor.put("ok", ok);
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                mensaje = ex.getLocalizedMessage();
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

    public boolean eliminar(Usuario usuario) {
        boolean ok = false;

        if (Conexion.conectar()) {
            try {
                String sql = "delete from usuarios "
                        + "where id_usuario=?";

                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, usuario.getId_usuario());

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

    public ArrayList buscarNombre() {
        ArrayList datos = new ArrayList();

        if (Conexion.conectar()) {
            try {
                String sql = "select * from usuarios order by id_usuario";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {                        
                        Usuario usuario = new Usuario();
                        usuario.setId_usuario(rs.getInt("id_usuario"));
                        usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                        usuario.setUsuario_usuario(rs.getString("usuario_usuario"));
                        usuario.setClave_usuario(rs.getString("clave_usuario"));                     
                        
                        datos.add(usuario);
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

        public boolean cambiarClave(Usuario usuario, Empresa empresaLogueada, String clave, String clavenueva, String claveconfirmacion) {
        boolean ok = false;

        if (Conexion.conectar()) {
            try {
                String sql = "update usuarios set "
                        + "clave_usuario=? "
                        + "where id_usuario=?";

                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setString(1, Util.md5(claveconfirmacion));
                    ps.setInt(2, usuario.getId_usuario());

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
