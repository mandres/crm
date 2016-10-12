package py.com.quality.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import py.com.quality.modelos.Temporada;
import py.com.quality.utiles.Conexion;

public class TemporadaDAO {

    public ArrayList buscarNombre() {
        ArrayList datos = new ArrayList();

        if (Conexion.conectar()) {
            try {
                String sql = "select * from temporadas order by id_temporada";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                       Temporada temporada = new Temporada();
                        temporada.setId_temporada(rs.getInt("id_temporada"));
                        temporada.setNombre_temporada(rs.getString("nombre_temporada"));
                        temporada.setCodigo_temporada(rs.getString("codigo_temporada"));

                        datos.add(temporada);
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

