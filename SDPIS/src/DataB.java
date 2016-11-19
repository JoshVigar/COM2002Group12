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

/*
                String sql = "CREATE TABLE Subscription(SubscriptionID INTEGER NOT NULL AUTO_INCREMENT, SubscriptionTitle VARCHAR(40),"+
                        " MonthlyCost INTEGER, CheckUp INTEGER, HygieneVisit INTEGER, Repair INTEGER, EndDate DATE,"+
                        " PRIMARY KEY (SubscriptionID))";
                stmt.executeUpdate(sql);
                sql = "CREATE TABLE Address(AddressID VARCHAR(40), HouseNum VARCHAR (40), Street VARCHAR(40), City VARCHAR(40),"+
                        " Region VARCHAR(40), PostCode VARCHAR(40), PRIMARY KEY (AddressID))";
                stmt.executeUpdate(sql);
                sql = "CREATE TABLE Customer(ID INTEGER NOT NULL AUTO_INCREMENT, Title VARCHAR(10),FName VARCHAR(30),"+
                        " LName VARCHAR(30), BirthDate DATE, PhoneNum VARCHAR(15),"+
                        " AddressID VARCHAR (40),PRIMARY KEY (ID),"+
                        "FOREIGN KEY (AddressID) REFERENCES Address(AddressID))";
                stmt.executeUpdate(sql);
                sql= "CREATE TABLE VisitType(TypeOfVisit VARCHAR(40) , Partner VARCHAR(40), Duration INTEGER, Cost INTEGER ,"+
                        " PRIMARY KEY (TypeOfVisit))";
                stmt.executeUpdate(sql);
                sql ="CREATE TABLE Appointment(ID INTEGER, TypeOfVisit VARCHAR(40), Partner VARCHAR(40), ADate DATE ,StartTime TIME,"+
                    " EndTime TIME, State VARCHAR(10), Cost INTEGER,PRIMARY KEY (Partner,ADate,StartTime),FOREIGN KEY (ID) REFERENCES Customer(ID),"+
                    "FOREIGN KEY (TypeOfVisit) REFERENCES VisitType(TypeOfVisit))";
                stmt.executeUpdate(sql);


            String sql = "SELECT * FROM Customer";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                //Retrieve by column name
                int id = rs.getInt("ID");
                String title = rs.getString("Title");
                String first = rs.getString("Fname");
                String last = rs.getString("Lname");
                Date dob = rs.getDate("BirthDate");
                String phone = rs.getString("PhoneNum");
                String address = rs.getString("AddressID");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Title: " + title);
                System.out.print(", First: " + first);
                System.out.print(", Last: " + last);
                System.out.print(", DOB: " + dob);
                System.out.print("Phone: " + phone);
                System.out.println("AddressID: " + address);
            }

            sql = "SELECT * FROM Address";
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                String a =rs.getString("AddressID");
                String b =rs.getString("HouseNum");
                String c =rs.getString("Street");
                String d =rs.getString("City");
                String e =rs.getString("Region");
                String f =rs.getString("PostCode");

                System.out.print("AddressID:"+a);
                System.out.print(" House No.:"+b);
                System.out.print(" Street:"+c);
                System.out.print(" City:"+d);
                System.out.print(" Region:"+e);
                System.out.println(" PostCode:"+f);
            }

            sql = "SELECT * FROM Subscription";
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                int a =rs.getInt("SubscriptionID");
                String b =rs.getString("SubscriptionTitle");
                int c =rs.getInt("MonthlyCost");
                int d =rs.getInt("CheckUp");
                int e =rs.getInt("HygieneVisit");
                int f =rs.getInt("Repair");
                Date g =rs.getDate("EndDate");

                System.out.print("SubscriptionID:"+a);
                System.out.print(" SubscriptionTitle:"+b);
                System.out.print(" MonthlyCost:"+c);
                System.out.print(" CheckUp:"+d);
                System.out.print(" HygieneVisit:"+e);
                System.out.print(" Repair:"+f);
                System.out.println(" EndDate:"+g);
            }

            stmt.executeUpdate("INSERT INTO VisitType VALUES ('HygieneVisit', 'Hygienist', 20, 45)");
            stmt.executeUpdate("INSERT INTO VisitType VALUES ('CheckUp', 'Dentist', 20, 45)");
            stmt.executeUpdate("INSERT INTO VisitType VALUES ('Silver Almagam Filling', 'Dentist', 60, 90)");
            stmt.executeUpdate("INSERT INTO VisitType VALUES ('White Composite Resin Filling', 'Dentist', 60, 150)");
            stmt.executeUpdate("INSERT INTO VisitType VALUES ('Gold Crown Fitting', 'Dentist', 60, 500)");

            stmt.executeUpdate("ALTER TABLE Appointment ADD FINISHED BOOLEAN");
            stmt.close();

            stmt.executeUpdate("DROP TABLE Appointment");


            //stmt.executeUpdate("INSERT INTO Appointment VALUES(1,'CheckUp','Dentist','2016-11-17','09:00:00','09:20:00','Active')");
            //stmt.executeUpdate("INSERT INTO Appointment VALUES(2,'HygieneVisit','Hygienist','2016-11-17','09:00:00','09:20:00','Active')");
            //stmt.executeUpdate("INSERT INTO Appointment VALUES(1,'CheckUp','Dentist','2016-11-17','09:20:00','09:40:00','Active')");
            //stmt.executeUpdate("INSERT INTO Appointment VALUES(2,'HygieneVisit','Hygienist','2016-11-17','09:20:00','09:40:00','Cancelled')");

            //stmt.executeUpdate("INSERT INTO Appointment VALUES(1,'CheckUp','Dentist','2016-11-17','09:40:00','10:00:00','Active')");
            //stmt.executeUpdate("INSERT INTO Appointment VALUES(1,'CheckUp','Dentist','2016-11-17','10:00:00','10:20:00','Active')");
            //stmt.executeUpdate("INSERT INTO Appointment VALUES(1,'CheckUp','Dentist','2016-11-17','10:20:00','10:40:00','Active')");
            //stmt.executeUpdate("INSERT INTO Appointment VALUES(1,'HygieneVisit','Hygienist','2016-11-17','10:00:00','10:20:00','Active')");

            String sql = "SELECT ID,TypeOfVisit,ADate,StartTime,EndTime FROM Appointment WHERE Partner = 'Dentist' AND State = 'Active'";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                int id = rs.getInt("ID");
                String type = rs.getString("TypeOfVisit");
                Date dates = rs.getDate("ADate");
                Time startTime = rs.getTime("StartTime");
                Time endTime = rs.getTime("EndTime");
                String appoint = "Date: "+ dates+" Start Time: "+ startTime+" End Time: "+endTime+" Customer ID: "+id+" Visit Type: "+type;
                System.out.println(appoint);
            }

            stmt.executeUpdate("DROP TABLE Appointment");
            String sql ="CREATE TABLE Appointment(ID INTEGER, TypeOfVisit VARCHAR(40), Partner VARCHAR(40), ADate DATE ,StartTime TIME,"+
                    " EndTime TIME, State VARCHAR(10), Cost INTEGER,PRIMARY KEY (Partner,ADate,StartTime),FOREIGN KEY (ID) REFERENCES Customer(ID),"+
                    "FOREIGN KEY (TypeOfVisit) REFERENCES VisitType(TypeOfVisit))";
            stmt.executeUpdate(sql);
            */
            //TypeOfVisit VARCHAR(40) , Partner VARCHAR(40), Duration INTEGER, Cost INTEGER ,"

            /*String sql = "SELECT * FROM VisitType";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getString("TypeOfVisit"));
                System.out.println(rs.getString("Duration"));
                System.out.println(rs.getString("Cost"));
            }*/
            //String[] appTypes = { "CheckUp", "HygieneVisit" , "White Composite Resin Filling", "Gold Crown Filling", "Silver Amalgam Filling"};
            //String sql = "UPDATE VisitType SET TypeOfVisit = 'Silver Amalgam Filling' WHERE TypeOfVisit = 'Silver Almagam Filling'";
            //int rs = stmt.executeUpdate(sql);
            //sql = "UPDATE VisitType SET TypeOfVisit = 'Gold Crown Filling' WHERE TypeOfVisit = 'Gold Crown Fitting'";
            //rs = stmt.executeUpdate(sql);

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