import java.awt.*;
import java.awt.event.KeyEvent;
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
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created by jodi on 16/11/2016.
 */
public class ViewAppointments extends JFrame {

    DataAccessBase view = new DataAccessBase("jdbc:mysql://stusql.dcs.shef.ac.uk/team012?user=team012&password=a735fd61");

    public ViewAppointments() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    public JPanel HygienistCalendar() {
        String[] columnNames  = {"Time","Appointment"};

        Object[][] monData = {
                {"9:00", ""},{"9:20", ""}, {"9:40", ""},
                {"10.00", ""},{"10:20", ""}, {"10:40", ""},
                {"11:00", ""},{"11:20", ""}, {"11:40", ""},
                {"12:00", ""},{"12:20", ""}, {"12:40", ""},
                {"13:00", ""},{"13:20", ""}, {"13:40", ""},
                {"14:00", ""},{"14:20", ""}, {"14:40", ""},
                {"15:00", ""},{"15:20", ""}, {"15:40", ""},
                {"16:00", ""},{"16:20", ""}, {"16:40", ""},
        };
        Object[][] tueData = {
                {"9:00", ""},{"9:20", ""}, {"9:40", ""},
                {"10.00", ""},{"10:20", ""}, {"10:40", ""},
                {"11:00", ""},{"11:20", ""}, {"11:40", ""},
                {"12:00", ""},{"12:20", ""}, {"12:40", ""},
                {"13:00", ""},{"13:20", ""}, {"13:40", ""},
                {"14:00", ""},{"14:20", ""}, {"14:40", ""},
                {"15:00", ""},{"15:20", ""}, {"15:40", ""},
                {"16:00", ""},{"16:20", ""}, {"16:40", ""},
        };
        Object[][] wedData = {
                {"9:00", ""},{"9:20", ""}, {"9:40", ""},
                {"10.00", ""},{"10:20", ""}, {"10:40", ""},
                {"11:00", ""},{"11:20", ""}, {"11:40", ""},
                {"12:00", ""},{"12:20", ""}, {"12:40", ""},
                {"13:00", ""},{"13:20", ""}, {"13:40", ""},
                {"14:00", ""},{"14:20", ""}, {"14:40", ""},
                {"15:00", ""},{"15:20", ""}, {"15:40", ""},
                {"16:00", ""},{"16:20", ""}, {"16:40", ""},
        };
        Object[][] thurData = {
                {"9:00", ""},{"9:20", ""}, {"9:40", ""},
                {"10.00", ""},{"10:20", ""}, {"10:40", ""},
                {"11:00", ""},{"11:20", ""}, {"11:40", ""},
                {"12:00", ""},{"12:20", ""}, {"12:40", ""},
                {"13:00", ""},{"13:20", ""}, {"13:40", ""},
                {"14:00", ""},{"14:20", ""}, {"14:40", ""},
                {"15:00", ""},{"15:20", ""}, {"15:40", ""},
                {"16:00", ""},{"16:20", ""}, {"16:40", ""},
        };
        Object[][] friData = {
                {"9:00", ""},{"9:20", ""}, {"9:40", ""},
                {"10.00", ""},{"10:20", ""}, {"10:40", ""},
                {"11:00", ""},{"11:20", ""}, {"11:40", ""},
                {"12:00", ""},{"12:20", ""}, {"12:40", ""},
                {"13:00", ""},{"13:20", ""}, {"13:40", ""},
                {"14:00", ""},{"14:20", ""}, {"14:40", ""},
                {"15:00", ""},{"15:20", ""}, {"15:40", ""},
                {"16:00", ""},{"16:20", ""}, {"16:40", ""},
        };

        JTable monCalendar = new JTable(monData,columnNames);
        JTable tueCalendar = new JTable(tueData,columnNames);
        JTable wedCalendar = new JTable(wedData,columnNames);
        JTable thurCalendar = new JTable(thurData,columnNames);
        JTable friCalendar = new JTable(friData,columnNames);

        JLabel monTitle = new JLabel("Monday:");
        JLabel tueTitle = new JLabel("Tuesday:");
        JLabel wedTitle = new JLabel("Wednesday:");
        JLabel thurTitle = new JLabel("Thursday:");
        JLabel friTitle = new JLabel("Friday:");

        JPanel hygPanel = new JPanel();
        hygPanel.add(monTitle);
        hygPanel.add(monCalendar);
        hygPanel.add(tueTitle);
        hygPanel.add(tueCalendar);
        hygPanel.add(wedTitle);
        hygPanel.add(wedCalendar);
        hygPanel.add(thurTitle);
        hygPanel.add(thurCalendar);
        hygPanel.add(friTitle);
        hygPanel.add(friCalendar);

        return hygPanel;

    }

