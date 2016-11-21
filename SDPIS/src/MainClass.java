/**
 * Created by babatundeadeola on 21/11/2016.
 */
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainClass {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        //variables for db connections
        Connection con = null;
        Statement stmt;
        try{
            //Connecting to the db
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String DB = "jdbc:mysql://stusql.dcs.shef.ac.uk/team012?user=team012&password=a735fd61";
            con = DriverManager.getConnection(DB);
            stmt = con.createStatement();
            LocalDate localDate = LocalDate.now();
            String date = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate);
            String subT = "";
            String endDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate.plusYears(1));
            boolean subExists = true;
            String updateSubscription = null;
            String getSubscription = "SELECT SubscriptionTitle FROM Subscription WHERE EndDate = '" +endDate +"'";
            ResultSet rs = stmt.executeQuery(getSubscription);
            while(rs.next()){
                if(!rs.wasNull()) {
                    subT = rs.getString("SubscriptionTitle").trim();
                    updateSubscription = "UPDATE Subscription SET ";
                    if (subT.equals("NHS Free Plan")) {
                        updateSubscription += "SubscriptionTitle = '" + subT + "', MonthlyCost = 0" +
                                ", Checkup = 2, HygieneVisit = 2, Repair = 6, EndDate = '" + endDate + "')";
                    } else if (subT.equals("Maintenance Plan")) {
                        updateSubscription += "SubscriptionTitle = '" + subT + "', MonthlyCost = 15" +
                                ", Checkup = 2, HygieneVisit = 2, Repair = 0, EndDate = '" + endDate + "')";
                    } else if (subT.equals("Oral Health Plan")) {
                        updateSubscription += "SubscriptionTitle = '" + subT + "', MonthlyCost = 21" +
                                ", Checkup = 2, HygieneVisit = 4, Repair = 0, EndDate = '" + endDate + "')";
                    } else if (subT.equals("Dental Repair Plan")) {
                        updateSubscription += "SubscriptionTitle = '" + subT + "', MonthlyCost = 36" +
                                ", Checkup = 2, HygieneVisit = 2, Repair = 2, EndDate = '" + endDate + "')";
                    }
                    else
                        subExists = false;
                    System.out.println(subT);
                    System.out.println(updateSubscription);
                    updateSubscription += " WHERE EndDate = '" + DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate) + "'";
                    if(subExists)
                        stmt.executeUpdate(updateSubscription);
                }
            }

            //initialise the GUI
            new WelcomeGUI().WelcomeGUI();
        }
        //Catch for the sql
        catch (SQLException ex) {
            ex.printStackTrace(); }
        finally {
            if (con != null){
                con.close();
            }
        }
    }
}

