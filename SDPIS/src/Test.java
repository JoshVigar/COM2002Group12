//import java.sql.*;
//
//public class Test {
//        public static void main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
//            Connection con = null;
//            Statement stmt;
//            try{
//                Class.forName("com.mysql.jdbc.Driver").newInstance();
//                String DB = "jdbc:mysql://stusql.dcs.shef.ac.uk/team012?user=team012&password=a735fd61";
//                con = DriverManager.getConnection(DB);
//
//                stmt = con.createStatement();
//                //String sql = "CREATE TABLE group_members(first_name VARCHAR(30), last_name VARCHAR(30), age INTEGER, PRIMARY KEY(last_name))" ;
//                //stmt.executeUpdate(sql);
//
//                String sql = "CREATE TABLE subscription(SubscriptionID INTEGER NOT NULL, SubscriptionTitle VARCHAR, MonthlyCost INTEGER, CheckUp INTEGER, HygienistVisits INTEGER, Repairs INTEGER, PRIMARY KEY (SubscriptionID))";
//                stmt.executeUpdate(sql);
//                sql = "CREATE TABLE address(AddressID VARCHAR, HouseNum INTEGER, Street VARCHAR, City VARCHAR, Region VARCHAR, PostCode VARCHAR, PRIMARY KEY (AddressID))";
//                stmt.executeUpdate(sql);
//                sql = "CREATE TABLE customer(ID INTEGER NOT NULL, Title VARCHAR,FName VARCHAR(30), LName VARCHAR(30),BirthDate DATE, PhoneNum VARCHAR(15), PRIMARY KEY (ID))";
//                stmt.executeUpdate(sql);
//                sql = "CREATE TABLE visitType(TypeID INTEGER, Partner VARCHAR, Duration TIME, Cost INTEGER , PRIMARY KEY (TypeID))";
//                stmt.executeUpdate(sql);
//                sql = "CREATE TABLE appointment(TypeOfVisit INTEGER, StartTime TIMESTAMP, EndTime TIMESTAMP, PRIMARY KEY (TypeOfVisit, StartTime))";
//                stmt.executeUpdate(sql);
//
//
//
//                stmt.close();
//            }
//            catch (SQLException ex) {
//                ex.printStackTrace(); }
//            finally {
//                if (con != null){
//                    con.close();
//                }
//            }
//        }
//}
