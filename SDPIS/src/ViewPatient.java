import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by babatundeadeola on 20/11/2016.
 */
public class ViewPatient extends JFrame{
    private DataAccessBase view = new DataAccessBase("jdbc:mysql://stusql.dcs.shef.ac.uk/team012?user=team012&password=a735fd61");

    //private int customId = 0;
    public ViewPatient(int id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        //Set title and size of frame
        setTitle("Sheffield Dental Practice");
        setSize(1300, 600);

        //get content pane
        Container container = getContentPane();

        //Title of panel
        JLabel title = new JLabel("Patient Details");
        //create a panel set its layout, add a title
        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        mPanel.add(title, BorderLayout.NORTH);

        //panel to house text area and cancel appointment button
        JPanel unSub = new JPanel();
        unSub.setLayout(new BorderLayout());

        //create a text area to house patient details details
        JTextArea textArea = new JTextArea();
        textArea.setMargin(new Insets(10, 10, 10, 10));
        textArea.setEditable(false);
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(300, 300));
        String newLine = "\n";

        //add title to unsub panel
        JLabel title1 = new JLabel("Customer Details"+newLine);
        unSub.add(title1, BorderLayout.NORTH);
        //variables
        final int customId = id;
        String address= "";
        String subTitle ="";

        //customer details
        ResultSet custom = view.getData("SELECT * FROM Customer WHERE ID ="+ customId);
        //subscription details
        ResultSet subscribe = view.getData("SELECT * FROM Subscription WHERE SubscriptionID ="+ customId);
        //address details
        ResultSet addressDet = view.getData("SELECT * FROM Address WHERE AddressID ='"+ address+"'");
        if(!custom.next()){
            textArea.append("Customer Doesn't Exist");
        }else {
            while (custom.next()){
                //Retrieve by column name
                //int customerId = custom.getInt("ID");
                //custom.next();
                String customerTitle = custom.getString("Title");
                String first = custom.getString("Fname");
                String last = custom.getString("Lname");
                Date dob = custom.getDate("BirthDate");
                String phone = custom.getString("PhoneNum");
                address = custom.getString("AddressID");
                String customer = "ID: " + customId + " Title: " + customerTitle + " Name: " + first + " " + last + " D.O.B: " +
                        dob + " Phone No.: " + phone + " ";
                textArea.append(customer + newLine);
            }

            do {
                //Retrieve by column name
                //int a = subscribe.getInt("SubscriptionID");
                subscribe.next();
                subTitle = subscribe.getString("SubscriptionTitle");
                int c = subscribe.getInt("MonthlyCost");
                int d = subscribe.getInt("CheckUp");
                int e = subscribe.getInt("HygieneVisit");
                int f = subscribe.getInt("Repair");
                Date g = subscribe.getDate("EndDate");
                textArea.append("Subscription Details" + newLine);
                String subscribtion = "Subscription ID: " + customId + " Subscription Title: " + subTitle + " Monthly Cost: £" +
                        c + " Check-Ups: " + d + " Hygiene Visits: " + e + " Repairs: " + f + " End Date: " + g;
                textArea.append(subscribtion + newLine);
            } while (subscribe.next());

            do {
                //Retrieve by column name
                addressDet.next();
                String b = addressDet.getString("HouseNum");
                String c = addressDet.getString("Street");
                String d = addressDet.getString("City");
                String e = addressDet.getString("Region");
                String f = addressDet.getString("PostCode");
                textArea.append("Address" + newLine);
                String addressID = "House No.: " + b + " Street: " + c + " City: " + d + " Region: " + e + " PostCode: " + f;
                textArea.append(addressID + newLine);
            } while (addressDet.next());
        }

        //add textArea to the panel
        unSub.add(textArea, BorderLayout.CENTER);
        JPanel subC = new JPanel();
        subC.setLayout(new BorderLayout());
        //Title of panel
        JLabel title2 = new JLabel("Patient Details");
        subC.add(title2,BorderLayout.NORTH);

        //get end date of subscription
        LocalDate localDate = LocalDate.now();
        final String endDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate.plusYears(1));

        if(subTitle.equals("None")){
            //combobox subscription types
            JLabel sub = new JLabel("Subscription:");
            final String[] subTypes = {"NHS Free Plan", "Maintenance Plan", "Oral Health Plan", "Dental Repair Plan"};
            final JComboBox subList = new JComboBox(subTypes);

            //subscribe button
            JButton subcr = new JButton("Subscribe Patient");
            subcr.addActionListener(
                    new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            //create sql command baseed on combo box selection
                            String updateSubscription = "UPDATE Subscription SET ";
                            if (((String) subList.getSelectedItem()).equals("NHS Free Plan")) {
                                updateSubscription += "SubscriptionTitle = '" + (String) subList.getSelectedItem() + "', MonthlyCost = 0" +
                                        ", Checkup = 2, HygieneVisit = 2, Repair = 6, EndDate = '" + endDate + "'";
                            }else if (((String) subList.getSelectedItem()).equals("Maintenance Plan")) {
                                updateSubscription += "SubscriptionTitle = '" + (String) subList.getSelectedItem() + "', MonthlyCost = 15" +
                                        ", Checkup = 2, HygieneVisit = 2, Repair = 0, EndDate = '" + endDate + "'";
                            }else if (((String) subList.getSelectedItem()).equals("Oral Health Plan")) {
                                updateSubscription += "SubscriptionTitle = '" + (String) subList.getSelectedItem() + "', MonthlyCost = 21" +
                                        ", Checkup = 2, HygieneVisit = 4, Repair = 0, EndDate = '" + endDate + "'";
                            }else if (((String) subList.getSelectedItem()).equals("Dental Repair Plan")) {
                                updateSubscription += "SubscriptionTitle = '" + (String) subList.getSelectedItem() + "', MonthlyCost = 36" +
                                        ", Checkup = 2, HygieneVisit = 2, Repair = 2, EndDate = '" + endDate + "'";
                            }
                            updateSubscription += " WHERE SubscriptionID = " + customId;
                            view.updateData(updateSubscription);
                            dispose();
                            JOptionPane.showMessageDialog(null, "Patient Subscribed");
                        }
                    }
            );
            subC.add(sub);
            subC.add(subList, BorderLayout.CENTER);
            subC.add(subcr, BorderLayout.SOUTH);
        }else{
            JLabel sub = new JLabel("Unsubscription:");
            JButton subcr = new JButton("Unsubscribe Patient");
            subcr.addActionListener(
                    new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            //create sql command to unscubscribe patient
                            String firstSubscription = "UPDATE Subscription SET SubscriptionTitle= 'None', MonthlyCost = 0,"+
                                    "CheckUp = 0, HygieneVisit = 0, Repair = 0, EndDate = '"+endDate+"'";
                            view.updateData(firstSubscription);
                            dispose();
                            JOptionPane.showMessageDialog(null, "Patient Unsubscribed");
                        }
                    }
            );
            subC.add(sub);
            subC.add(subcr, BorderLayout.CENTER);
        }

        unSub.add(subC, BorderLayout.SOUTH);

        //go back button
        JButton btnBackMan = new JButton("Go Back");
        btnBackMan.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        try {
                            new ManagePatients().ManagePatients();
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
        mPanel.add(unSub, BorderLayout.CENTER);
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
