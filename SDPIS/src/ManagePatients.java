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

        setTitle("Sheffield Dental Practice");
        setSize(500,600);

        JLabel title = new JLabel("Enter Patient Details");
        JLabel pID = new JLabel("PatientID:");
        final JTextField txtPID = new JTextField(20);
        JLabel fName = new JLabel("Forename:");
        final JTextField txtFName = new JTextField(20);
        JLabel sName = new JLabel("Surname:");
        final JTextField txtSName = new JTextField(20);
        String[] sub = {"Subscribe","Cancel Subscription"};
        JLabel operation= new JLabel("Operation:");
        final JComboBox Sub = new JComboBox(sub);
        JButton bSubmit = new JButton("Submit");

        JPanel inputsPanel = new JPanel();
        inputsPanel.add(pID);
        inputsPanel.add(txtPID);
        inputsPanel.add(fName);
        inputsPanel.add(txtFName);
        inputsPanel.add(sName);
        inputsPanel.add(txtSName);
        inputsPanel.add(operation);
        inputsPanel.add(Sub);
        inputsPanel.setLayout(new BoxLayout(inputsPanel, BoxLayout.Y_AXIS));


        bSubmit.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        String[] managedPatient = {txtPID.getText(), (String)Sub.getSelectedItem(), txtFName.getText(),
                                txtSName.getText()};

                        System.out.print(Arrays.toString(managedPatient));

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
