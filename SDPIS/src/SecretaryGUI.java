import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;
import java.io.File;
import java.io.PrintStream;

/**
 * Created by User on 14/11/2016.
 */
public class SecretaryGUI extends JFrame{

    public void SecretaryGUI(){
        //set initial settings for window
        setTitle("Sheffield Dental Practice");
        setSize(500,600);

        //add buttons and event listeners for the secretary options
        JButton btnApp = new JButton("View Appointment Calendar");
        btnApp.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        try {
                            new ViewAppointment().startDate("Secretary");
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
        //view single Appointment button
        JButton btnMA = new JButton("View A Single Appointment");
        btnMA.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        new ManageAppointments().ManageAppointments();
                    }
                }
        );

        //register patient button
        JButton btnReg = new JButton("Register Patient");
        btnReg.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        try {
                            new RegistrationPage().RegisterPage();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                        } catch (InstantiationException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
        );

        //manage patients button
        JButton btnMP = new JButton("Manage Patients");
        btnMP.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        try {
                            new ManagePatients().ManagePatients();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                        } catch (InstantiationException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
        );

        //book appointment button
        JButton btnBA = new JButton("Book Appointment");
        btnBA.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        try {
                            new BookAppointment().BookAppointment();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                        } catch (InstantiationException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
        );
        //book holiday button
        JButton btnBH = new JButton("Book Holiday");
        btnBH.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        try {
                            new BookAppointment().BookHoliday();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                        } catch (InstantiationException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
        );

        //check out patient button
        JButton btnChk = new JButton("Checkout Patient");
        btnChk.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        new SecretaryGUI().CheckoutPatient();
                    }
                }
        );

        //add buttons to panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.add(btnApp);
        buttonPanel.add(btnMA);
        buttonPanel.add(btnReg);
        buttonPanel.add(btnMP);
        buttonPanel.add(btnBA);
        buttonPanel.add(btnBH);
        buttonPanel.add(btnChk);

        //add back button and event handler
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        new WelcomeGUI().WelcomeGUI();
                    }
                }
        );

        //create title label
        JLabel title = new JLabel("Secretary Page");

        //adding border to the inputs
        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);

        //adding elements to contentpane and setting layout
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(title, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        contentPane.add(btnBack,BorderLayout.SOUTH);

        //adding border to inputs
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));


        //Don't forget to pack!
        pack();
        //setting position and close operation for window
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }

    public void CheckoutPatient(){
        //initial setting for window
        setTitle("Sheffield Dental Practice");
        setSize(500,600);

        //creating labels, text fields and comboboxes for inputs
        JLabel title = new JLabel("Enter Appointment Details To Finish:");
        JLabel ptnr = new JLabel("Partner:");
        String[] partners = {"Dentist","Hygienist"};
        final JComboBox partner = new JComboBox(partners);
        JLabel sTime = new JLabel("Appointment Start Time:");
        String[] hour = {"Hour","09","10","11","12","14","15","16","17"};
        String[] minute = {"Minute","00","20","40"};
        final JComboBox hr = new JComboBox(hour);
        final JComboBox min = new JComboBox(minute);
        JButton bSubmit = new JButton("Submit");

        //adding partner label and combobox to a panel
        JPanel inputsPanel = new JPanel();
        inputsPanel.add(ptnr);
        inputsPanel.add(partner);

        //adding time of day selectors to a panel
        JPanel timePanel = new JPanel();
        timePanel.add(sTime);
        timePanel.add(hr);
        timePanel.add(min);

        //adding subpanels to a main panel
        JPanel mPanel = new JPanel();
        mPanel.add(inputsPanel);
        mPanel.add(timePanel);
        mPanel.add(bSubmit);

        //setting the layout of mPanel
        mPanel.setLayout(new BoxLayout(mPanel, BoxLayout.Y_AXIS));
        mPanel.setAlignmentX(Component.LEFT_ALIGNMENT);


        //event handling for the submit button
        bSubmit.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        String finishedApp = (String)partner.getSelectedItem()+(String)hr.getSelectedItem()+":"+(String)min.getSelectedItem();
                       // File file = new File("receipt.txt");
                       // PrintStream printStreamToFile = new PrintStream(file);
                       // System.setOut(printStreamToFile);
                        //System.out.println("Hello I am writing to File xyz.txt");
                        System.out.println(finishedApp);

                    }
                }
        );

        //creating and adding event handling for back button
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        new SecretaryGUI().SecretaryGUI();
                    }
                }
        );

        //for adding border to inputs
        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);

        //adding elements to the content pane
        Container contentpane = getContentPane();
        contentpane.add(title, BorderLayout.NORTH);
        contentpane.add(mPanel, BorderLayout.CENTER);
        contentpane.add(btnBack, BorderLayout.SOUTH);

        //setting the border for the inputs
        mPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));

        //Don't forget to pack!
        pack();
        //setting position and close operation for window
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


}
