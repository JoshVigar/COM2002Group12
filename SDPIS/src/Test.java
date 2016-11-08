import java.sql.*;

public class Test {
        public static void main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
            Connection con = null;
            Statement stmt;
            try{
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                String DB = "jdbc:mysql://stusql.dcs.shef.ac.uk/team012?user=team012&password=a735fd61";
                con = DriverManager.getConnection(DB);

                stmt = con.createStatement();
                //String sql = "CREATE TABLE group_members(first_name VARCHAR(30), last_name VARCHAR(30), age INTEGER, PRIMARY KEY(last_name))" ;
                //stmt.executeUpdate(sql);

                String sql = "SELECT * FROM group_members";
                ResultSet rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                while(rs.next()){
                    //Retrieve by column name
                    String first = rs.getString("first_name");
                    String last = rs.getString("last_name");
                    int age = rs.getInt("age");

                    //Display values
                    System.out.print("First: " + first);
                    System.out.print(" Last: " + last);
                    System.out.println(" Age: " + age);
                }
                rs.close();
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
