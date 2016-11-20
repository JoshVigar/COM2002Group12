import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
/**
 * Created by babatundeadeola on 18/11/2016.
 */
public class ViewAppointment extends JFrame {
    private DataAccessBase view = new DataAccessBase("jdbc:mysql://stusql.dcs.shef.ac.uk/team012?user=team012&password=a735fd61");

    public ViewAppointment() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    public void startDate(String user) {
        final String person = user;
        //Set title and size of frame
        setTitle("Sheffield Dental Practice");
        setSize(1300, 600);

        //get content pane
        Container container = getContentPane();
        //Title of panel
        JLabel title = new JLabel("Select Calendar Start Date");
        //current date
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate);

        //combobox for date
        final JComboBox days = new JComboBox();
        final JComboBox months = new JComboBox();
        final JComboBox years = new JComboBox();
        JButton bSubmit = new JButton("Submit");

        //Use for loops to create the date options
        days.addItem("Day");
        for (int i = 01; i <= 31; i++) {
            days.addItem(new Integer(i));
        }

        months.addItem("Month");
        for (int i = 01; i <= 12; i++) {
            months.addItem(new Integer(i));
        }

        years.addItem("Year");
        for (int i = year; i <= year + 1; i++) {
            years.addItem(new Integer(i));
        }
        //panel to store dates and submit button.
        JPanel dpanel = new JPanel();
        dpanel.add(days);
        dpanel.add(months);
        dpanel.add(years);
        dpanel.add(bSubmit);

        //create a panel set its layout, add a title
        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        mPanel.add(title, BorderLayout.NORTH);
        mPanel.add(dpanel, BorderLayout.CENTER);


