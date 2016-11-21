import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

/**
 * Created by User on 16/11/2016.
 */
public class BookAppointment extends JFrame{
    public void BookAppointment()throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException{

        final DataAccessBase reg = new DataAccessBase("jdbc:mysql://stusql.dcs.shef.ac.uk/team012?user=team012&password=a735fd61");
        setTitle("Sheffield Dental Practice");
        setSize(500,600);

        //Creating labels and text fields
        JLabel title = new JLabel("Enter Appointment Details");
        JLabel pID = new JLabel("PatientID:");
        final JLabel partner = new JLabel("Partner:");
        JLabel tType = new JLabel("Treatment Type:");
        final JTextField txtPID = new JTextField(20);
        String[] partners = {"Dentist","Hygienist"};
        final JComboBox Partner = new JComboBox(partners);
        JLabel sTime = new JLabel("Appointment Start Time:");

        //Creating the combobox for the hours and minutes entries for appointments
        String[] hour = {"Hour","09","10","11","12","14","15","16"};
        String[] minute = {"Minute","00","20","40"};
        final JComboBox hr = new JComboBox(hour);
        final JComboBox min = new JComboBox(minute);
        //combo boxes for types of appointments
        String[] appTypes = { "CheckUp", "HygieneVisit" , "White Composite Resin Filling", "Gold Crown Filling", "Silver Amalgam Filling"};
        final JComboBox aType = new JComboBox(appTypes);
        JButton bBook = new JButton("Book");

        //comboboxes date of appointment
        JLabel date = new JLabel("Date of Appointment:");
        final JComboBox days = new JComboBox();
        final JComboBox months = new JComboBox();
        final JComboBox years = new JComboBox();

        //Use for loops to create the date options
        days.addItem("Day");
        for(int i=1;i<=31;i++) {
            days.addItem(new Integer(i));
        }

        months.addItem("Month");
        for(int i=1;i<=12;i++) {
            months.addItem(new Integer(i));
        }

        years.addItem("Year");
        for(int i=(int)(Calendar.getInstance().get(Calendar.YEAR));i<=
                (int)(Calendar.getInstance().get(Calendar.YEAR))+1;i++) {
            years.addItem(new Integer(i));
        }

        //making panel for time of appointment
        JPanel timePanel = new JPanel();
        timePanel.add(sTime);
        timePanel.add(hr);
        timePanel.add(min);

        //create a panel and add date comboBox
        JPanel dPanel = new JPanel();
        dPanel.add(date);
        dPanel.add(days);
        dPanel.add(months);
        dPanel.add(years);

        //panel for the rest of the inputs
        JPanel inputsPanel = new JPanel();
        inputsPanel.add(pID);
        inputsPanel.add(txtPID);
        inputsPanel.add(partner);
        inputsPanel.add(Partner);
        inputsPanel.add(timePanel);
        inputsPanel.add(tType);
        inputsPanel.add(aType);
        inputsPanel.setLayout(new BoxLayout(inputsPanel, BoxLayout.Y_AXIS));

        //adding panels to a main panel
        JPanel mPanel = new JPanel();
        mPanel.add(inputsPanel);
        mPanel.add(dPanel);
        mPanel.add(bBook);
        mPanel.setLayout(new BoxLayout(mPanel, BoxLayout.Y_AXIS));


        //button to book appointment
        bBook.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        //boolean for validation
                        boolean val = true;

                        //checking months and days for consistency eg. No 31st of February
                        if ((months.getSelectedIndex() == 4
                                || months.getSelectedIndex() == 6 || months.getSelectedIndex() == 9
                                || months.getSelectedIndex() == 11) && days.getSelectedIndex() >= 31) {
                            JOptionPane.showMessageDialog(null, "Invalid day selected");
                            val = false;
                        } else if ((months.getSelectedIndex() == 2 && days.getSelectedIndex() >= 30
                                && years.getSelectedIndex() % 4 != 0)
                                || months.getSelectedIndex() == 2 && days.getSelectedIndex() <= 29
                                && years.getSelectedIndex() % 4 == 0) {
                            JOptionPane.showMessageDialog(null, "Invalid day selected");
                            val = false;
                        }
                        if (years.getSelectedItem().equals("Year") || (months.getSelectedItem().equals("Month") || days.getSelectedItem().equals("Day"))){
                            val = false;
                        }

                        //Checking the text fields have something in them
                        if (txtPID.getText().trim().equals("")) {
                            val = false;
                        }
                        //message if fields are not filled/unselected
                        if(!val){
                            JOptionPane.showMessageDialog(null, "Invalid input, some field is blank or incorrect");
                        }

                        //if validation succeeds then add entry to database
                        if (val) {
                            String getVisitDuration = "SELECT Duration FROM VisitType Where TypeOfVisit='"
                                    + (String) aType.getSelectedItem() + "'";
                            int dur = 0;
                            ResultSet rs = reg.getData(getVisitDuration);
                            try {
                                while (rs.next()) {
                                    dur = rs.getInt("Duration");
                                }
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }

                            int startMinutes = Integer.parseInt(min.getSelectedItem().toString());
                            int startHours = Integer.parseInt(hr.getSelectedItem().toString());

                            int endMinutes=0;
                            int endHours=0;

                            if (dur + startMinutes >= 60) {
                                endMinutes = startMinutes + dur - 60;
                                endHours = startHours + 1;
                            } else {
                                endMinutes = startMinutes + dur;
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
                            System.out.println(dur);

                            int startH, endH, startM, endM,newSM = 0;
                            boolean validateBooking = true, validateID = true;
                            String getClientsID = "SELECT ID FROM Appointment Where (ADate = '"
                                    + years.getSelectedItem().toString() + "-" + months.getSelectedItem().toString() + "-" +
                                    days.getSelectedItem().toString()+"'";
                            for(int i =0;i<dur;i+=20){
                                if(i==0){
                                    getClientsID = getClientsID + " AND StartTime = '" + startHours + ":" + startMinutes + ":00'" +
                                            " AND State = 'Active')";
                                }
                                else if(startMinutes+i<60){
                                    getClientsID = getClientsID + " OR (ADate = '"
                                            + years.getSelectedItem().toString() + "-" + months.getSelectedItem().toString() + "-" +
                                            days.getSelectedItem().toString()+"' AND StartTime = '" + startHours + ":" + (startMinutes+i) + ":00'" +
                                            " AND State = 'Active')";
                                }
                                else {
                                    getClientsID = getClientsID + " OR (ADate = '"
                                            + years.getSelectedItem().toString() + "-" + months.getSelectedItem().toString() + "-" +
                                            days.getSelectedItem().toString()+"' AND StartTime = '" + (startHours + 1) + ":" + newSM + ":00'" +
                                            " AND State = 'Active')";
                                    newSM+=20;
                                }
                            }
                            ResultSet clientsID = reg.getData(getClientsID);
                            try {
                                while (clientsID.next()) {
                                    if(!clientsID.wasNull()) {
                                        if (clientsID.getInt("ID") == Integer.parseInt(txtPID.getText().trim()))
                                            validateID = false;
                                        System.out.println(clientsID.getInt("ID"));
                                    }else{
                                        validateID = true;
                                    }
                                }
                                if (!validateID) {
                                    validateBooking = false;
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
                            String getOtherAppointments = "SELECT ID,StartTime,EndTime FROM Appointment WHERE ADate = '"
                                    + years.getSelectedItem().toString() + "-" + months.getSelectedItem().toString() + "-"
                                    + days.getSelectedItem().toString() + "' AND Partner = '" + Partner.getSelectedItem().toString() + "'" +
                                    "  AND (State = 'Active' OR State = 'Vacation')";
                            ResultSet appointmentDT = reg.getData(getOtherAppointments);
                            try {
                                while (appointmentDT.next()) {
                                    if(!appointmentDT.wasNull()) {
                                        startH = Integer.parseInt(appointmentDT.getTime("StartTime").toString().substring(0, 2));
                                        startM = Integer.parseInt(appointmentDT.getTime("StartTime").toString().substring(3, 5));
                                        endH = Integer.parseInt(appointmentDT.getTime("EndTime").toString().substring(0, 2));
                                        endM = Integer.parseInt(appointmentDT.getTime("EndTime").toString().substring(3, 5));

                                        System.out.println("Customer ID " + appointmentDT.getInt("ID"));
                                        System.out.println(startH);
                                        System.out.println(startM);
                                        System.out.println(endH);
                                        System.out.println(endM);

                                        if (((startHours*60 + startMinutes) >= (startH*60 + startM)
                                                && (startHours*60 + startMinutes) <= (endH*60 + endM))
                                                || (startHours*60 + startMinutes) >= (startH*60 + startM)
                                                && (endHours*60 + endMinutes) <= (endH*60 + endM)) {
                                            validateBooking = false;
                                        }
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

                            if (validateBooking) {
                                String newBooking = "INSERT INTO Appointment VALUES(" + txtPID.getText() + ", '" +
                                        (String) aType.getSelectedItem() + "', '" + (String) Partner.getSelectedItem() + "', '" +
                                        years.getSelectedItem().toString() + "-" + months.getSelectedItem().toString() + "-" +
                                        days.getSelectedItem().toString() + "', '" + hr.getSelectedItem().toString() + ":"
                                        + min.getSelectedItem().toString() + ":00', '"
                                        + Integer.toString(endHours) + ":" + Integer.toString(endMinutes) + ":00', 'Active', 0)";
                                reg.updateData(newBooking);
                                dispose();
                                new SecretaryGUI().SecretaryGUI();
                            }else{
                                JOptionPane.showMessageDialog(null, "This Appointment time is unavailable. Please Select another.");
                            }
                        }
                    }
                }
        );

        //Add back button and event listener
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        new SecretaryGUI().SecretaryGUI();
                    }
                }
        );

        //work out height and width for the window
        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);

