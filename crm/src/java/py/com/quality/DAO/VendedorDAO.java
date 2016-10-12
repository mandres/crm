package py.com.quality.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import py.com.quality.modelos.EstadoAtencion;
import py.com.quality.modelos.Seccion;
import py.com.quality.modelos.Vendedor;
import py.com.quality.utiles.Conexion;

public class VendedorDAO {

    public ArrayList buscarVendedores() {
        ArrayList datos = new ArrayList();

        if (Conexion.conectar()) {
            try {
                String sql = "select "
                        + "	v.id_vendedor, "
                        + "	v.nombre_vendedor, "
                        + "	s.id_seccion, "
                        + "	s.nombre_seccion, "
                        + "	e.id_estadoatencion, "
                        + "	e.descripcion_estadoatencion, "
                        + "	v.fecha_inicioatencion, "
                        + "	v.fecha_finatencion "
                        + "	"
                        + "from vendedores v left join secciones s "
                        + "on (v.id_seccion = s.id_seccion) left join estado_atenciones e "
                        + "on (v.id_estadoatencion = e.id_estadoatencion)";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        Seccion seccion = new Seccion();
                        seccion.setId_seccion(rs.getInt("id_seccion"));
                        seccion.setNombre_seccion(rs.getString("nombre_seccion"));
                        seccion.setCodigo_seccion(rs.getString("codigo_seccion"));
                        
                        EstadoAtencion estadoatencion = new EstadoAtencion();
                        estadoatencion.setId_estadoatencion(rs.getInt("id_estadoatencion"));
                        estadoatencion.setDescripcion_estadoatencion(rs.getString("descripcion_estadoatencion"));

                        Vendedor vendedor = new Vendedor();
                        vendedor.setId_vendedor(rs.getInt("id_vendedor"));
                        vendedor.setNombre_vendedor(rs.getString("nombre_vendedor"));
                        vendedor.setSeccion(seccion);
                        vendedor.setEstadoatencion(estadoatencion);
                        vendedor.setFecha_inicioatencion(rs.getTimestamp("fecha_inicioatencion"));
                        vendedor.setFecha_finatencion(rs.getTimestamp("fecha_finatencion"));                      

                        datos.add(vendedor);
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
