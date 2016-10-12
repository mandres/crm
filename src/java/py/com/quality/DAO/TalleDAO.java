package py.com.quality.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import py.com.quality.modelos.Talle;
import py.com.quality.utiles.Conexion;

public class TalleDAO {

    public ArrayList buscarNombre() {
        ArrayList datos = new ArrayList();

        if (Conexion.conectar()) {
            try {
                String sql = "select * from talles order by id_talle";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                       Talle talle = new Talle();
                        talle.setId_talle(rs.getInt("id_talle"));
                        talle.setNombre_talle(rs.getString("nombre_talle"));
                        talle.setCodigo_talle(rs.getString("codigo_talle"));

                        datos.add(talle);
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
