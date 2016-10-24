package py.com.quality.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import py.com.quality.modelos.Usuario;
import py.com.quality.utiles.Conexion;

public class AtencionventaDAO {

    public Map listar(String fecha_desde, String fecha_hasta, Usuario usuariologueado) {

        Map valor = new HashMap();
        String tabla = "";

        if (Conexion.conectar()) {
            try {
                String sql = "select "
                        + "	a.id_atencion, "
                        + "	c.id_cliente, "
                        + "	c.nombre_cliente, "
                        + "	s.nombre_seccion, "
                        + "     e.id_estadoatencion, "
                        + "	e.descripcion_estadoatencion "
                        + "	"
                        + "from "
                        + "	atenciones a left join usuarios u "
                        + "	on (a.id_usuario = u.id_usuario) left join vendedores v "
                        + "	on (a.id_vendedor = v.id_vendedor) left join estado_atenciones e "
                        + "	on (a.id_estadoatencion = e.id_estadoatencion) left join clientes c "
                        + "	on (a.id_cliente = c.id_cliente) left join secciones s "
                        + "	on (a.id_seccion = s.id_seccion) "
                        + "where "
                        + "     to_char(a.fechahora_recepcion, 'dd/mm/yyyy') between '" + fecha_desde + "' and '" + fecha_hasta + "' "
                        + "     and v.id_usuario=" + usuariologueado.getId_usuario() + " "
                        + "     and a.id_estadoatencion in('3','4') "
                        + "order by"
                        + "     1 desc";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        int id_atencion = rs.getInt("id_atencion");
                        int id_cliente = rs.getInt("id_cliente");
                        String nombre_cliente = rs.getString("nombre_cliente");
                        int id_estadoatencion = rs.getInt("id_estadoatencion");
                        String descripcion_estadoatencion = rs.getString("descripcion_estadoatencion");

                        String color = "";
                        if (id_estadoatencion == 1) {
                            color = "#ffcdd2";
                        } else if (id_estadoatencion == 2) {
                            color = "#80cbc4";
                        } else if (id_estadoatencion == 3) {
                            color = "#81c784";
                        }

                        tabla += "<tr style='background-color:" + color + "'>"
                                + "     <td>" + id_atencion + "</td>"
                                + "     <td>" + id_cliente + "</td>"
                                + "     <td>" + nombre_cliente + "</td>"
                                + "     <td>" + descripcion_estadoatencion + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=4>No existen registros ...</td></tr>";
                    }
                    valor.put("tabla", tabla);
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public Map asignadoPendiente(int id_usuario) {

        Map valor = new HashMap();
        String tabla = "";
        int cantidad = 0;

        if (Conexion.conectar()) {
            try {
                String sql = "select "
                        + "	a.id_atencion, "
                        + "	a.id_cliente, "
                        + "	c.nombre_cliente, "
                        + "	to_char(a.fechahora_recepcion,'dd-mm-yyyy HH24:mm:ss') as fechahora_recepcion "
                        + "	"
                        + "from "
                        + "	atenciones a left join clientes c "
                        + "	on (a.id_cliente = c.id_cliente) left join vendedores v "
                        + "	on (a.id_vendedor = v.id_vendedor) left join usuarios u "
                        + "	on (v.id_usuario = u.id_usuario) "
                        + "where "
                        + "	a.id_estadoatencion = '2' and "
                        + "	v.id_usuario = ?";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, id_usuario);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        int id_atencion = rs.getInt("id_atencion");
                        int id_cliente = rs.getInt("id_cliente");
                        String nombre_cliente = rs.getString("nombre_cliente");
                        String fechahora_recepcion = rs.getString("fechahora_recepcion");
                        ++cantidad;
                        tabla += "<tr>"
                                + "     <td>" + id_atencion + "</td>"
                                + "     <td>" + id_cliente + "</td>"
                                + "     <td>" + nombre_cliente + "</td>"
                                + "     <td>" + fechahora_recepcion + "</td>"
                                + "     <td></td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=4>No existen registros ...</td></tr>";
                    }
                    valor.put("tabla", tabla);
                    valor.put("cantidad", cantidad);
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }
}
