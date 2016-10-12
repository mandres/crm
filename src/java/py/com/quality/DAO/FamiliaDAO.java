package py.com.quality.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import py.com.quality.modelos.Familia;
import py.com.quality.utiles.Conexion;

public class FamiliaDAO {

    public ArrayList buscarNombre() {
        ArrayList datos = new ArrayList();

        if (Conexion.conectar()) {
            try {
                String sql = "select * from familias order by id_familia";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                       Familia familia = new Familia();
                        familia.setId_familia(rs.getInt("id_familia"));
                        familia.setNombre_familia(rs.getString("nombre_familia"));
                        familia.setCodigo_familia(rs.getString("codigo_familia"));

                        datos.add(familia);
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