        //add items to contentpane and manage the layout
        Container contentPane = getContentPane();
        contentPane.add(title, BorderLayout.NORTH);
        contentPane.add(mPanel, BorderLayout.CENTER);
        contentPane.add(btnBack, BorderLayout.SOUTH);

        //add the borders
        mPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));

        //Don't forget to pack!
        pack();
        //setting the location in the window and the close operation
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void BookHoliday() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        //initialise the window and database connection
        final DataAccessBase reg = new DataAccessBase("jdbc:mysql://stusql.dcs.shef.ac.uk/team012?user=team012&password=a735fd61");
        setTitle("Sheffield Dental Practice");
        setSize(500,600);

        //Creating labels and text fields
        JLabel title = new JLabel("Enter Holiday Details");
        final JLabel partner = new JLabel("Partner:");
        String[] partners = {"Partner","Dentist","Hygienist"};
        final JComboBox partnerBox = new JComboBox(partners);
        JLabel date = new JLabel("Holiday Date:");

        //comboboxes- start date of holiday
        final JComboBox days = new JComboBox();
        final JComboBox months = new JComboBox();
        final JComboBox years = new JComboBox();

        //Use for loops to create the date options
        days.addItem("Day");
        for(int i=1;i<=31;i++) {
            days.addItem(new Integer(i));
        }

        months.addItem("Month");
        for(int i=1;i<=12;i++) {
            months.addItem(new Integer(i));
        }

        years.addItem("Year");
        for(int i=(int)(Calendar.getInstance().get(Calendar.YEAR));i<=
                (int)(Calendar.getInstance().get(Calendar.YEAR))+1;i++) {
            years.addItem(new Integer(i));
        }

        //making panel for date of holiday
        JPanel datePanel = new JPanel();
        datePanel.add(days);
        datePanel.add(months);
        datePanel.add(years);

        //Set the layout of the datePanel
        datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.X_AXIS));

        //submit button and eventhandler
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //boolean for validation
                        boolean val = true;

                        //checking months and days for consistency eg. No 31st of February
                        if ((months.getSelectedIndex() == 4
                                || months.getSelectedIndex() == 6 || months.getSelectedIndex() == 9
                                || months.getSelectedIndex() == 11) && days.getSelectedIndex() >= 31) {
                            JOptionPane.showMessageDialog(null, "Invalid day selected");
                            val = false;
                        } else if ((months.getSelectedIndex() == 2 && days.getSelectedIndex() >= 30
                                && years.getSelectedIndex() % 4 != 0)
                                || months.getSelectedIndex() == 2 && days.getSelectedIndex() <= 29
                                && years.getSelectedIndex() % 4 == 0) {
                            JOptionPane.showMessageDialog(null, "Invalid day selected");
                            val = false;
                        }
                        //check something is selected in the date combobox
                        String updateAppointments;
                        if (years.getSelectedItem().equals("Year") || (months.getSelectedItem().equals("Month") ||
                                (days.getSelectedItem().equals("Day") || partnerBox.getSelectedItem().equals("Partner")))) {
                            JOptionPane.showMessageDialog(null, "Please fill all fields");
                            val = false;
                            updateAppointments = null;
                        }
                        if (val) {
                            updateAppointments = "UPDATE Appointment SET State = 'Vacation' WHERE (State = 'Active' "
                                    + "And ADate = '" + years.getSelectedItem().toString() + "-" + months.getSelectedItem().toString() + "-"
                                    + days.getSelectedItem().toString() + "' AND Partner = '" + partnerBox.getSelectedItem().toString() + "')";
                            reg.updateData(updateAppointments);
                            String bookVac = "INSERT INTO Appointment VALUES( 1, 'CheckUp', '" + partnerBox.getSelectedItem() + "', '" +
                                    years.getSelectedItem().toString() + "-" + months.getSelectedItem().toString() + "-"
                                    + days.getSelectedItem().toString() + "', '09:00:00', '18:00:00', 'Vacation', 0)";
                            reg.updateData(bookVac);
                            dispose();
                            new SecretaryGUI().SecretaryGUI();
                            }
                        }

              }

        );

        //adding the input fields to the main Panel
        JPanel mPanel = new JPanel();
        mPanel.add(partner);
        mPanel.add(partnerBox);
        mPanel.add(date);
        mPanel.add(datePanel);
        mPanel.add(btnSubmit);

        //Add back button and event listener
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(
                new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            dispose();
                            new SecretaryGUI().SecretaryGUI();
                            }
                    }
        );

        //for adding borders to the components
        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);

        //add elements to the container
        Container contentPane = getContentPane();
        contentPane.add(title, BorderLayout.NORTH);
        contentPane.add(mPanel, BorderLayout.CENTER);
        contentPane.add(btnBack, BorderLayout.SOUTH);

        //setting the border for the inputs
        mPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));

        //Don't forget to pack!
        pack();
        //setting the location in the window and the close operation
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
