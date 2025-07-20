// Author name : Akram Tarek
package Airport;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.DriverManager;

public class Flight extends Frame implements ActionListener, WindowListener, MouseListener{
    JTable table1;
    
    String[] columnNames = {"FlightNumber","Airline","Weekdays"};
    Object [][] rowData = {

};
    DefaultTableModel model = new DefaultTableModel(rowData,columnNames);
    
    JLabel background;
    JPanel flight_panel = new JPanel();
    JButton exit = new JButton("Exit");
    JButton back = new JButton("Back To Main");
    JButton add = new JButton("Add");
    JButton update = new JButton("Update");
    JButton delete = new JButton("Delete");
    JButton search1 = new JButton("Search");
    JButton Refresh = new JButton("Refresh");
    JLabel Flight = new JLabel("Flight");
    JLabel flightnumber = new JLabel("Flight Number");
    JLabel airline = new JLabel("Airline");
    JLabel weekdays = new JLabel("Weekdays");
    TextField tflightnumber = new TextField();
    TextField tairline = new TextField();
    TextField tweekdays = new TextField();
    TextField search = new TextField();
    Connection con;
    ResultSet rs;
    Statement stmt;
    
    Flight(){
    ConToSql();
    table1 = new JTable(model);
    JScrollPane scrollpane1 = new JScrollPane(table1);
    scrollpane1.setBounds(510,40, 460, 230);
    add(scrollpane1);
        setBounds(380, 280, 990, 510);
        setLayout(null);
        setVisible(false);
        setTitle("Flight");
        setLocationRelativeTo(null);
        back.setBounds(50,450,120,30);// Back To Main
        back.addActionListener(this); 
        back.setFont(new Font("impact", Font.PLAIN , 15));
        add(back);
        delete.setBounds(370,290,80,30); //delete
        delete.addActionListener(this);
        delete.setFont(new Font("impact", Font.PLAIN, 15));
        add(delete);
        update.setBounds(220, 290, 80, 30); //update
        update.addActionListener(this); 
        update.setFont(new Font ("impact", Font.PLAIN, 15));
        add(update);
        add.setBounds(80,290,80,30); //add
        add.addActionListener(this);
        add.setFont(new Font ("impact", Font.PLAIN, 15));
        add(add);
        exit.setBounds(880, 450, 80, 30); //exit
        exit.addActionListener(this);
        exit.setFont(new Font ("impact", Font.PLAIN, 15));
        add(exit);
        search1.setBounds(780, 300, 180, 30);
        search1.addActionListener(this);
        search1.setFont(new Font ("impact", Font.PLAIN, 15));
        add(search1);
        search.setText("");
        search.setBounds(550, 305, 200 ,22);
        add(search);
        Refresh.setBounds(780, 350, 180, 30);
        Refresh.addActionListener(this);
        Refresh.setFont(new Font ("impact", Font.PLAIN, 15));
        add(Refresh);
        flightnumber.setBounds(40, 60, 140, 70);
        flightnumber.setFont(new Font("Roman", Font.PLAIN, 20));
        flightnumber.setForeground(Color.white);
        add(flightnumber);
        airline.setBounds(40, 110, 170, 70);
        airline.setFont(new Font("Roman", Font.PLAIN, 20));
        airline.setForeground(Color.white);
        add(airline);
        weekdays.setBounds(40, 150, 120, 70);
        weekdays.setFont(new Font("Roman", Font.PLAIN, 20));
        weekdays.setForeground(Color.white);
        add(weekdays);
        Flight.setBounds(190, 5, 200, 70);
        Flight.setFont(new Font("impact", Font.PLAIN, 30));
        Flight.setForeground(Color.WHITE);
        add(flightnumber);
        tflightnumber.setText("");
        tflightnumber.setBounds(235,90,200,20); // airport code
        add(tflightnumber);
        tairline.setText("");
        tairline.setBounds(235, 135, 200, 20); // airport name
        add(tairline);
        tweekdays.setText("");
        tweekdays.setBounds(235, 175, 200, 20); // state
        add(tweekdays);
     flight_panel.setLayout(null);
     flight_panel.setBounds(40, 40, 450, 230);
     flight_panel.setBackground(new Color(0, 0, 0, 80));
     add(flight_panel);
     flight_panel.add(Flight);
     flight_panel.add(airline);
     flight_panel.add(weekdays);
     flight_panel.add(flightnumber);
     flight_panel.add(tflightnumber);
     flight_panel.add(tairline);
     flight_panel.add(tweekdays);
        ImageIcon bg = new ImageIcon(
                "C:\\Users\\pc\\Desktop\\Flightv2222.jpg"
        );
        background = new JLabel ("", bg, JLabel.CENTER);
        background.setBounds(0, 0, 990, 510);
        add(background);
        addWindowListener(this);
        table1.addMouseListener(this);
        UpdateTable();
    }
            public void ConToSql(){  
            String url="jdbc:sqlserver://localhost:1433;databaseName=Airline";
            String user="akram";
            String pass="123321";
            
            try{
            con= DriverManager.getConnection(url, user, pass);
            stmt=con.createStatement();
            rs=stmt.executeQuery("select * from Flight");
            
            while(rs.next()){
            model.addRow(new Object[] {rs.getString("Flightnumber"),rs.getString("Airline"),rs.getString("Weekdays")});
            }
            
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
    }
    public void UpdateTable(){
          try{
              model.getDataVector().removeAllElements(); 
          
              String query=("select * from Flight");
                
                PreparedStatement PS=con.prepareStatement(query);
                  rs=PS.executeQuery();
                   while(rs.next()){
                       model.addRow(new Object[] {rs.getString("Flightnumber"),rs.getString("Airline"),rs.getString("Weekdays")});
            }
                
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
                        if(e.getSource()==back)
     {
         this.hide();
         Main n1 = new Main();
         n1.show();
     }
     if(e.getSource()==exit)
     {
         System.exit(0);
     }
     if(e.getSource()==add)
     {
         {
            String flightnumber1= tflightnumber.getText().toString();
            String airline1=tairline.getText().toString();
            String weekdays1=tweekdays.getText().toString();
           
            
            String query="insert into Flight  values ( '" + flightnumber1 + "' , '" + airline1 + "' , '" + weekdays1 + "')";
           
            try{
                
                stmt.executeUpdate(query);
                model.addRow(new Object[]{flightnumber1,airline1,weekdays1});
                JOptionPane.showMessageDialog(null, "Added Successfully");
            }
            catch(SQLException ex){
             JOptionPane.showMessageDialog(null, ex.toString());
            }
   
            tflightnumber.setText(null);
            tairline.setText(null);
            tweekdays.setText(null);
         }
     }
     if(e.getSource()==update){
          try{ 
            String flightnumber1= tflightnumber.getText().toString();
            String airline1=tairline.getText().toString();
            String weekdays1=tweekdays.getText().toString();
           
            
            String query="update Flight set Flightnumber='"+flightnumber1+"', Airline='"+airline1+"', Weekdays='"+weekdays1+"' where Flightnumber='"+flightnumber1+"' ";
                
                PreparedStatement PS=con.prepareStatement(query);
                    PS.execute();
                    JOptionPane.showMessageDialog(null, "Updated Successfully");
                    UpdateTable();
               }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
               }
            tflightnumber.setText(null);
            tairline.setText(null);
            tweekdays.setText(null);
         
     }
     if(e.getSource()==delete){
         String query="delete from Flight where Flightnumber=?";
                try{
                 PreparedStatement PS=con.prepareStatement(query);
                 PS.setString(1, flightnumber.getText());
                 PS.execute();
                 JOptionPane.showMessageDialog(null, "Deleted Successfully");
                 UpdateTable();
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
                flightnumber.setText(null);
     }
     if(e.getSource()==search1)
     {
       String query="select * from Flight where Flightnumber=?";
             try{
                    PreparedStatement PS=con.prepareStatement(query);
                    PS.setString(1, search.getText());
                    model.getDataVector().removeAllElements();
                    rs=PS.executeQuery();
                    while(rs.next()){
                        model.addRow(new Object[] {rs.getString("Flightnumber"),rs.getString("Airline"),rs.getString("Weekdays")});
                                }
             }
             catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
             }
     }
          if(e.getSource()==Refresh){
    try {
        model.setRowCount(0);
        rs = stmt.executeQuery("select * from Flight");
    while (rs.next()) {
        model.addRow(new Object[]{rs.getString("Flightnumber"),rs.getString("Airline"),rs.getString("Weekdays")});
    }
            tflightnumber.setText("");
            tairline.setText("");
            tweekdays.setText("");
    } catch (SQLException ex) {
       JOptionPane.showMessageDialog(null, ex.getMessage());
    }
     }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        dispose();
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int rowIndex = table1.getSelectedRow();
        String flightnumber1 = table1.getModel().getValueAt(rowIndex, 0).toString();
        String airline1 = table1.getModel().getValueAt(rowIndex, 1).toString();
        String  weekdays1= table1.getModel().getValueAt(rowIndex, 2).toString();
    tflightnumber.setText(flightnumber1);
    tairline.setText(airline1);
    tweekdays.setText(weekdays1);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
