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
                
                /*String sql = "CREATE TABLE subscription(SubscriptionID INTEGER not NULL, SubscriptionTitle VARCHAR(40), MonthlyCost INTEGER, CheckUp INTEGER, HygieneVisits INTEGER, Repairs INTEGER, EndDate DATE, PRIMARY KEY (SubscriptionID))";
                stmt.executeUpdate(sql);
                sql = "CREATE TABLE address(AddressID VARCHAR(40), HouseNum INTEGER, Street VARCHAR(40), City VARCHAR(40), Region VARCHAR(40), PostCode VARCHAR(40), PRIMARY KEY (AddressID))";
                stmt.executeUpdate(sql);
                sql = "CREATE TABLE customer(ID INTEGER NOT NULL AUTO_INCREMENT, Title VARCHAR(10),FName VARCHAR(30), LName VARCHAR(30), BirthDate DATE, PhoneNum VARCHAR(15),SubscriptionID INTEGER, AddressID VARCHAR (40),PRIMARY KEY (ID), FOREIGN KEY (SubscriptionID) REFERENCES subscription(SubscriptionID),FOREIGN KEY (AddressID) REFERENCES address(AddressID))";
                stmt.executeUpdate(sql);
                sql= "CREATE TABLE visitType(TypeOfVisit VARCHAR(40) , Partner VARCHAR(40), Duration TIME, Cost INTEGER , PRIMARY KEY (TypeOfVisit))";
                stmt.executeUpdate(sql);
                sql ="CREATE TABLE appointment(ID INTEGER, TypeOfVisit VARCHAR(40), Partner VARCHAR(40), StartTime TIMESTAMP, EndTime TIMESTAMP, PRIMARY KEY (Partner, StartTime),FOREIGN KEY (ID) REFERENCES customer(ID),FOREIGN KEY (TypeOfVisit) REFERENCES visitType(TypeOfVisit))";
                stmt.executeUpdate(sql);*/

                stmt.close();

                new WelcomeGUI().WelcomeGUI();
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
