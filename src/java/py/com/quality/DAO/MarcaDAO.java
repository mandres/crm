package py.com.quality.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import py.com.quality.modelos.Marca;
import py.com.quality.utiles.Conexion;

public class MarcaDAO {

    public ArrayList buscarNombre() {
        ArrayList datos = new ArrayList();

        if (Conexion.conectar()) {
            try {
                String sql = "select * from marcas order by id_marca";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                      Marca marca = new Marca();
                        marca.setId_marca(rs.getInt("id_marca"));
                        marca.setNombre_marca(rs.getString("nombre_marca"));
                        marca.setCodigo_marca(rs.getString("codigo_marca"));

                        datos.add(marca);
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

