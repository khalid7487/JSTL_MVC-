
package util;

/**
 *
 * @author KHALID
 */
import java.sql.Connection;
import java.sql.DriverManager;
public class Database {
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/loginjdbc",
                    "root","7487");
            return con;
        } catch (Exception ex) {
           ex.printStackTrace();
            return null;
        }
    }
    public static void close(Connection con){
        try {
            con.close();
        } catch (Exception e) {
        }
    }
}
