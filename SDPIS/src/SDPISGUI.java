import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.Set;

/**
 * Created by Josh on 08/11/2016.
 */
public class SDPISGUI extends JFrame {
    public void SetTitleNSize(){
        this.setTitle("Sheffield Dental Practice");
        this.setSize(500,600);
    }

    public void WelcomeGUI() {
        SetTitleNSize();

        JButton btnSec = new JButton("Secretary");
        btnSec.addActionListener(
             new ActionListener(){
                 public void actionPerformed(ActionEvent e){
                     new SDPISGUI().SecretaryGUI();
                 }
             }
        );

        JButton btnDen = new JButton("Dentist");
        btnDen.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    new SDPISGUI().DentistGUI();
                }
            }
        );

        JButton btnHyg = new JButton("Hygenist");
        btnHyg.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    new SDPISGUI().HygienistGUI();
                }
            }
        );

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.add(btnSec);
        buttonPanel.add(btnDen);
        buttonPanel.add(btnHyg);

        JLabel title = new JLabel("Welcome to Sheffield Dental Practice");

        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);

        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        mPanel.add(title, BorderLayout.NORTH);
        mPanel.add(buttonPanel, BorderLayout.CENTER);
        mPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));

        Container contentPane = getContentPane();
        contentPane.add(mPanel);

        //Don't forget to pack!
        pack();
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void SecretaryGUI(){
        SetTitleNSize();

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
                        new SDPISGUI().RegisterPage();
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
                        new SDPISGUI().BookAppointment();
                    }
                }
        );

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.add(btnApp);
        buttonPanel.add(btnReg);
        buttonPanel.add(btnMP);
        buttonPanel.add(btnBA);

        JButton back = new JButton("Go Back");
        back.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                       WelcomeGUI();
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
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));


        //Don't forget to pack!
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


    }

    public void BookAppointment(){

        SetTitleNSize();

        JLabel title = new JLabel("Enter Appointment Details");
        JLabel pID = new JLabel("PatientID:");
        JLabel partner = new JLabel("Partner:");
        JLabel sTime = new JLabel("Start Time:");
        JLabel tType = new JLabel("Treatment Type:");
        JTextField txtPID = new JTextField(20);
        JTextField txtPartner = new JTextField(20);
        JTextField txtStart = new JTextField(20);
        JTextField txtType = new JTextField(20);

        JPanel inputsPanel = new JPanel();
        inputsPanel.add(pID);
        inputsPanel.add(txtPID);
        inputsPanel.add(partner);
        inputsPanel.add(txtPartner);
        inputsPanel.add(sTime);
        inputsPanel.add(txtStart);
        inputsPanel.add(tType);
        inputsPanel.add(txtType);
        inputsPanel.setLayout(new BoxLayout(inputsPanel, BoxLayout.Y_AXIS));

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

    public void DentistGUI(){
        setTitle("Sheffield Dental Practice");
        setSize(500,600);
        JButton btnChk = new JButton("Finish Appointment");
        btnChk.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        //Go to finish appointment view
                    }
                }
        );
        JButton btnView = new JButton("View Appointments");
        btnView.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        //Go to Appointments
                    }
                }
        );
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.add(btnChk);
        buttonPanel.add(btnView);

        JLabel title = new JLabel("Dentist View");

        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(title, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));

        //Don't forget to pack!
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void HygienistGUI(){
        setTitle("Sheffield Dental Practice");
        setSize(500,600);
        JButton btnChk = new JButton("Finish Appointment");
        btnChk.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        //Go to finish appointment view
                    }
                }
        );
        JButton btnView = new JButton("View Appointments");
        btnView.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        //Go to appointments
                    }
                }
        );
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.add(btnChk);
        buttonPanel.add(btnView);

        JLabel title = new JLabel("Hygienist View");

        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(title, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));
        //Don't forget to pack!
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void RegisterPage(){
        SetTitleNSize();

        JTextField txtTitle = new JTextField(20);
        JTextField txtFName = new JTextField(20);
        JTextField txtLName = new JTextField(20);

        //comboboxes date of birth
        JComboBox days = new JComboBox();
        JComboBox months = new JComboBox();
        JComboBox years = new JComboBox();

        days.addItem("Day");
        for(int i=1;i<=31;i++) {
            days.addItem(new Integer(i));
        }

        months.addItem("Month");
        for(int i=1;i<=12;i++) {
            months.addItem(new Integer(i));
        }

        years.addItem("Year");
        for(int i=1900;i<=2100;i++) {
            years.addItem(new Integer(i));
        }

        JTextField txtPhone = new JTextField(20);

        //combobox subscription types
        String[] subTypes = { "None", "NHS", "Maintenance", "Oral", "Repair"};
        JComboBox subList = new JComboBox(subTypes);

        JTextField txtHousenum = new JTextField(20);
        JTextField txtStreet = new JTextField(20);
        JTextField txtAddressCity = new JTextField(20);
        JTextField txtAddressRegion = new JTextField(20);
        JTextField txtPostCode = new JTextField(20);
        JButton submit = new JButton("Submit");

        JPanel inputsPanel = new JPanel();
        inputsPanel.add(txtTitle);
        inputsPanel.add(txtFName);
        inputsPanel.add(txtLName);
        inputsPanel.setLayout(new BoxLayout(inputsPanel, BoxLayout.Y_AXIS));

        JPanel inputsPanel2 = new JPanel();
        inputsPanel2.add(txtPhone);
        inputsPanel2.add(subList);
        inputsPanel2.add(txtHousenum);
        inputsPanel2.add(txtStreet);
        inputsPanel2.add(txtAddressCity);
        inputsPanel2.add(txtAddressRegion);
        inputsPanel2.add(txtPostCode);
        inputsPanel2.add(submit);
        inputsPanel2.setLayout(new BoxLayout(inputsPanel2, BoxLayout.Y_AXIS));

        JPanel dobPanel = new JPanel();
        dobPanel.add(days);
        dobPanel.add(months);
        dobPanel.add(years);



        JLabel title = new JLabel("Please enter your details:");

        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);

        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        mPanel.add(title,BorderLayout.NORTH);

        JPanel cPanel = new JPanel();
        cPanel.add(inputsPanel);
        cPanel.add(dobPanel);
        cPanel.add(inputsPanel2);
        cPanel.setLayout(new BoxLayout(cPanel, BoxLayout.Y_AXIS));
        mPanel.add(cPanel,BorderLayout.SOUTH);
        mPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));

        Container contentPane = getContentPane();
        contentPane.add(mPanel);



        //Don't forget to pack!
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
