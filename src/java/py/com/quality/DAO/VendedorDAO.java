package py.com.quality.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import py.com.quality.modelos.EstadoVendedor;
import py.com.quality.modelos.Seccion;
import py.com.quality.modelos.Usuario;
import py.com.quality.modelos.Vendedor;
import py.com.quality.utiles.Conexion;

public class VendedorDAO {

    public boolean modificar_estado(int id_usuario, int id_estadovendedor) {

        boolean ok = false;

        if (Conexion.conectar()) {
            try {
                String sql = "update vendedores set "
                        + "id_estadovendedor=? where id_usuario=?";

                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {

                    ps.setInt(1, id_estadovendedor);
                    ps.setInt(2, id_usuario);

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

    public Vendedor buscarIdusuario(int id_usuario) {
        Vendedor vendedor = new Vendedor();
        if (Conexion.conectar()) {
            try {
                String sql = "select "
                        + "	v.id_vendedor, "
                        + "	v.nombre_vendedor, "
                        + "	v.id_seccion, "
                        + "	s.nombre_seccion, "
                        + "	v.fecha_inicioatencion, "
                        + "	v.fecha_finatencion, "
                        + "	v.id_estadovendedor, "
                        + "	e.descripcion_estadovendedor, "
                        + "	v.id_usuario, "
                        + "	u.nombre_usuario "
                        + "	"
                        + "from "
                        + "	vendedores v left join secciones s "
                        + "	on (v.id_seccion = s.id_seccion) left join usuarios u "
                        + "	on (v.id_usuario = u.id_usuario) left join estado_vendedores e "
                        + "	on (v.id_estadovendedor = e.id_estadovendedor) "
                        + "where "
                        + "	v.id_usuario = ?";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, id_usuario);

                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {

                        vendedor.setId_vendedor(rs.getInt("id_vendedor"));
                        vendedor.setNombre_vendedor(rs.getString("nombre_vendedor"));

                        Seccion seccion = new Seccion();
                        seccion.setId_seccion(rs.getInt("id_seccion"));
                        seccion.setNombre_seccion(rs.getString("nombre_seccion"));

                        vendedor.setSeccion(seccion);
                        vendedor.setFecha_inicioatencion(rs.getTimestamp("fecha_inicioatencion"));
                        vendedor.setFecha_finatencion(rs.getTimestamp("fecha_finatencion"));

                        EstadoVendedor estadovendedor = new EstadoVendedor();
                        estadovendedor.setId_estadovendedor(rs.getInt("id_estadovendedor"));
                        estadovendedor.setDescripcion_estadovendedor(rs.getString("descripcion_estadovendedor"));

                        vendedor.setEstadovendedor(estadovendedor);

                        Usuario usuario = new Usuario();
                        usuario.setId_usuario(rs.getInt("id_usuario"));
                        usuario.setNombre_usuario(rs.getString("nombre_usuario"));

                        vendedor.setUsuario(usuario);
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return vendedor;
    }

}
