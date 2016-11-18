import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

/**
 * Created by User on 18/11/2016.
 */
public class ManageAppointments extends JFrame{


    public void ManageAppointments(){
        //setting initial window settings
        setTitle("Sheffield Dental Practice");
        setSize(500,600);

        JLabel title = new JLabel("Choose Details for a Single Appoitment");

        JLabel pID = new JLabel("PatientID:");
        final JTextField txtPID = new JTextField(20);

        JLabel partner = new JLabel("Partner:");
        String[] partners = {"Dentist","Hygienist"};
        final JComboBox Partner = new JComboBox(partners);

        JPanel pPanel = new JPanel();
        pPanel.add(pID);
        pPanel.add(txtPID);

        JPanel partPanel = new JPanel();
        partPanel.add(partner);
        partPanel.add(Partner);

        JLabel sTime = new JLabel("Appointment Start Time:");
        final String[] hour = {"Hour","09","10","11","12","14","15","16"};
        String[] minute = {"Minute","00","20","40"};
        final JComboBox hr = new JComboBox(hour);
        final JComboBox min = new JComboBox(minute);

        JPanel timePanel = new JPanel();
        timePanel.add(sTime);
        timePanel.add(hr);
        timePanel.add(min);

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
        for(int i = (int)(Calendar.getInstance().get(Calendar.YEAR)); i<=
                (int)(Calendar.getInstance().get(Calendar.YEAR))+1; i++) {
            years.addItem(new Integer(i));
        }

        //create a panel and add date comboBox
        JPanel dPanel = new JPanel();
        dPanel.add(date);
        dPanel.add(days);
        dPanel.add(months);
        dPanel.add(years);

        JButton bSubmit = new JButton("Submit");
        bSubmit.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        //View Particular appointment
                        //Here's an array of strings of all the data
                        String[] appointmentData = {txtPID.getText(),Partner.getSelectedItem().toString(),
                                hr.getSelectedItem().toString()+":"+min.getSelectedItem().toString(),
                                years.getSelectedItem().toString() + "-" + months.getSelectedItem().toString() + "-" + days.getSelectedItem().toString()} ;
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

        JPanel subPanel = new JPanel();
        subPanel.add(pPanel);
        subPanel.add(partPanel);
        subPanel.add(timePanel);
        subPanel.add(dPanel);
        subPanel.add(bSubmit);

        subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));

        //create a panel for the appointment search
        JPanel sePanel = new JPanel();
        sePanel.setLayout(new BorderLayout());
        sePanel.add(title, BorderLayout.NORTH);
        sePanel.add(subPanel, BorderLayout.CENTER);

        int bHeight = (int) (this.getHeight() * 0.05);
        int bWidth = (int) (this.getWidth() * 0.05);

        //get content pane
        Container container = getContentPane();
        container.add(sePanel);
        container.add(btnBack, BorderLayout.SOUTH);

        sePanel.setBorder(BorderFactory.createEmptyBorder(bHeight, bWidth, bHeight, bWidth));

        //Don't forget to pack! and setVisible to true
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