    public JPanel DentistCalendar() {
        String[] columnNames  = {"Time","Appointment"};

        Object[][] monData = {
                {"9:00", ""},{"9:20", ""}, {"9:40", ""},
                {"10.00", ""},{"10:20", ""}, {"10:40", ""},
                {"11:00", ""},{"11:20", ""}, {"11:40", ""},
                {"12:00", ""},{"12:20", ""}, {"12:40", ""},
                {"13:00", ""},{"13:20", ""}, {"13:40", ""},
                {"14:00", ""},{"14:20", ""}, {"14:40", ""},
                {"15:00", ""},{"15:20", ""}, {"15:40", ""},
                {"16:00", ""},{"16:20", ""}, {"16:40", ""},
        };
        Object[][] tueData = {
                {"9:00", ""},{"9:20", ""}, {"9:40", ""},
                {"10.00", ""},{"10:20", ""}, {"10:40", ""},
                {"11:00", ""},{"11:20", ""}, {"11:40", ""},
                {"12:00", ""},{"12:20", ""}, {"12:40", ""},
                {"13:00", ""},{"13:20", ""}, {"13:40", ""},
                {"14:00", ""},{"14:20", ""}, {"14:40", ""},
                {"15:00", ""},{"15:20", ""}, {"15:40", ""},
                {"16:00", ""},{"16:20", ""}, {"16:40", ""},
        };
        Object[][] wedData = {
                {"9:00", ""},{"9:20", ""}, {"9:40", ""},
                {"10.00", ""},{"10:20", ""}, {"10:40", ""},
                {"11:00", ""},{"11:20", ""}, {"11:40", ""},
                {"12:00", ""},{"12:20", ""}, {"12:40", ""},
                {"13:00", ""},{"13:20", ""}, {"13:40", ""},
                {"14:00", ""},{"14:20", ""}, {"14:40", ""},
                {"15:00", ""},{"15:20", ""}, {"15:40", ""},
                {"16:00", ""},{"16:20", ""}, {"16:40", ""},
        };
        Object[][] thurData = {
                {"9:00", ""},{"9:20", ""}, {"9:40", ""},
                {"10.00", ""},{"10:20", ""}, {"10:40", ""},
                {"11:00", ""},{"11:20", ""}, {"11:40", ""},
                {"12:00", ""},{"12:20", ""}, {"12:40", ""},
                {"13:00", ""},{"13:20", ""}, {"13:40", ""},
                {"14:00", ""},{"14:20", ""}, {"14:40", ""},
                {"15:00", ""},{"15:20", ""}, {"15:40", ""},
                {"16:00", ""},{"16:20", ""}, {"16:40", ""},
        };
        Object[][] friData = {
                {"9:00", ""},{"9:20", ""}, {"9:40", ""},
                {"10.00", ""},{"10:20", ""}, {"10:40", ""},
                {"11:00", ""},{"11:20", ""}, {"11:40", ""},
                {"12:00", ""},{"12:20", ""}, {"12:40", ""},
                {"13:00", ""},{"13:20", ""}, {"13:40", ""},
                {"14:00", ""},{"14:20", ""}, {"14:40", ""},
                {"15:00", ""},{"15:20", ""}, {"15:40", ""},
                {"16:00", ""},{"16:20", ""}, {"16:40", ""},
        };

        JTable monCalendar = new JTable(monData,columnNames);
        JTable tueCalendar = new JTable(tueData,columnNames);
        JTable wedCalendar = new JTable(wedData,columnNames);
        JTable thurCalendar = new JTable(thurData,columnNames);
        JTable friCalendar = new JTable(friData,columnNames);

        JLabel monTitle = new JLabel("Monday:");
        JLabel tueTitle = new JLabel("Tuesday:");
        JLabel wedTitle = new JLabel("Wednesday:");
        JLabel thurTitle = new JLabel("Thursday:");
        JLabel friTitle = new JLabel("Friday:");

        JPanel denPanel = new JPanel();
        denPanel.add(monTitle);
        denPanel.add(monCalendar);
        denPanel.add(tueTitle);
        denPanel.add(tueCalendar);
        denPanel.add(wedTitle);
        denPanel.add(wedCalendar);
        denPanel.add(thurTitle);
        denPanel.add(thurCalendar);
        denPanel.add(friTitle);
        denPanel.add(friCalendar);

        return denPanel;
    }

