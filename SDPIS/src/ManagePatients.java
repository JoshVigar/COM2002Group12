import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Created by User on 16/11/2016.
 */
public class ManagePatients extends JFrame {

    public void ManagePatients(){
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

                        System.out.print(Arrays.toString(managedPatient));

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
