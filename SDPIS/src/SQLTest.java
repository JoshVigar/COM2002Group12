

import java.sql.*;

public class SQLTest {
    public static void main(String[] args) throws SQLException {
        Connection con = null;
        try{
            String DB = "jdbc:mysql://stusql.dcs.shef.ac.uk/dbname?user=team012&password=a735fd61";
            con = DriverManager.getConnection(DB);
        }
        catch (SQLException ex) {
            ex.printStackTrace(); }
        finally {
            if (con != null){
                con.close();
            }
        }
    }
}


