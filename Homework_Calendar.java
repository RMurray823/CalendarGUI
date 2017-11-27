



import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.text.AttributedString;
    
public class Homework_Calendar extends JFrame {

  DefaultTableModel model;
  Calendar calendar = new GregorianCalendar();
  JLabel label = new JLabel();
  JTextArea textArea = new JTextArea("Write calendar notes here",10,10);
  //JScrollPane scrollPane = new JScrollPane(textArea); 
  //textArea.setEditable(false);

  Homework_Calendar() {
       
    //creates overall window  
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Swing Appointment Calandar");
    this.setSize(1000,600);
    this.setLayout(new BorderLayout());
    this.setVisible(true);
    
    textArea.setFont(new Font("Arial", Font.BOLD, 30));
    
    label = new JLabel();
    label.setHorizontalAlignment(SwingConstants.CENTER);
    
    JButton b1 = new JButton("<<");
    b1.setFont(new Font("Arial", Font.BOLD, 40));
    b1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        calendar.add(Calendar.MONTH, -1);
        updateMonth();
      }
    });
 
    JButton b2 = new JButton(">>");
    b2.setFont(new Font("Arial", Font.BOLD, 40));
    b2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        calendar.add(Calendar.MONTH, +1);
        updateMonth();
      }
    });
 
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.add(b1,BorderLayout.WEST);
    panel.add(label,BorderLayout.CENTER);
    panel.add(b2,BorderLayout.EAST);
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
    label.setFont(new Font("Arial", Font.BOLD, 50));

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
    Homework_Calendar sc = new Homework_Calendar();
  }
 
}