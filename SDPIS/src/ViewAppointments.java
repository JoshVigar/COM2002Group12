import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Created by jodi on 16/11/2016.
 */
public class ViewAppointments extends JFrame {

    public void ViewDentistAppointments() {
        setTitle("Sheffield Dental Practice");
        setSize(1300,600);

        Container container = getContentPane();

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

        JLabel title = new JLabel("Dentist Appointments Calendar: Week of...");
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

        //int bHeight = (int)(this.getHeight()*0.1);
        //int bWidth = (int)(this.getWidth()*0.1);

        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        mPanel.add(title, BorderLayout.NORTH);
        mPanel.add(denPanel, BorderLayout.CENTER);
        //mPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));
        container.add(mPanel);

        //Don't forget to pack!
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void ViewHygienistAppointments() {
        setTitle("Sheffield Dental Practice");
        setSize(1300,600);

        Container container = getContentPane();

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

        JLabel title = new JLabel("Hygienist Appointments Calendar: Week of...");
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

        //int bHeight = (int)(this.getHeight()*0.1);
        //int bWidth = (int)(this.getWidth()*0.1);

        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        mPanel.add(title, BorderLayout.NORTH);
        mPanel.add(hygPanel, BorderLayout.CENTER);
        //mPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));
        container.add(mPanel);

        //Don't forget to pack!
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args){
        ViewAppointments appointments = new ViewAppointments();
        appointments.ViewDentistAppointments();

    }
}
