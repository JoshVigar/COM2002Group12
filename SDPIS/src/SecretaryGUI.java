import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Created by User on 14/11/2016.
 */
public class SecretaryGUI extends JFrame{

    public void SecretaryGUI(){

        //Set title and size of Secretary frame
        setTitle("Sheffield Dental Practice");
        setSize(500,600);

        //create view appointments calendar button and add its actionListener
        JButton btnApp = new JButton("View Appointments Calendar");
        btnApp.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        //open calendar
                    }
                }
        );

        //Create check Out patient button and add its action Listener
        JButton btnLog = new JButton("Check Out Patient");
        btnLog.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        //open calendar
                    }
                }
        );

        //Create Register Patient button and add its action listener
        JButton btnReg = new JButton("Register Patient");
        btnReg.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        new RegistrationPage().RegisterPage();
                    }
                }
        );

        //Create Manage Patients button and add its action listener
        JButton btnMP = new JButton("Manage Patients");
        btnMP.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        //View/edit patients outstanding balance
                        //View treatment record
                        //
                    }
                }
        );

        //Create Book Appointments button and add its action listener
        JButton btnBA = new JButton("Book Appointments");
        btnBA.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        new SecretaryGUI().BookAppointment();
                    }
                }
        );

        //create a buttonPanel and add all buttons to it
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.add(btnApp);
        buttonPanel.add(btnLog);
        buttonPanel.add(btnReg);
        buttonPanel.add(btnMP);
        buttonPanel.add(btnBA);

        JLabel title = new JLabel("Secretary View");

        //create ints to represent borders for contents of the panel
        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);
        //get the contentPane add the title and buttonPanel.
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(title, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));


        //Don't forget to pack! and setVisible to true
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void BookAppointment(){

        //Set title and size of Secretary frame
        setTitle("Sheffield Dental Practice");
        setSize(500,600);

        JLabel title = new JLabel("Enter Appointment Details");
        JLabel pID = new JLabel("PatientID:");
        JLabel partner = new JLabel("Partner:");
        JLabel sTime = new JLabel("Start Time:");
        JLabel tType = new JLabel("Treatment Type:");
        final JTextField txtPID = new JTextField(20);
        String[] partners = {"Dentist","Hygienist"};
        final JComboBox Partner = new JComboBox(partners);
        final JTextField txtStart = new JTextField(20);
        String[] appTypes = { "Checkup", "Teeth Cleaning", "Composite Resin Filling", "Gold Crown", "Amalgam Filling"};
        final JComboBox aType = new JComboBox(appTypes);
        JButton bBook = new JButton("Book");

        JPanel inputsPanel = new JPanel();
        inputsPanel.add(pID);
        inputsPanel.add(txtPID);
        inputsPanel.add(partner);
        inputsPanel.add(Partner);
        inputsPanel.add(sTime);
        inputsPanel.add(txtStart);
        inputsPanel.add(tType);
        inputsPanel.add(aType);
        inputsPanel.add(bBook);
        inputsPanel.setLayout(new BoxLayout(inputsPanel, BoxLayout.Y_AXIS));


        bBook.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        String[] newBooking = {txtPID.getText(), (String)Partner.getSelectedItem(), txtStart.getText(),
                                (String)aType.getSelectedItem()};

                        System.out.print(Arrays.toString(newBooking));

                    }
                }
        );

        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);

        Container contentpane = getContentPane();
        contentpane.add(title, BorderLayout.NORTH);
        contentpane.add(inputsPanel, BorderLayout.SOUTH);

        inputsPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));

        //Don't forget to pack!
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
