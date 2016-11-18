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
    DataAccessBase view = new DataAccessBase("jdbc:mysql://stusql.dcs.shef.ac.uk/team012?user=team012&password=a735fd61");

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
        for (int i = 1; i <= 31; i++) {
            days.addItem(new Integer(i));
        }

        months.addItem("Month");
        for (int i = 1; i <= 12; i++) {
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

    public void getAppointments(String date, String partner){
        String startDay = date;
        String user = partner;

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
        }else{
            LocalDate appointDate = LocalDate.parse(startDay);
            String endDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(appointDate.plusWeeks(1));
        }
    }
}