    public void ViewDentistAppointments() {
        //Set title and size of frame
        setTitle("Sheffield Dental Practice");
        setSize(1300,600);

        //get content pane
        Container container = getContentPane();

        //get current date and store it as a string
        LocalDate localDate = LocalDate.now();
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate);

        //Title of panel
        JLabel title = new JLabel("Dentist Appointments: " + date);
        //create string to represent sql query
        String sql = "SELECT ID,TypeOfVisit,StartTime FROM Appointment WHERE ADate = '"+ date +"' AND Partner = 'Dentist' AND State = 'Active'";
        //store result set of executing Query
        ResultSet rs = view.getData(sql);

        //create a panel set its layout, add a title
        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        mPanel.add(title, BorderLayout.NORTH);

        //create a text area to house appointment details
        JTextArea textArea = new JTextArea();
        textArea.setMargin(new Insets(10,10,10,10));
        textArea.setEditable(false);
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(300, 300));
        String newLine = "\n";

        try {
            //loop through result set appending each appointment to the textArea
            if (!rs.next()){
                textArea.append("No Appointments today");
            }else {
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
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        new DentistGUI().DentistGUI();
                    }
                }
        );

        int bHeight = (int)(this.getHeight()*0.05);
        int bWidth = (int)(this.getWidth()*0.05);
        //add back button to panel
        mPanel.add(btnBack, BorderLayout.SOUTH);
        mPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));
        container.add(mPanel);

        //Don't forget to pack! and setVisible to true
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void ViewHygienistAppointments() {
        //Set title and size of frame
        setTitle("Sheffield Dental Practice");
        setSize(1300,600);

        //get content pane
        Container container = getContentPane();

        //get current date and store it as a string
        LocalDate localDate = LocalDate.now();
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate);

        //Title of panel
        JLabel title = new JLabel("Hygienist Appointments: "+localDate);
        //create string to represent sql query
        String sql = "SELECT ID,TypeOfVisit,StartTime FROM Appointment WHERE ADate = '"+date+"' AND Partner = 'Hygienist' AND State = 'Active'";
        //store result set of executing Query
        ResultSet rs = view.getData(sql);

        //create a panel set its layout, add a title
        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        mPanel.add(title, BorderLayout.NORTH);

        //create a text area to house appointment details
        JTextArea textArea = new JTextArea();
        textArea.setMargin(new Insets(10,10,10,10));
        textArea.setEditable(false);
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(300, 300));
        String newLine = "\n";

        //loop through result set appending each appointment to the textArea
        try {
            if (!rs.next()){
                textArea.append("No Appointments today");
            }else {
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
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        new HygienistGUI().HygienistGUI();
                    }
                }
        );

        int bHeight = (int)(this.getHeight()*0.05);
        int bWidth = (int)(this.getWidth()*0.05);
        //add back button to panel
        mPanel.add(btnBack, BorderLayout.SOUTH);
        mPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));
        container.add(mPanel);

        //Don't forget to pack! and setVisible to true
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void ViewSecretaryAppointments(){
        setTitle("Sheffield Dental Practice");
        JTabbedPane tabbedPane = new JTabbedPane();

        JLabel denTitle = new JLabel("Dentist Weekly Calendar for Week ...");
        JLabel hygTitle = new JLabel("Hygienist Weekly Calendar for Week ...");

        JButton btnBackDen = new JButton("Go Back");
        btnBackDen.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        new SecretaryGUI().SecretaryGUI();
                    }
                }
        );

        JButton btnBackHyg = new JButton("Go Back");
        btnBackHyg.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        new SecretaryGUI().SecretaryGUI();
                    }
                }
        );

        JPanel dentist = new JPanel();
        dentist.add(denTitle,BorderLayout.NORTH);
        dentist.add(DentistCalendar(),BorderLayout.CENTER);
        dentist.add(btnBackDen,BorderLayout.SOUTH);

        tabbedPane.addTab("Dentist Appointments",null,dentist, null);

        JPanel hygienist = new JPanel();
        hygienist.add(hygTitle,BorderLayout.NORTH);
        hygienist.add(HygienistCalendar(),BorderLayout.CENTER);
        hygienist.add(btnBackHyg,BorderLayout.SOUTH);

        tabbedPane.addTab("Hygienist Appointments", null,hygienist, null);

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
