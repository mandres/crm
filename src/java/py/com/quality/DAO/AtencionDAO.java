package py.com.quality.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import py.com.quality.modelos.Atencion;
import py.com.quality.modelos.Cliente;
import py.com.quality.modelos.EstadoAtencion;
import py.com.quality.modelos.Usuario;
import py.com.quality.modelos.Vendedor;
import py.com.quality.utiles.Conexion;

public class AtencionDAO {

    public ArrayList buscarAsignaciones() {
        ArrayList datos = new ArrayList();

        if (Conexion.conectar()) {
            try {
                String sql = "select "
                        + "	a.id_atencion, "
                        + "	u.id_usuario, "
                        + "	u.nombre_usuario, "
                        + "	v.id_vendedor, "
                        + "	v.nombre_vendedor, "
                        + "	a.fechahora_recepcion, "
                        + "	a.fechahora_inicioatencion, "
                        + "	a.fechahora_finatencion, "
                        + "	e.id_estadoatencion, "
                        + "	e.descripcion_estadoatencion, "
                        + "	c.id_cliente, "
                        + "	c.codigo_cliente,"
                        + "	c.nombre_cliente"
                        + "	"
                        + "from "
                        + "	atenciones a left join usuarios u "
                        + "	on (a.id_usuario = u.id_usuario) left join vendedores v "
                        + "	on (a.id_vendedor = v.id_vendedor) left join estado_atenciones e "
                        + "	on (a.id_estadoatencion = e.id_estadoatencion) left join clientes c "
                        + "	on (a.id_cliente = c.id_cliente)";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        Usuario usuario = new Usuario();
                        usuario.setId_usuario(rs.getInt("id_usuario"));
                        usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                        
                        Vendedor vendedor = new Vendedor();
                        vendedor.setId_vendedor(rs.getInt("id_vendedor"));
                        vendedor.setNombre_vendedor(rs.getString("nombre_vendedor"));
                        
                        EstadoAtencion estadoatencion = new EstadoAtencion();
                        estadoatencion.setId_estadoatencion(rs.getInt("id_estadoatencion"));
                        estadoatencion.setDescripcion_estadoatencion(rs.getString("descripcion_estadoatencion"));
                        
                        Cliente cliente = new Cliente();
                        cliente.setId_cliente(rs.getInt("id_cliente"));
                        cliente.setCodigo_cliente(rs.getInt("codigo_cliente"));
                        cliente.setNombre_cliente(rs.getString("nombre_cliente"));
                        
                        Atencion atencion = new Atencion();
                        atencion.setId_atencion(rs.getInt("id_atencion"));
                        atencion.setUsuario(usuario);
                        atencion.setVendedor(vendedor);
                        atencion.setFechahora_recepcion(rs.getTimestamp("fechahora_recepcion"));
                        atencion.setFechahora_inicioatencion(rs.getTimestamp("fechahora_inicioatencion"));
                        atencion.setFechahora_finatencion(rs.getTimestamp("fechahora_finatencion"));
                        atencion.setEstadoatencion(estadoatencion);
                        atencion.setCliente(cliente);
                        
                        datos.add(atencion);
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