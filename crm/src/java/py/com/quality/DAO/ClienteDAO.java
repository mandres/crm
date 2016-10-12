package py.com.quality.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import py.com.quality.modelos.Ciudad;
import py.com.quality.modelos.Cliente;
import py.com.quality.modelos.Tipocliente;
import py.com.quality.modelos.Usuario;
import py.com.quality.utiles.Conexion;
import py.com.quality.utiles.Util;

public class ClienteDAO {

    public Map agregar(Cliente cliente) {
        Map valor = new HashMap();
        boolean ok = false;
        int id_cliente = 0;

        if (Conexion.conectar()) {
            try {
                String sql = "insert into clientes("
                        + "nombre_cliente, direccion_cliente, id_ciudad, fecha_nacimiento_cliente, "
                        + "mail_cliente, telefono_cliente, contacto_cliente, actualizacion_cliente, id_usuario_auditoria, "
                        + "cedula_cliente, ruc_cliente) "
                        + "values('" + cliente.getNombre_cliente() + "', "
                        + "'" + cliente.getDireccion_cliente() + "', "
                        + "" + cliente.getCiudad().getId_ciudad() + ", "
                        + "'" + cliente.getFecha_nacimiento_cliente() + "', "
                        + "'" + cliente.getMail_cliente() + "', "
                        + "'" + cliente.getTelefono_cliente() + "', "
                        + "'" + cliente.getContacto_cliente() + "', "
                        + "now(), "
                        + "" + cliente.getUsuario_auditoria().getId_usuario() + ", "
                        + "'" + cliente.getCedula_cliente() + "', "
                        + "'" + cliente.getRuc_cliente() + "')";

                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

                try (ResultSet keyset = Conexion.getSt().getGeneratedKeys()) {
                    if (keyset.next()) {
                        id_cliente = keyset.getInt("id_cliente");
                        ok = true;
                    }
                    
                    Conexion.getCon().commit();
                    valor.put("ok", ok);
                    valor.put("id_cliente", id_cliente);
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

    public Map modificar(Cliente cliente) {
        Map valor = new HashMap();
        boolean ok = false;

        if (Conexion.conectar()) {
            try {
                String sql = "update clientes set "
                        + "nombre_cliente=?, direccion_cliente=?, id_ciudad=?, fecha_nacimiento_cliente=?, "
                        + "mail_cliente=?, telefono_cliente=?, contacto_cliente=?, actualizacion_cliente= now(), id_usuario_auditoria=?, "
                        + "cedula_cliente=?, ruc_cliente=? where id_cliente=?";

                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setString(1, cliente.getNombre_cliente());
                    ps.setString(2, cliente.getDireccion_cliente());
                    ps.setInt(3, cliente.getCiudad().getId_ciudad());
                    ps.setDate(4, cliente.getFecha_nacimiento_cliente());
                    ps.setString(5, cliente.getMail_cliente());
                    ps.setString(6, cliente.getTelefono_cliente());
                    ps.setString(7, cliente.getContacto_cliente());
                    ps.setInt(8, cliente.getUsuario_auditoria().getId_usuario());
                    ps.setString(9, cliente.getCedula_cliente());
                    ps.setString(10, cliente.getRuc_cliente());
                    ps.setInt(11, cliente.getId_cliente());

                    int cr = ps.executeUpdate();
                    Conexion.getCon().commit();
                    if (cr > 0) {
                        ok = true;
                    }
                    valor.put("ok", ok);
                    ps.close();
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

    public Cliente buscarId(int id_cliente) {
        Cliente cliente = new Cliente();
        cliente.setId_cliente(0);
        cliente.setCodigo_cliente(0);
        cliente.setCedula_cliente("");
        cliente.setNombre_cliente("");
        cliente.setDireccion_cliente("");

        Ciudad ciudad = new Ciudad();
        ciudad.setId_ciudad(0);
        ciudad.setNombre_ciudad("");
        cliente.setCiudad(ciudad);

        cliente.setFecha_nacimiento_cliente(new Date(new java.util.Date().getTime()));
        cliente.setMail_cliente("");
        cliente.setTelefono_cliente("");
        cliente.setContacto_cliente("");
        cliente.setActualizacion_cliente(new Timestamp(new java.util.Date().getTime()));

        Usuario usuario_auditoria = new Usuario();
        usuario_auditoria.setId_usuario(0);
        usuario_auditoria.setNombre_usuario("");
        usuario_auditoria.setUsuario_usuario("");
        cliente.setUsuario_auditoria(usuario_auditoria);

        Tipocliente tipocliente = new Tipocliente();
        tipocliente.setId_tipocliente(0);
        tipocliente.setNombre_tipocliente("");
        cliente.setTipocliente(tipocliente);

        if (Conexion.conectar()) {
            try {
                String sql = "select "
                        + "	codigo_cliente, "
                        + "	nombre_cliente, "
                        + "	direccion_cliente, "
                        + "	ci.id_ciudad, "
                        + "	ci.nombre_ciudad, "
                        + "	fecha_nacimiento_cliente,'dd-mm-yyyy', "
                        + "	mail_cliente, "
                        + "	telefono_cliente, "
                        + "	contacto_cliente, "
                        + "	actualizacion_cliente, "
                        + "	cl.id_usuario_auditoria, "
                        + "	tc.id_tipocliente, "
                        + "	tc.nombre_tipocliente, "
                        + "	cedula_cliente, "
                        + "	id_cliente, "
                        + "	ruc_cliente "
                        + "from clientes cl "
                        + "left join ciudades ci on cl.id_ciudad=ci.id_ciudad "
                        + "left join tiposclientes tc on cl.id_tipocliente=tc.id_tipocliente "
                        + "left join usuarios us on cl.id_usuario_auditoria=us.id_usuario "
                        + "where id_cliente=?";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, id_cliente);

                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {

                        cliente.setId_cliente(rs.getInt("id_cliente"));
                        cliente.setCodigo_cliente(rs.getInt("codigo_cliente"));
                        cliente.setCedula_cliente(rs.getString("cedula_cliente"));
                        cliente.setRuc_cliente(rs.getString("ruc_cliente"));
                        cliente.setNombre_cliente(rs.getString("nombre_cliente"));
                        cliente.setDireccion_cliente(rs.getString("direccion_cliente"));
                        cliente.getCiudad().setId_ciudad(rs.getInt("id_ciudad"));
                        cliente.getCiudad().setNombre_ciudad(rs.getString("nombre_ciudad"));
                        cliente.setFecha_nacimiento_cliente(rs.getDate("fecha_nacimiento_cliente"));
                        cliente.setMail_cliente(rs.getString("mail_cliente"));
                        cliente.setTelefono_cliente(rs.getString("telefono_cliente"));
                        cliente.setContacto_cliente(rs.getString("contacto_cliente"));
                        cliente.setActualizacion_cliente(rs.getTimestamp("actualizacion_cliente"));
                        cliente.getUsuario_auditoria().setId_usuario(rs.getInt("id_usuario_auditoria"));
                        cliente.getTipocliente().setId_tipocliente(rs.getInt("id_tipocliente"));
                        cliente.getTipocliente().setNombre_tipocliente(rs.getString("nombre_tipocliente"));
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return cliente;
    }

    public ArrayList buscarNombre(String bnombre, int pagina) {
        ArrayList datos = new ArrayList();
        int offset = (pagina - 1) * Util.REGISTROS_PAGINA;

        if (Conexion.conectar()) {
            try {
                String sql = "select * from clientes cl "
                        + "left join ciudades ci on cl.id_ciudad=ci.id_ciudad "
                        + "left join usuarios u on cl.id_usuario_auditoria=u.id_usuario "
                        + "left join tiposclientes tc on cl.id_tipocliente=tc.id_tipocliente "
                        + "where upper(cl.nombre_cliente) || ' ' || COALESCE(cl.cedula_cliente,'') || ' ' || COALESCE(cl.ruc_cliente,'') like '%" + bnombre.toUpperCase() + "%'"
                        + " order by id_cliente "
                        + "offset " + offset + " limit " + Util.REGISTROS_PAGINA;
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        Ciudad ciudad = new Ciudad();
                        ciudad.setId_ciudad(rs.getInt("id_ciudad"));
                        ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));

                        Usuario usuario = new Usuario();
                        usuario.setId_usuario(rs.getInt("id_usuario"));
                        usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                        usuario.setUsuario_usuario(rs.getString("usuario_usuario"));

                        Tipocliente tipocliente = new Tipocliente();
                        tipocliente.setId_tipocliente(rs.getInt("id_tipocliente"));
                        tipocliente.setNombre_tipocliente(rs.getString("nombre_tipocliente"));

                        Cliente cliente = new Cliente();
                        cliente.setId_cliente(rs.getInt("id_cliente"));
                        cliente.setCodigo_cliente(rs.getInt("codigo_cliente"));
                        cliente.setCedula_cliente(rs.getString("cedula_cliente"));
                        cliente.setRuc_cliente(rs.getString("ruc_cliente"));
                        cliente.setNombre_cliente(rs.getString("nombre_cliente"));
                        cliente.setDireccion_cliente(rs.getString("direccion_cliente"));
                        cliente.setCiudad(ciudad);
                        cliente.setFecha_nacimiento_cliente(rs.getDate("fecha_nacimiento_cliente"));
                        cliente.setMail_cliente(rs.getString("mail_cliente"));
                        cliente.setTelefono_cliente(rs.getString("telefono_cliente"));
                        cliente.setContacto_cliente(rs.getString("contacto_cliente"));
                        cliente.setActualizacion_cliente(rs.getTimestamp("actualizacion_cliente"));
                        cliente.setUsuario_auditoria(usuario);
                        cliente.setTipocliente(tipocliente);
                        datos.add(cliente);
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
