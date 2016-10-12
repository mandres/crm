package py.com.quality.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import py.com.quality.modelos.Dependencia;
import py.com.quality.utiles.Conexion;

public class DependenciaDAO {

    public ArrayList buscarNombre() {
        ArrayList datos = new ArrayList();

        if (Conexion.conectar()) {
            try {
                String sql = "select * from dependencias order by id_dependencia";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        Dependencia dependencia = new Dependencia();
                        dependencia.setId_dependencia(rs.getInt("id_dependencia"));
                        dependencia.setNombre_dependencia(rs.getString("nombre_dependencia"));
                        dependencia.setCodigo_dependencia(rs.getString("codigo_dependencia"));

                        datos.add(dependencia);
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

