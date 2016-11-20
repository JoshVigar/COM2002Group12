import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Created by User on 16/11/2016.
 */

/*//event handler for unsubscription button
        bUnsubscribe.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        String unsubscribe = "UPDATE Subscription Set " +
                                "SubscriptionTitle = 'None' ,MonthlyCost = 0,CheckUp = 0,HygieneVisit = 0,Repair = 0" +
                                " WHERE SubscriptionID = " + txtPID.getText().trim();
                        reg.updateData(unsubscribe);
                    }
                }
        );*/

/* If you need to turn unsubscribe and subscribe to methods
*  write public int Unsubscribe( int pID) or public int Subscribe(int pID,String typeOfS)
* and replace the code in the action listener with the correct parameters
* and copy paste it to the functions
* the last statement in them should be return reg.updateData which will return 1 if succeeded or 0 if failed
* */

/*//event handler for suscribe button
        bSubscribe.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        LocalDate localDate = LocalDate.now();
                        String endDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate.plusYears(1));

                        String firstSubscription = "INSERT INTO Subscription (SubscriptionTitle, MonthlyCost, CheckUp," +
                                " HygieneVisit, Repair, EndDate) VALUES(";
                        if (((String)Sub.getSelectedItem()).equals("None")){
                            firstSubscription += "'" + (String) Sub.getSelectedItem() + "',0,0,0,0" + ", '" + endDate + "')";
                        } else if (((String) Sub.getSelectedItem()).equals("NHS Free Plan")) {
                            firstSubscription += "'" + (String) Sub.getSelectedItem() + "', 0,2,2,6" + ", '" + endDate + "')";
                        } else if (((String) Sub.getSelectedItem()).equals("Maintenance Plan")) {
                            firstSubscription += "'" + (String) Sub.getSelectedItem() + "', 15,2,2,0" + ", '" + endDate + "')";
                        } else if (((String) Sub.getSelectedItem()).equals("Oral Health Plan")) {
                            firstSubscription += "'" + (String) Sub.getSelectedItem() + "', 21,2,4,0" + ", '" + endDate + "')";
                        } else if (((String) Sub.getSelectedItem()).equals("Dental Repair Plan")) {
                            firstSubscription += "'" + (String) Sub.getSelectedItem() + "', 36,2,2,2" + ", '" + endDate + "')";
                        }
                        reg.updateData(firstSubscription);
                    }
                }
        );*/

public class ManagePatients extends JFrame {

    public void ManagePatients()throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException{
        final DataAccessBase reg = new DataAccessBase("jdbc:mysql://stusql.dcs.shef.ac.uk/team012?user=team012&password=a735fd61");

        //setting initial window ssettings
        setTitle("Sheffield Dental Practice");
        setSize(500,600);

        //creating labels, buttons and comboboxes
        JLabel title = new JLabel("Enter Patient Details");
        JLabel pID = new JLabel("PatientID:");
        final JTextField txtPID = new JTextField(20);
        String[] sub = {"Subscribe","Cancel Subscription"};
        JLabel operation= new JLabel("Operation:");
        final JComboBox Sub = new JComboBox(sub);
        JButton bSubmit = new JButton("Submit");

        //add inputs to panel
        JPanel inputsPanel = new JPanel();
        inputsPanel.add(pID);
        inputsPanel.add(txtPID);
        inputsPanel.add(operation);
        inputsPanel.add(Sub);
        //setting layout of inputs
        inputsPanel.setLayout(new BoxLayout(inputsPanel, BoxLayout.Y_AXIS));


        //event handler for submit button
        bSubmit.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        String[] managedPatient = {txtPID.getText(), (String)Sub.getSelectedItem()};

                    }
                }
        );


        //added back button and event listener
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        new SecretaryGUI().SecretaryGUI();
                    }
                }
        );

        //for setting the inputs border later
        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);

        //adding items to content pane and setting layout
        Container contentPane = getContentPane();
        contentPane.add(title, BorderLayout.NORTH);
        contentPane.add(inputsPanel, BorderLayout.CENTER);
        contentPane.add(btnBack, BorderLayout.SOUTH);

        //adding border to inputs
        inputsPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));

        //Don't forget to pack!
        pack();
        //setting position and close operation for window
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
