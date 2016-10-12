package py.com.quality.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import py.com.quality.modelos.Dependencia;
import py.com.quality.modelos.Producto;
import py.com.quality.utiles.Conexion;

public class ProductoDAO {

    public ArrayList buscarNombre() {
        ArrayList datos = new ArrayList();

        if (Conexion.conectar()) {
            try {
                String sql = "select * from productos order by id_producto";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        Producto producto = new Producto();
                        producto.setId_producto(rs.getInt("id_producto"));
                        producto.setNombre_producto(rs.getString("nombre_producto"));
                        producto.setCodigo_producto(rs.getString("codigo_producto"));
                        
                        

                        datos.add(producto);
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

