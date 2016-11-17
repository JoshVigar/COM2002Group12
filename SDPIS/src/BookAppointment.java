import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Created by User on 16/11/2016.
 */
public class BookAppointment extends JFrame{
    public void BookAppointment(){

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
        String[] hour = {"Hour","09","10","11","12","14","15","16","17"};
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
        for(int i=2015;i<=2017;i++) {
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

        //create a panel and add D.O.B comboBox
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
                        String[] newBooking = {txtPID.getText(), (String)Partner.getSelectedItem(),
                                (String)hr.getSelectedItem()+":"+(String)min.getSelectedItem(),
                                (String)aType.getSelectedItem()};

                        System.out.print(Arrays.toString(newBooking));

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
        contentpane.add(mPanel, BorderLayout.CENTER);
        contentpane.add(btnBack, BorderLayout.SOUTH);


        mPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));

        //Don't forget to pack!
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