        bSubmit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        try {
                            String startDate = years.getSelectedItem() + "-" + months.getSelectedItem() + "-" + days.getSelectedItem();
                            new ViewAppointment().getAppointments(startDate,person);
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        } catch (InstantiationException e1) {
                            e1.printStackTrace();
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
        );
        //create go back button
        JButton btnBack = new JButton("Go Back");
        btnBack.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new WelcomeGUI().WelcomeGUI();
                    }
                }
        );

        int bHeight = (int) (this.getHeight() * 0.05);
        int bWidth = (int) (this.getWidth() * 0.05);
        //add back button to panel
        mPanel.add(btnBack, BorderLayout.SOUTH);
        mPanel.setBorder(BorderFactory.createEmptyBorder(bHeight, bWidth, bHeight, bWidth));
        container.add(mPanel);

        //Don't forget to pack! and setVisible to true
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    //get appointments on a certain date for partners and for a week for secretary
    public void getAppointments(String date, String partner){
        String startDay = date;
        final String user = partner;

        if(!partner.equals("Secretary")){
            //Set title and size of frame
            setTitle("Sheffield Dental Practice");
            setSize(1300, 600);

            //get content pane
            Container container = getContentPane();

            //Title of panel
            JLabel title = new JLabel(user +"Appointments: " + startDay);
            //create string to represent sql query
            String sql = "SELECT ID,TypeOfVisit,StartTime FROM Appointment WHERE ADate = '" + startDay +
                    "' AND Partner = '"+user+"' AND State = 'Active'";
            //store result set of executing Query
            ResultSet rs = view.getData(sql);
            //create a panel set its layout, add a title
            JPanel mPanel = new JPanel();
            mPanel.setLayout(new BorderLayout());
            mPanel.add(title, BorderLayout.NORTH);

            //create a text area to house appointment details
            JTextArea textArea = new JTextArea();
            textArea.setMargin(new Insets(10, 10, 10, 10));
            textArea.setEditable(false);
            JScrollPane areaScrollPane = new JScrollPane(textArea);
            areaScrollPane.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            areaScrollPane.setPreferredSize(new Dimension(300, 300));
            String newLine = "\n";

            try {
                //loop through result set appending each appointment to the textArea
                if (!rs.next()) {
                    textArea.append("No Appointments today");
                } else {
                    while (rs.next()) {
                        int id = rs.getInt("ID");
                        String type = rs.getString("TypeOfVisit");
                        Time startTime = rs.getTime("StartTime");
                        String appoint = "Start Time: " + startTime + " Customer ID: " + id + " Visit Type: " + type;
                        System.out.println(" " + appoint);
                        textArea.append(appoint + newLine);
                    }
                }
                //add textArea to the panel
                mPanel.add(textArea, BorderLayout.CENTER);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //create go back button
            JButton btnBack = new JButton("Go Back");
            btnBack.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                            if(user.equals("Dentist")) {
                                new DentistGUI().DentistGUI();
                            }else if(user.equals("Hygienist")){
                                new HygienistGUI().HygienistGUI();
                            }
                        }
                    }
            );

            int bHeight = (int) (this.getHeight() * 0.05);
            int bWidth = (int) (this.getWidth() * 0.05);
            //add back button to panel
            mPanel.add(btnBack, BorderLayout.SOUTH);
            mPanel.setBorder(BorderFactory.createEmptyBorder(bHeight, bWidth, bHeight, bWidth));
            container.add(mPanel);

            //Don't forget to pack! and setVisible to true
            pack();
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);

        }else{
            //create a tabbed pane
            JTabbedPane tabbedPane = new JTabbedPane();
            LocalDate appointDate;
            //start date and end date of calendar
            if(startDay.length()<10){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
                appointDate = LocalDate.parse(startDay,formatter);
            }else{
                appointDate = LocalDate.parse(startDay);
            }
            String endDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(appointDate.plusWeeks(1));

            JLabel denTitle = new JLabel("Dentist Weekly Calendar from: " + startDay);
            JLabel hygTitle = new JLabel("Hygienist Weekly Calendar from: " + startDay);

            String sql = "SELECT ID,TypeOfVisit,Partner,ADate,StartTime FROM Appointment WHERE ADate BETWEEN '" +
                    startDay + "' AND '" + endDate + "' AND State = 'Active'";

            //store result set of executing Query
            ResultSet rs = view.getData(sql);

            //dentist panel
            //JPanel dPanel = new JPanel();
            //hygienist panel
            //JPanel hPanel = new JPanel();

            //create a text area to house appointment details
            //dentist text area
            JTextArea textArea = new JTextArea();
            textArea.setMargin(new Insets(10, 10, 10, 10));
            textArea.setEditable(false);
            JScrollPane areaScrollPane = new JScrollPane(textArea);
            areaScrollPane.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            areaScrollPane.setPreferredSize(new Dimension(300, 300));
            String newLine = "\n";

            //hygienist text area
            JTextArea textArea1 = new JTextArea();
            textArea1.setMargin(new Insets(10, 10, 10, 10));
            textArea1.setEditable(false);
            JScrollPane areaScrollPane1 = new JScrollPane(textArea1);
            areaScrollPane.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            areaScrollPane1.setPreferredSize(new Dimension(300, 300));

            try {
                //loop through result set appending each appointment to the textArea
                if (!rs.next()) {
                    textArea.append("No Appointments this Week");
                    textArea1.append("No Appointments this Week");
                } else {
                    while (rs.next()) {
                        int id = rs.getInt("ID");
                        String type = rs.getString("TypeOfVisit");
                        String partners = rs.getString("Partner");
                        Date appDay = rs.getDate("ADate");
                        Time startTime = rs.getTime("StartTime");
                        String appoint = "Date: "+appDay+" Start Time: " + startTime + " Customer ID: " + id +
                                " Visit Type: " + type;
                        System.out.println(" " + appoint);
                        if(partners.equals("Dentist")) {
                            textArea.append(appoint + newLine);
                        }else if(partners.equals("Hygienist")){
                            textArea1.append(appoint + newLine);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //back button
            JButton btnBackSec = new JButton("Go Back");
            btnBackSec.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                            new SecretaryGUI().SecretaryGUI();
                        }
                    }
            );
            //go back button
            JButton btnBackHyg = new JButton("Go Back");
            btnBackHyg.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                            new SecretaryGUI().SecretaryGUI();
                        }
                    }
            );
            //add details to the dentist panel
            JPanel dentist = new JPanel();
            dentist.add(denTitle, BorderLayout.NORTH);
            dentist.add(textArea, BorderLayout.CENTER);
            dentist.add(btnBackSec, BorderLayout.SOUTH);

            tabbedPane.addTab("Dentist Appointments", null, dentist, null);

            //add details to the hygienist panel
            JPanel hygienist = new JPanel();
            hygienist.add(hygTitle, BorderLayout.NORTH);
            hygienist.add(textArea1, BorderLayout.CENTER);
            hygienist.add(btnBackHyg, BorderLayout.SOUTH);

            tabbedPane.addTab("Hygienist Appointments", null, hygienist, null);

            JFrame frame = new JFrame("TabbedPaneDemo");

            //Add content to the window.
            add(tabbedPane, BorderLayout.CENTER);

            //Display the window.
            pack();
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setVisible(true);
        }
    }

    //get a single active appointment
    public void getAppointment(String patient, String partners, String time, String date){
        final String patientID = patient;
        final String partner = partners;
        final String appTime = time;
        final String appDate = date;
        String patientName ="";

        //Set title and size of frame
        setTitle("Sheffield Dental Practice");
        setSize(1300, 600);

        //get content pane
        Container container = getContentPane();

        //get the patients first name and last name
        String sql = "SELECT FName, LName FROM Customer WHERE ID ="+Integer.parseInt(patientID);
        ResultSet rs = view.getData(sql);

        try {
            //loop through result set appending each appointment to the textArea
            while (rs.next()) {
                String fName = rs.getString("FName");
                String lName = rs.getString("LName");
                patientName = fName+" "+lName;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Title of panel
        JLabel title = new JLabel("View single appointment");
        //create a panel set its layout, add a title
        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        mPanel.add(title, BorderLayout.NORTH);

        //panel to house text area and cancel appointment button
        JPanel cancel = new JPanel();
        JLabel title1 = new JLabel(patientName +"'s appointment on " + appDate);
        cancel.setLayout(new BorderLayout());

        //create a text area to house appointment details
        JTextArea textArea = new JTextArea();
        textArea.setMargin(new Insets(10, 10, 10, 10));
        textArea.setEditable(false);
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(300, 300));
        String newLine = "\n";

        //cancel Appointment button
        JButton cancelled = new JButton("Cancel Appointment");
        cancelled.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        view.updateData("UPDATE Appointment SET State = 'Cancelled' WHERE ID = '"+patientID+"' AND ADate = '" + appDate +
                                "' AND Partner = '"+partner+"' AND StartTime = '"+appTime+"'");
                        dispose();
                        JOptionPane.showMessageDialog(null, "Appointment Cancelled");
                        new ManageAppointments().ManageAppointments();
                    }
                }
        );

        //create string to represent sql query
        sql = "SELECT * FROM Appointment WHERE ID = '"+patientID+"' AND ADate = '" + appDate +
                "' AND Partner = '"+partner+"' AND State = 'Active' AND StartTime = '"+appTime+"'";
        rs = view.getData(sql);

        try {
            //loop through result set appending each appointment to the textArea
            if (!rs.next()) {
                textArea.append("Appointment doesnt't exist");
            } else {
                //int id = rs.getInt("ID");
                String type = rs.getString("TypeOfVisit");
                Date dates = rs.getDate("ADate");
                Time startTime = rs.getTime("StartTime");
                Time endTime = rs.getTime("EndTime");
                String appoint = " Customer: " + patientName + " Date: "+dates+" Start Time: " + startTime +
                        " End Time: "+ endTime+ " Visit Type: " + type;
                //System.out.println(" " + appoint);
                textArea.append(appoint + newLine);

                //add cancelled button to panel
                cancel.add(cancelled, BorderLayout.SOUTH);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        cancel.add(title1, BorderLayout.NORTH);
        cancel.add(textArea, BorderLayout.CENTER);

        //add cancel panel and back button to mpanel
        mPanel.add(cancel, BorderLayout.CENTER);

        //go back button
        JButton btnBackMan = new JButton("Go Back");
        btnBackMan.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new ManageAppointments().ManageAppointments();
                    }
                }
        );

        int bHeight = (int) (this.getHeight() * 0.05);
        int bWidth = (int) (this.getWidth() * 0.05);
        mPanel.add(btnBackMan, BorderLayout.SOUTH);
        mPanel.setBorder(BorderFactory.createEmptyBorder(bHeight, bWidth, bHeight, bWidth));
        container.add(mPanel);

        //Don't forget to pack! and setVisible to true
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
