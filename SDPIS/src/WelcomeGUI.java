import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by User on 14/11/2016.
 */
public class WelcomeGUI extends JFrame {

    public void WelcomeGUI() {
        //set size and title of welcome frame
        setTitle("Sheffield Dental Practice");
        setSize(500,600);

        //create multiple buttons to represent different users
        JButton btnSec = new JButton("Secretary");
        btnSec.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        new SecretaryGUI().SecretaryGUI();
                    }
                }
        );

        JButton btnDen = new JButton("Dentist");
        btnDen.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        new DentistGUI().DentistGUI();
                    }
                }
        );

        JButton btnHyg = new JButton("Hygenist");
        btnHyg.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        new HygienistGUI().HygienistGUI();
                    }
                }
        );

        //create button panel and add user buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.add(btnSec);
        buttonPanel.add(btnDen);
        buttonPanel.add(btnHyg);

        JLabel title = new JLabel("Welcome to Sheffield Dental Practice");

        //creates ints to represent borders for contents of the panel
        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);

        //create a new panel that consists of the title and buttonPanel
        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        mPanel.add(title, BorderLayout.NORTH);
        mPanel.add(buttonPanel, BorderLayout.CENTER);
        mPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));

        //add mPanel to the contentPane
        Container contentPane = getContentPane();
        contentPane.add(mPanel);

        //Don't forget to pack!
        pack();
        setLocationRelativeTo(null);

//        WelcomeGUI().addWindowListener(new WindowAdapter()
//        {
//            public void windowClosing(WindowEvent e)
//            {
//
//            }
//        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

//    public void CloseNDisconnect{
//
//    }

}
