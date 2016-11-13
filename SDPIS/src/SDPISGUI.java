import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Josh on 08/11/2016.
 */
public class SDPISGUI extends JFrame {


    public void WelcomeGUI() {
        setTitle("Sheffield Dental Practice");
        setSize(500,600);

        JButton btnSec = new JButton("Secretary");
        btnSec.addActionListener(
             new ActionListener(){
                 public void actionPerformed(ActionEvent e){
                     //Go to Secretary view
                 }
             }
        );

        JButton btnDen = new JButton("Dentist");
        btnDen.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    //Go to Dentist view
                }
            }
        );

        JButton btnHyg = new JButton("Hygenist");
        btnHyg.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    //Go to Hygenist view
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

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(title, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));

        //Don't forget to pack!
        pack();
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void SecretaryGUI(){

    }

    public void RegisterPage(){
        setTitle("Sheffield Dental Practice");
        setSize(500,600);
        setLocationRelativeTo(null);

        JTextField txtTitle = new JTextField();
        JTextField txtFName = new JTextField();
        JTextField txtLName = new JTextField();

        //comboboxes date of birth
        JComboBox days = new JComboBox();
        JComboBox months = new JComboBox();
        JComboBox years = new JComboBox();

        days.addItem("Day");
        String[] strDays = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14"
                ,"15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        days.addItem(strDays);

        months.addItem("Month");
        String[] strMonths = {"1","2","3","4","5","6","7","8","9","10","11","12"};
        months.addItem(strMonths);

        years.addItem("Year");
//        for(int i=1900;i<=2100;i++) {
//            years.addItem(i);
//        }

        JTextField txtPhone = new JTextField();

        //combobox subscription types
        String[] subTypes = { "None", "NHS", "Maintenance", "Oral", "Repair"};
        JComboBox subList = new JComboBox(subTypes);

        JTextField txtHousenum = new JTextField();
        JTextField txtStreet = new JTextField();
        JTextField txtAddressCity = new JTextField();
        JTextField txtAddressRegion = new JTextField();
        JTextField txtPostCode = new JTextField();

        JPanel inputsPanel = new JPanel();
        inputsPanel.add(txtTitle);
        inputsPanel.add(txtFName);
        inputsPanel.add(txtLName);

        JPanel inputsPanel2 = new JPanel();
        inputsPanel2.add(txtPhone);
        inputsPanel2.add(subList);
        inputsPanel2.add(txtHousenum);
        inputsPanel2.add(txtStreet);
        inputsPanel2.add(txtAddressCity);
        inputsPanel2.add(txtAddressRegion);
        inputsPanel2.add(txtPostCode);

        JPanel dobPanel = new JPanel();
        dobPanel.add(days);
        dobPanel.add(months);
        dobPanel.add(years);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
}
