package py.com.quality.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import py.com.quality.modelos.Familia;
import py.com.quality.modelos.Grupo;
import py.com.quality.modelos.Marca;
import py.com.quality.modelos.Seccion;
import py.com.quality.modelos.Tipo;
import py.com.quality.utiles.Conexion;

public class GrupoDAO {

    public ArrayList buscarNombre() {
        ArrayList datos = new ArrayList();

        if (Conexion.conectar()) {
            try {
                String sql = "select * from grupos order by id_grupo";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                       Grupo grupo = new Grupo();
                        grupo.setId_grupo(rs.getInt("id_grupo"));
                        grupo.setNombre_grupo(rs.getString("nombre_grupo"));
                        grupo.setCodigo_grupo(rs.getString("codigo_grupo"));
                        
                        Tipo tipo= new Tipo();
                        tipo.setId_tipo(rs.getInt("id_tipo"));
                        tipo.setNombre_tipo(rs.getString("nombre_tipo"));                       
                        
                      
                        Familia familia= new Familia();
                        familia.setId_familia(rs.getInt("id_familia"));
                        familia.setNombre_familia(rs.getString("nombre_familia"));
                        
                        Seccion seccion= new Seccion();
                        seccion.setId_seccion(rs.getInt("id_seccion"));
                        seccion.setNombre_seccion(rs.getString("nombre_seccion"));
                        
                        Marca marca= new Marca();
                        marca.setId_marca(rs.getInt("id_marca"));
                        marca.setNombre_marca(rs.getString("nombre_marca"));
                        

                        datos.add(grupo);
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

