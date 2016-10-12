package py.com.quality.utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
    private final static String driver = "org.postgresql.Driver";
    private final static String servidor = "localhost";
    private final static String puerto = "5432";
    private final static String usuario = "postgres";
    private final static String clave = "postgres";
//    private final static String clave = "1";
    private final static String basedato = "crm_quality";
    private static Connection con;
    private static Statement st;

    public static boolean conectar() {
        boolean valor = false;
        try {
            Class.forName(driver);
            String connectString = "jdbc:postgresql://"+servidor+":"+puerto+"/"+basedato;
            con = DriverManager.getConnection(connectString, usuario, clave);
            st = con.createStatement();
            con.setAutoCommit(false);
            st.setFetchSize(100);
            valor = true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("error --> "+ex.getLocalizedMessage());
        }
        return valor;
    }

    public static boolean cerrar(){
        boolean valor=false;
        try {
            st.close();
            con.close();
            valor=true;
        } catch (SQLException ex) {
            System.out.println("--> "+ex.getLocalizedMessage());
        }
        return valor;
    }

    public static Connection getCon() {
        return con;
    }

    public static Statement getSt() {
        return st;
    }
}
