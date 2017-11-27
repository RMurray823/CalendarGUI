import java.util.*; //where gregorian calendar lives
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
//import java.text.AttributedString; thought might help but didn't use
    
public class CalendarGUI extends JFrame {

  DefaultTableModel model;
  Calendar calendar = new GregorianCalendar();
  JLabel label = new JLabel();
  JTextArea textArea = new JTextArea("Write calendar notes here",5,5);
  //JScrollPane scrollPane = new JScrollPane(textArea); 
  //textArea.setEditable(false);

  CalendarGUI() {
       
    //creates overall window  
    
    //exit on close
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //title
    this.setTitle("Swing Appointment Calandar");
    //initial size
    this.setSize(400,400);
    this.setLayout(new BorderLayout());
    //makes visible
    this.setVisible(true);
    
    //sets font for text area
    textArea.setFont(new Font("Arial", Font.BOLD, 20));
    
    label = new JLabel();
    label.setHorizontalAlignment(SwingConstants.CENTER);
    
    //back month button
    JButton month_back = new JButton("<<");
    month_back.setFont(new Font("Arial", Font.BOLD, 20));
    month_back.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        calendar.add(Calendar.MONTH, -1);
        updateMonth();
      }
    });
 
    //forward month button
    JButton month_forward = new JButton(">>");
    month_forward.setFont(new Font("Arial", Font.BOLD, 20));
    month_forward.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        calendar.add(Calendar.MONTH, +1);
        updateMonth();
      }
    });
 
    //overall JPanel layout
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.add(month_back,BorderLayout.WEST);
    panel.add(label,BorderLayout.CENTER);
    panel.add(month_forward,BorderLayout.EAST);
    panel.add(textArea,BorderLayout.SOUTH);
    
    String [] columns = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    model = new DefaultTableModel(null, columns);
    JTable table = new JTable(model);
    JScrollPane pane = new JScrollPane(table);
 
    this.add(panel,BorderLayout.NORTH);
    this.add(pane,BorderLayout.CENTER);
        this.updateMonth();
}
 
void updateMonth() {
    calendar.set(Calendar.DAY_OF_MONTH, 1);
 
    String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
    int year = calendar.get(Calendar.YEAR);
    label.setText(month + " " + year);
    label.setFont(new Font("Arial", Font.BOLD, 20));

    int startDay = calendar.get(Calendar.DAY_OF_WEEK);
    int numberOfDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    int weeks = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
 
    model.setRowCount(0);
    model.setRowCount(weeks);
 
    int i = startDay-1;
    for(int day = 1; day <= numberOfDays; day++){
      model.setValueAt(day, i/7 , i%7 );  
      //model.setFont(new Font("Arial", Font.BOLD, 50));
      i = i + 1;
    }
  }
  
  public static void main(String[] arguments) {
    //JFrame.setDefaultLookAndFeelDecorated(true); 
    CalendarGUI new_calendar = new CalendarGUI();
  }
 
}