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
        setTitle("Sheffield Dental Practice");
        setSize(500,600);

        JButton btnSec = new JButton("Secretary");
        btnSec.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        new SecretaryGUI().SecretaryGUI();
                        setVisible(false);
                    }
                }
        );

        JButton btnDen = new JButton("Dentist");
        btnDen.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        new DentistGUI().DentistGUI();
                        setVisible(false);
                    }
                }
        );

        JButton btnHyg = new JButton("Hygenist");
        btnHyg.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        new HygienistGUI().HygienistGUI();
                        setVisible(false);
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
