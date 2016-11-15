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

        setTitle("Sheffield Dental Practice");
        setSize(500,600);

        JButton btnApp = new JButton("View Appointments Calendar");
        btnApp.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        //open calendar
                    }
                }
        );

        JButton btnReg = new JButton("Register Patient");
        btnReg.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        new RegistrationPage().RegisterPage();
                    }
                }
        );

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

        JButton btnBA = new JButton("Book Appointments");
        btnBA.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        new SecretaryGUI().BookAppointment();
                    }
                }
        );

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.add(btnApp);
        buttonPanel.add(btnReg);
        buttonPanel.add(btnMP);
        buttonPanel.add(btnBA);

        JButton btnBack = new JButton("Go Back");
        btnBack.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        new WelcomeGUI().WelcomeGUI();
                    }
                }
        );

        JLabel title = new JLabel("Secretary View");

        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(title, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        contentPane.add(btnBack,BorderLayout.SOUTH);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));


        //Don't forget to pack!
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }



    public void BookAppointment(){

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
