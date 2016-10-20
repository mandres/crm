package py.com.quality.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import py.com.quality.utiles.Conexion;

public class VendedorDAO {

    public boolean modificar_estado(int id_usuario, int id_estadovendedor) {

        boolean ok = false;

        if (Conexion.conectar()) {
            try {
                String sql = "update vendedores set "
                        + "id_estadovendedor=? where id_usuario=?";

                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {

                    ps.setInt(1, id_estadovendedor);
                    ps.setInt(2, id_usuario);

                    int cr = ps.executeUpdate();
                    Conexion.getCon().commit();
                    if (cr > 0) {
                        ok = true;
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getCon().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return ok;
    }
}
