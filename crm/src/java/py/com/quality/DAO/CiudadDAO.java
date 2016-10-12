package py.com.quality.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import py.com.quality.modelos.Ciudad;
import py.com.quality.utiles.Conexion;

public class CiudadDAO {

    public ArrayList buscarNombre() {
        ArrayList datos = new ArrayList();

        if (Conexion.conectar()) {
            try {
                String sql = "select * from ciudades order by id_ciudad";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        Ciudad ciudad = new Ciudad();
                        ciudad.setId_ciudad(rs.getInt("id_ciudad"));
                        ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));
                        ciudad.setCodigo_ciudad(rs.getString("codigo_ciudad"));

                        datos.add(ciudad);
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
