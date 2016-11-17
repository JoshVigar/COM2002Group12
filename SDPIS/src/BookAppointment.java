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

        JLabel title = new JLabel("Enter Appointment Details");
        JLabel pID = new JLabel("PatientID:");
        JLabel partner = new JLabel("Partner:");
        JLabel tType = new JLabel("Treatment Type:");
        final JTextField txtPID = new JTextField(20);
        String[] partners = {"Dentist","Hygienist"};
        final JComboBox Partner = new JComboBox(partners);
        JLabel sTime = new JLabel("Appointment Start Time:");
        final String[] hour = {"Hour","09","10","11","12","14","15","16","17"};
        String[] minute = {"Minute","00","20","40"};
        final JComboBox hr = new JComboBox(hour);
        final JComboBox min = new JComboBox(minute);
        String[] appTypes = { "CheckUp", "HygieneVisit" , "White Composite Resin Filling", "Gold Crown", "Silver Amalgam Filling"};
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

        JPanel timePanel = new JPanel();
        timePanel.add(sTime);
        timePanel.add(hr);
        timePanel.add(min);

        JPanel inputsPanel = new JPanel();
        inputsPanel.add(pID);
        inputsPanel.add(txtPID);
        inputsPanel.add(partner);
        inputsPanel.add(Partner);
        inputsPanel.add(timePanel);
        inputsPanel.add(tType);
        inputsPanel.add(aType);
        inputsPanel.setLayout(new BoxLayout(inputsPanel, BoxLayout.Y_AXIS));

        //create a panel and add date comboBox
        JPanel dPanel = new JPanel();
        dPanel.add(date);
        dPanel.add(days);
        dPanel.add(months);
        dPanel.add(years);

        JPanel mPanel = new JPanel();
        mPanel.add(inputsPanel);
        mPanel.add(dPanel);
        mPanel.add(bBook);
        mPanel.setLayout(new BoxLayout(mPanel, BoxLayout.Y_AXIS));


        bBook.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        /*String[] newBooking = {txtPID.getText(), (String)Partner.getSelectedItem(),
                                (String)hr.getSelectedItem()+":"+(String)min.getSelectedItem(),
                                (String)aType.getSelectedItem()};*/

                        String getVisitDuration = "SELECT Duration FROM VisitType Where TypeOfVisit='"
                                + (String)aType.getSelectedItem() +"'";
                        int dur = 0;
                        ResultSet rs = reg.getData(getVisitDuration);
                        try {
                            while(rs.next()) {
                                dur = rs.getInt("Duration");
                            }
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }

                        int minutes = Integer.parseInt(min.getSelectedItem().toString());
                        int hours = Integer.parseInt(hr.getSelectedItem().toString());

                        if(dur + minutes>=60){
                            minutes = minutes +dur -60;
                            hours += 1;
                        }else {
                            minutes += dur;
                        }

                        int startH, endH,startM, endM;
                        boolean validateBooking = true;
                        String getOtherAppointments = "SELECT * FROM Appointment WHERE ADate = '"
                                + years.getSelectedItem().toString() + "-" + months.getSelectedItem().toString() + "-"
                                + days.getSelectedItem().toString()+"'";
                        ResultSet appointmentDT = reg.getData(getOtherAppointments);
                        try {
                            while(appointmentDT.next()){
                                startH = Integer.parseInt(appointmentDT.getTime("StartTime").toString().substring(0,1));
                                startM = Integer.parseInt(appointmentDT.getTime("StartTime").toString().substring(3,4));
                                endH = Integer.parseInt(appointmentDT.getTime("EndTime").toString().substring(0,1));
                                endM = Integer.parseInt(appointmentDT.getTime("EndTime").toString().substring(3,4));

                                if(startH*60+startM+dur>=endH*60+endM){
                                    validateBooking = false;
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

                        if(validateBooking == true) {
                            String newBooking = "INSERT INTO Appointment VALUES(" + txtPID.getText() + ", '" +
                                    (String) aType.getSelectedItem() + "', '" + (String) Partner.getSelectedItem() + "', '" +
                                    years.getSelectedItem().toString() + "-" + months.getSelectedItem().toString() + "-" +
                                    days.getSelectedItem().toString() + "', '"+  hr.getSelectedItem().toString() + ":"
                                    + min.getSelectedItem().toString() + ":00', '"
                                    + Integer.toString(hours) + ":" + Integer.toString(minutes) + ":00', 'Active')";
                            reg.updateData(newBooking);
                        }
                    }
                }
        );

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        new SecretaryGUI().SecretaryGUI();
                    }
                }
        );

        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);

        Container contentPane = getContentPane();
        contentPane.add(title, BorderLayout.NORTH);
        contentPane.add(mPanel, BorderLayout.CENTER);
        contentPane.add(btnBack, BorderLayout.SOUTH);


        mPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));

        //Don't forget to pack!
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void CancelAppointment(){

        setTitle("Sheffield Dental Practice");
        setSize(500,600);

        JLabel title = new JLabel("Enter Appointment Details");
        JLabel pID = new JLabel("PatientID:");
        final JTextField txtPID = new JTextField(20);

        JLabel sTime = new JLabel("Appointment Start Time:");
        String[] hour = {"Hour","09","10","11","12","14","15","16","17"};
        String[] minute = {"Minute","00","20","40"};
        final JComboBox hr = new JComboBox(hour);
        final JComboBox min = new JComboBox(minute);

        //comboboxes date of appointment
        JLabel date = new JLabel("Date of Appointment:");
        final JComboBox days = new JComboBox();
        final JComboBox months = new JComboBox();
        final JComboBox years = new JComboBox();
        JButton bSubmit = new JButton();

        JPanel timePanel = new JPanel();
        timePanel.add(sTime);
        timePanel.add(hr);
        timePanel.add(min);

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
        for(int i = 2015; i<=(int)(Calendar.getInstance().get(Calendar.YEAR)); i++) {
            years.addItem(new Integer(i));
        }

        //create a panel and add date comboBox
        JPanel dPanel = new JPanel();
        dPanel.add(date);
        dPanel.add(days);
        dPanel.add(months);
        dPanel.add(years);

        JPanel inputsPanel = new JPanel();
        inputsPanel.add(pID);
        inputsPanel.add(txtPID);
        inputsPanel.add(timePanel);
        inputsPanel.add(dPanel);
        inputsPanel.add(bSubmit);


        inputsPanel.setLayout(new BoxLayout(inputsPanel, BoxLayout.Y_AXIS));


        bSubmit.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        String[] managedPatient = {txtPID.getText(),(String)hr.getSelectedItem()+":"+(String)min.getSelectedItem(),
                        years.getSelectedItem() + "-" + months.getSelectedItem() + "-" + days.getSelectedItem()};

                        System.out.print(Arrays.toString(managedPatient));

                    }
                }
        );

        JButton btnBack = new JButton("Go Back");
        btnBack.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        new SecretaryGUI().SecretaryGUI();
                    }
                }
        );

        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);

        Container contentpane = getContentPane();
        contentpane.add(title, BorderLayout.NORTH);
        contentpane.add(inputsPanel, BorderLayout.CENTER);
        contentpane.add(btnBack, BorderLayout.SOUTH);


        inputsPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));

        //Don't forget to pack!
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
