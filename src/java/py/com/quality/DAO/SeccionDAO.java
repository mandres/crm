package py.com.quality.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import py.com.quality.utiles.Conexion;

public class SeccionDAO {

    public String generarLista() {
        String combo = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from secciones order by id_seccion";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        int id = rs.getInt("id_seccion");
                        String descripcion = rs.getString("nombre_seccion");
                        combo += "<option value='" + id + "'>" + descripcion + "</option>";
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return combo;
    }
}
