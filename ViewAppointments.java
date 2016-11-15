import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ViewAppointments extends JFrame {
	
	public void ViewAppointments() {
        setTitle("Sheffield Dental Practice");
        setSize(500,600);

        Container container = getContentPane();
        
        String[] rowNames = {"Monday","Tuesday","Wednesday","Thursday","Friday"};


        //DefaultTableModel mtblCalendar = new DefaultTableModel();
        JTable hygCalendar = new JTable(5,2);
        JTable denCalendar = new JTable(5,2);
        
        JLabel title = new JLabel("Appointments Calendar: Week of...");
        JLabel hygTitle = new JLabel("Hygienist Calendar:");
        JLabel denTitle = new JLabel("Dentist Calendar:");
        
        JPanel hygPanel = new JPanel();
        hygPanel.add(hygTitle);
        hygPanel.add(hygCalendar);
        
        JPanel denPanel = new JPanel();
        denPanel.add(denTitle);
        denPanel.add(denCalendar);

        
        //panel.add(title);
        
        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);
        
        //String[] rowNames = {"Monday","Tuesday","Wednesday","Thursday","Friday"};

        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        mPanel.add(title, BorderLayout.NORTH);
        mPanel.add(hygPanel, BorderLayout.CENTER);
        mPanel.add(denPanel, BorderLayout.SOUTH);
        mPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));
        container.add(mPanel);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
	}
        
     public static void main(String[] args){
    	 ViewAppointments appointments = new ViewAppointments();
   	     appointments.ViewAppointments();

  }
}