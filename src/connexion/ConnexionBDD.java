package connexion;
import java.sql.*;

public  class ConnexionBDD {
    public Connection execBDD()
    {	
      	Connection conn = null;
      	String url = "jdbc:mysql://localhost:3306/"; //String url = "jdbc:mysql://localhost/";
      	String dbName = "ppe4.1";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root"; 
        String password = ""; //saisir mot de passe si existant
        /*
    	Connection conn = null;
      	String url = "jdbc:mysql://89.159.118.89:3306/"; //String url = "jdbc:mysql://localhost/";
        String dbName = "rubinp";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "rubinp"; 
        String password = "240292f"; 
        */
    /*	Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "ppe4.1";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root"; 
        String password = ""; 
       */ 
        try {
            Class.forName(driver).newInstance();
            conn = (Connection) DriverManager.getConnection(url+dbName,userName,password);
		    //System.out.println("Connexion effective !"); 
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
