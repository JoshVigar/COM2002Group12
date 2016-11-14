import java.sql.*;

public class DataB {

        public static void main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
            Connection con = null;
            Statement stmt;
            try{
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                String DB = "jdbc:mysql://stusql.dcs.shef.ac.uk/team012?user=team012&password=a735fd61";
                con = DriverManager.getConnection(DB);

                stmt = con.createStatement();
                //String sql = "INSERT INTO customer Values(0, Mr,defaul, Customer, 2000-01-20, 12345678912, 0, 10 S1 3PD";
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
                    System.out.print(", Last: " + last);
                    System.out.println(", Age: " + age);
                }
                sql = "SELECT * FROM customer";
                rs = stmt.executeQuery(sql);
                while(rs.next()){
                    //Retrieve by column name
                    int id = rs.getInt("ID");
                    String title = rs.getString("Title");
                    String first = rs.getString("Fname");
                    String last = rs.getString("Lname");
                    Date dob = rs.getDate("BirthDate");
                    String phone = rs.getString("PhoneNum");
                    int subId = rs.getInt("SubscriptionID");
                    String address = rs.getString("AddressID");

                    //Display values
                    System.out.print("ID: " + id);
                    System.out.print(", Title: " + title);
                    System.out.print(", First: " + first);
                    System.out.print(", Last: " + last);
                    System.out.print(", DOB: " + dob);
                    System.out.print("Phone: " + phone);
                    System.out.print("SubID: " + subId);
                    System.out.print("AddressID: " + address);
                }
                stmt.close();
            }
            catch (SQLException ex) {
                ex.printStackTrace(); }
            finally {
                if (con != null){
                    con.close();
                }
            }

            new SDPISGUI().WelcomeGUI();
        }
}
