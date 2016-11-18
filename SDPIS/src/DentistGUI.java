import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Created by User on 14/11/2016.
 */
public class DentistGUI extends JFrame {

    public void DentistGUI(){

        //initialising the window
        setTitle("Sheffield Dental Practice");
        setSize(500,600);

        //Add buttons and event handlers to take you to the various screens
        JButton btnFApp = new JButton("Finish Appointment");
        btnFApp.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        new DentistGUI().FinishAppointmment();
                    }
                }
        );
        JButton btnView = new JButton("View Appointments");
        btnView.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        try {
                            new ViewAppointments().ViewDentistAppointments();
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
        JButton btnBack = new JButton("Go Back");
        btnBack.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        new WelcomeGUI().WelcomeGUI();
                    }
                }
        );


        //create a buttonPanel and add all buttons to it
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.add(btnFApp);
        buttonPanel.add(btnView);

        //Creates and adds title label
        JLabel title = new JLabel("Dentist View");

        //for adding borders later
        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);

        //Add elements to container
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(title, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        contentPane.add(btnBack, BorderLayout.SOUTH);
        //create border around inputs
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));

        //Don't forget to pack!
        pack();
        //setting the location in the window and the close operation
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set frame visibility to true.
        setVisible(true);
    }


    public void FinishAppointmment(){
        //initialise the window
        setTitle("Sheffield Dental Practice");
        setSize(500,600);

        //add labels and text fields for the form
        JLabel title = new JLabel("Enter Appointment Information To Log Visit:");
        JLabel type = new JLabel("Treatment type:");
        //ocmbobox for types of treatmet
        String[] tType = {"Checkup","","","",""};
        final JComboBox Type = new JComboBox(tType);
        //comboboxes for the time of day selection
        JLabel sTime = new JLabel("Start Time:");
        String[] hour = {"Hour","09","10","11","12","14","15","16"};
        String[] minute = {"Minute","00","20","40"};
        final JComboBox hr = new JComboBox(hour);
        final JComboBox min = new JComboBox(minute);
        JButton bSubmit = new JButton("Submit");

        //making the time selection panel
        JPanel timePanel = new JPanel();
        timePanel.add(sTime);
        timePanel.add(hr);
        timePanel.add(min);

        //adding time selection and submit to a main panel
        JPanel mPanel = new JPanel();
        mPanel.add(timePanel);
        mPanel.add(bSubmit);

        //set layout of mPanel
        mPanel.setLayout(new BoxLayout(mPanel, BoxLayout.Y_AXIS));
        mPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        //evet listener for hte submit button
        bSubmit.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        String[] FinishedApp = {(String)hr.getSelectedItem()+":"+(String)min.getSelectedItem()};

                        System.out.print(Arrays.toString(FinishedApp));

                    }
                }
        );

        //add back button and event listener
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        new DentistGUI().DentistGUI();
                    }
                }
        );

        //for creating the inputs border
        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);

        //adding elements to container and setting layout
        Container contentPane = getContentPane();
        contentPane.add(title, BorderLayout.NORTH);
        contentPane.add(mPanel, BorderLayout.CENTER);
        contentPane.add(btnBack, BorderLayout.SOUTH);

        //adding border to the main panel
        mPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));

        //Don't forget to pack!
        pack();
        //setting the location in the window and the close operation
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
