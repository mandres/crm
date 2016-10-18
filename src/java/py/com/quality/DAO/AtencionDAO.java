package py.com.quality.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import py.com.quality.modelos.Atencion;
import py.com.quality.utiles.Conexion;

public class AtencionDAO {

    public Map agregar(Atencion atencion) {
        Map valor = new HashMap();
        boolean ok = false;
        int id_atencion = 0;

        if (Conexion.conectar()) {
            try {
                String sql = "insert into atenciones("
                        + "id_usuario, fechahora_recepcion, id_estadoatencion, id_cliente, id_seccion) "
                        + "values(" + atencion.getUsuario().getId_usuario() + ", "
                        + " now(), "
                        + "1, " + atencion.getCliente().getId_cliente() + ", "
                        + "" + atencion.getSeccion().getId_seccion() + ")";

                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

                try (ResultSet keyset = Conexion.getSt().getGeneratedKeys()) {
                    if (keyset.next()) {
                        id_atencion = keyset.getInt("id_atencion");
                        ok = true;
                    }

                    Conexion.getCon().commit();
                    valor.put("ok", ok);
                    valor.put("id_atencion", id_atencion);
                }

            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                String mensaje = ex.getLocalizedMessage();
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

    public Map listar(int filtroid_estadoatencion) {
        Map valor = new HashMap();
        String tabla = "";
        String where = "";
        int todos = 0;
        int pendiente = 0;
        int asignado = 0;
        int atendiendo = 0;
        int cerrado = 0;
        if (filtroid_estadoatencion != 0) {
            where = "where a.id_estadoatencion=" + filtroid_estadoatencion;
        }

        if (Conexion.conectar()) {
            try {
                String sql = "select "
                        + "	a.id_atencion, "
                        + "	c.id_cliente, "
                        + "	c.nombre_cliente, "
                        + "	to_char(a.fechahora_recepcion, 'dd/mm/yyyy HH24:mm:ss ') fechahora_recepcion, "
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
                        + " " + where
                        + " order by"
                        + "     1 desc";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        int id_atencion = rs.getInt("id_atencion");
                        int id_cliente = rs.getInt("id_cliente");
                        String nombre_cliente = rs.getString("nombre_cliente");
                        String fechahora_recepcion = rs.getString("fechahora_recepcion");
                        String nombre_seccion = rs.getString("nombre_seccion");
                        int id_estadoatencion = rs.getInt("id_estadoatencion");
                        String descripcion_estadoatencion = rs.getString("descripcion_estadoatencion");

                        String color = "";
                        if (id_estadoatencion == 1) {
                            color = "#ffcdd2";
                            ++pendiente;
                        } else if (id_estadoatencion == 2) {
                            color = "#80cbc4";
                            ++asignado;
                        } else if (id_estadoatencion == 3) {
                            color = "#81c784";
                            ++atendiendo;
                        } else if (id_estadoatencion == 4) {
                            color = "#00e676";
                            ++cerrado;
                        }
                        ++todos;

                        tabla += "<tr style='background-color:" + color + "'>"
                                + "     <td>" + id_atencion + "</td>"
                                + "     <td>" + id_cliente + "</td>"
                                + "     <td>" + nombre_cliente + "</td>"
                                + "     <td>" + fechahora_recepcion + "</td>"
                                + "     <td>" + nombre_seccion + "</td>"
                                + "     <td>" + descripcion_estadoatencion + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    }
                    valor.put("tabla", tabla);
                    valor.put("pendiente", pendiente);
                    valor.put("asignado", asignado);
                    valor.put("atendiendo", atendiendo);
                    valor.put("cerrado", cerrado);
                    valor.put("todos", todos);
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
