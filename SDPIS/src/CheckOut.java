import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

/**
 * Created by babatundeadeola on 21/11/2016.
 */
public class CheckOut extends JFrame{
    private DataAccessBase view = new DataAccessBase("jdbc:mysql://stusql.dcs.shef.ac.uk/team012?user=team012&password=a735fd61");

    public CheckOut(int id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        final int customId = id;
        //initial setting for window
        setTitle("Sheffield Dental Practice");
        setSize(500,600);

        //get content pane
        Container container = getContentPane();

        //Title of panel
        JLabel title = new JLabel("Patient Details");
        //create a panel set its layout, add a title
        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        mPanel.add(title, BorderLayout.NORTH);

        JPanel details = new JPanel();
        details.setLayout(new BorderLayout());

        //create a text area to house patient details details
        JTextArea textArea = new JTextArea();
        textArea.setMargin(new Insets(10, 10, 10, 10));
        textArea.setEditable(false);
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(300, 300));
        String newLine = "\n";

        //Subscrription Title
        String subTitle= "";
        String patientName= "";

        //get the patients first name and last name
        String sql = "SELECT FName, LName FROM Customer WHERE ID = "+customId;
        ResultSet rs = view.getData(sql);

        try {
            //loop through result set appending each appointment to the textArea
            while (rs.next()) {
                String fName = rs.getString("FName");
                String lName = rs.getString("LName");
                patientName = fName+" "+lName;
                textArea.append(patientName+newLine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        details.add(new JLabel(patientName), BorderLayout.NORTH);

        //subscription details
        ResultSet subscribe = view.getData("SELECT * FROM Subscription WHERE SubscriptionID ="+ customId);
        if(!subscribe.next()) {
            textArea.append("Not Available");
        }else{
            while (subscribe.next()){
                //Retrieve by column name
                //int a = subscribe.getInt("SubscriptionID");
                subTitle = subscribe.getString("SubscriptionTitle");
                int c = subscribe.getInt("MonthlyCost");
                int d = subscribe.getInt("CheckUp");
                int e = subscribe.getInt("HygieneVisit");
                int f = subscribe.getInt("Repair");
                Date g = subscribe.getDate("EndDate");
                textArea.append("Subscription Details:" + newLine);
                String subscribtion = "Subscription ID: " + customId + " Subscription Title: " + subTitle + " Monthly Cost: £" +
                        c + " Check-Ups: " + d + " Hygiene Visits: " + e + " Repairs: " + f + " End Date: " + g;
                textArea.append(subscribtion + newLine);
            }
        }


        int tempCost = 0;
        ResultSet apps = view.getData("SELECT * FROM Appointment WHERE STATE ='Waiting' AND ID ="+customId);
        String genVT = "";
        String type ="";
        //Appointment details
        int totalCost =0;
        if (!apps.next()) {
            textArea.append("No Appointments");
            } else {
            while (apps.next()) {
                //int id = rs.getInt("ID");
                type = apps.getString("TypeOfVisit");
                Time startTime = apps.getTime("StartTime");
                String partner = apps.getString("Partner");
                int cost = apps.getInt("Cost");
                Date appDate = apps.getDate("Date");
                String appoint = "Customer ID: " + id + " Date:" + appDate + " Start Time: " + startTime +
                        " Visit Type: " + type + " Partner: " + partner + " Cost: £" + cost;
                System.out.println(" " + appoint);
                textArea.append(appoint + newLine);
                totalCost += cost;
            }
            textArea.append("Total Cost: £"+totalCost+newLine);
            if(!subTitle.equals("None")){
                while (apps.next()) {
                    type = apps.getString("TypeOfVisit");
                    if (type.equals("CheckUp") || type.equals("HygieneVisit"))
                        genVT = type;
                    else if (type.equals("White Composite Resin Filling") || type.equals("Silver Almagam Filling"))
                        genVT = "Repair";
                    //this checks if the customer's subscription covers the cost of the appointment
                    boolean valRemainingSubs = true;
                    int changedSubs = 0;
                    String getRemainingSubs = "SELECT " + genVT + " FROM Subscription WHERE SubscriptionID = " + id;
                    ResultSet remainingSubs = view.getData(getRemainingSubs);
                    try {
                        while (remainingSubs.next()) {
                            if (remainingSubs.getInt(1) > 0) {
                                changedSubs = remainingSubs.getInt(1) - 1;
                            } else {
                                valRemainingSubs = false;
                            }
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    //if a patient's subscription covered him then remove 1 from the visits/checups/repairs
                    String updateSubs = "UPDATE Subscription SET " + genVT + " = " + changedSubs
                            + " WHERE SubscriptionID = " + id;
                    if (valRemainingSubs) {
                        view.updateData(updateSubs);
                    } else {
                        //cost = 0 if the subscription covers it, if not get the cost
                        String getCost = "SELECT Cost FROM VisitType Where TypeOfVisit = '" + type + "'";
                        ResultSet rCost = view.getData(getCost);
                        try {
                            while (rCost.next()) {
                                tempCost += rCost.getInt("Cost");
                            }

                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                textArea.append("Actual Cost: £"+tempCost);
                while (subscribe.next()){
                    //Retrieve by column name
                    //int a = subscribe.getInt("SubscriptionID");
                    subTitle = subscribe.getString("SubscriptionTitle");
                    int c = subscribe.getInt("MonthlyCost");
                    int d = subscribe.getInt("CheckUp");
                    int e = subscribe.getInt("HygieneVisit");
                    int f = subscribe.getInt("Repair");
                    Date g = subscribe.getDate("EndDate");
                    textArea.append("Updated Subscription Details:" + newLine);
                    String subscribtion = "Subscription ID: " + customId + " Subscription Title: " + subTitle + " Monthly Cost: £" +
                            c + " Check-Ups: " + d + " Hygiene Visits: " + e + " Repairs: " + f + " End Date: " + g;
                    textArea.append(subscribtion + newLine);
                }
            }
        }

        details.add(textArea,BorderLayout.CENTER);
        //finish button
        JButton btnFin = new JButton("Check Out");
        btnFin.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        view.updateData("UPDATE Appointment SET State = 'Finished' WHERE STATE ='Waiting' AND ID =" + customId);
                        dispose();
                        try {
                            new SecretaryGUI().CheckoutPatient();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                        } catch (InstantiationException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
        );
        details.add(btnFin,BorderLayout.SOUTH);
        //go back button
        JButton btnBackMan = new JButton("Go Back");
        btnBackMan.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        try {
                            new SecretaryGUI().CheckoutPatient();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                        } catch (InstantiationException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
        );

        //border sizes
        int bHeight = (int) (this.getHeight() * 0.05);
        int bWidth = (int) (this.getWidth() * 0.05);
        //add contents of mPanel
        mPanel.add(details, BorderLayout.CENTER);
        mPanel.add(btnBackMan, BorderLayout.SOUTH);
        mPanel.setBorder(BorderFactory.createEmptyBorder(bHeight, bWidth, bHeight, bWidth));
        container.add(mPanel);

        //Don't forget to pack! and setVisible to true]
        view.closeConnection();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
/*
    //this checks if an appointment exists
    boolean appointmentExists = true;
    String visitType= "",genVT ="";
    int id = 0;
    String getVT = "SELECT TypeOfVisit,ID FROM Appointment Where (State = 'Active' "
            + "And ADate = '" + today + "' AND StartTime = '" + hr.getSelectedItem().toString()
            +":"+ min.getSelectedItem().toString()+":00' AND Partner = 'Dentist')";
    ResultSet rVT = reg.getData(getVT);
                        try {
                                while(rVT.next()){
                                if(!rVT.wasNull()) {
                                visitType = rVT.getString("TypeOfVisit");
                                id = rVT.getInt("ID");
                                }
                                else
                                appointmentExists = false;
                                }
                                } catch (SQLException e1) {
                                e1.printStackTrace();
                                }
                                //if it does then it wil first check in what category it is
                                if(appointmentExists) {
                                if (visitType.equals("CheckUp") || visitType.equals("HygieneVisit"))
                                genVT = visitType;
                                else
                                genVT = "Repair";
                                try {
                                reg.closeConnection();
                                } catch (SQLException e1) {
                                e1.printStackTrace();
                                } catch (ClassNotFoundException e1) {
                                e1.printStackTrace();
                                } catch (IllegalAccessException e1) {
                                e1.printStackTrace();
                                } catch (InstantiationException e1) {
                                e1.printStackTrace();
                                }
                                //this checks if the customer's subscription covers the cost of the appointment
                                boolean valRemainingSubs = true;
                                int changedSubs = 0;
                                String getRemainingSubs = "SELECT " + genVT + " FROM Subscription WHERE SubscriptionID = " + id;
                                ResultSet remainingSubs = reg.getData(getRemainingSubs);
                                try {
                                while (remainingSubs.next()) {
                                if (remainingSubs.getInt(1) > 0) {
                                changedSubs = remainingSubs.getInt(1) - 1;
                                } else {
                                valRemainingSubs = false;
                                }
                                }
                                } catch (SQLException e1) {
                                e1.printStackTrace();
                                }
                                try {
                                reg.closeConnection();
                                } catch (SQLException e1) {
                                e1.printStackTrace();
                                } catch (ClassNotFoundException e1) {
                                e1.printStackTrace();
                                } catch (IllegalAccessException e1) {
                                e1.printStackTrace();
                                } catch (InstantiationException e1) {
                                e1.printStackTrace();
                                }
                                int cost = 0;
                                //if a patient's subscription covered him then remove 1 from the visits/checups/repairs
                                String updateSubs = "UPDATE Subscription SET " + genVT + " = " + changedSubs
                                + " WHERE SubscriptionID = " + id;
                                if (valRemainingSubs) {
                                reg.updateData(updateSubs);
                                } else {
                                //cost = 0 if the subscription covers it, if not get the cost
                                String getCost = "SELECT Cost FROM VisitType Where TypeOfVisit = '" + visitType + "'";
                                ResultSet rCost = reg.getData(getCost);
                                try {
                                while (rCost.next()) {
                                cost = rCost.getInt("Cost");
                                }
                                } catch (SQLException e1) {
                                e1.printStackTrace();
                                }
                                }
                                //update the appointment to waiting and assign a cost to it
                                String updateAppointment = "UPDATE Appointment SET State = 'Waiting' WHERE (State = 'Active' "
                                + "And ADate = '" + today + "' AND StartTime = '" + hr.getSelectedItem().toString()
                                + ":" + min.getSelectedItem().toString() + ":00' AND Partner = 'Dentist')";
                                reg.updateData(updateAppointment);
                                }
                                else{
                                JOptionPane.showMessageDialog(null, "This Appointment time is unavailable. Please Select another.");

                                }*/
