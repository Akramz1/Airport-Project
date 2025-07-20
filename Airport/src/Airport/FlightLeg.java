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

public class FlightLeg extends Frame implements ActionListener, WindowListener, MouseListener{
    JTable table1;
    
    String[] columnNames = {"LegNo.","Scheduled_Dep_T","Scheduled_Arr_T","FlightNumber","AirportCode"};
    Object [][] rowData = {

};
    DefaultTableModel model = new DefaultTableModel(rowData,columnNames);
    
    JLabel background;
    JPanel flightleg_panel = new JPanel();
    JButton exit = new JButton("Exit");
    JButton back = new JButton("Back To Main");
    JButton add = new JButton("Add");
    JButton update = new JButton("Update");
    JButton delete = new JButton("Delete");
    JButton search1 = new JButton("Search");
    JButton Refresh = new JButton("Refresh");
    JLabel flightleg = new JLabel("Flight Leg");
    JLabel legno = new JLabel("Leg Number");
    JLabel scheduled_dep_time = new JLabel("Scheduled Departure Time");
    JLabel scheduled_arr_time = new JLabel("Scheduled Arrive Time");
    JLabel flightnumber = new JLabel("Flight Number");
    JLabel airportcode = new JLabel("Airport Code");
    TextField tlegno = new TextField();
    TextField tscheduled_dep_time = new TextField();
    TextField tscheduled_arr_time = new TextField();
    TextField tflightnumber = new TextField();
    TextField tairportcode = new TextField();
    TextField search = new TextField();
    Connection con;
    ResultSet rs;
    Statement stmt;
    
    FlightLeg(){
        ConToSql();
        table1 = new JTable(model);
    JScrollPane scrollpane1 = new JScrollPane(table1);
    scrollpane1.setBounds(420,40, 555, 230);
    add(scrollpane1);
        setBounds(380, 280, 990, 510);
        setBounds(380, 280, 990, 510);
        setLayout(null);
        setVisible(false);
        setTitle("FlightLeg");
        setLocationRelativeTo(null);
        back.setBounds(50,450,120,30);// Back To Main
        back.addActionListener(this); 
        back.setFont(new Font("impact", Font.PLAIN , 15));
        add(back);
        delete.setBounds(320,330,80,30); //delete
        delete.addActionListener(this);
        delete.setFont(new Font("impact", Font.PLAIN, 15));
        add(delete);
        update.setBounds(170, 330, 80, 30); //update
        update.addActionListener(this); 
        update.setFont(new Font ("impact", Font.PLAIN, 15));
        add(update);
        add.setBounds(30,330,80,30); //add
        add.addActionListener(this);
        add.setFont(new Font ("impact", Font.PLAIN, 15));
        add(add);
        exit.setBounds(880, 450, 80, 30); //exit
        exit.addActionListener(this);
        exit.setFont(new Font ("impact", Font.PLAIN, 15));
        add(exit);
        search1.setBounds(780, 290, 180, 30);
        search1.addActionListener(this);
        search1.setFont(new Font ("impact", Font.PLAIN, 15));
        add(search1);
        search.setText("");
        search.setBounds(550, 295, 200 ,22);
        add(search);
        Refresh.setBounds(780, 340, 180, 30);
        Refresh.addActionListener(this);
        Refresh.setFont(new Font ("impact", Font.PLAIN, 15));
        add(Refresh);
        flightleg.setBounds(140, 0, 140, 70);
        flightleg.setFont(new Font("impact", Font.PLAIN, 25));
        flightleg.setForeground(Color.WHITE);
        add(flightleg);
        legno.setBounds(20, 45, 120, 70);
        legno.setFont(new Font("Roman", Font.PLAIN, 18));
        legno.setForeground(Color.white);
        add(legno);
        scheduled_dep_time.setBounds(20, 90, 220, 70);
        scheduled_dep_time.setFont(new Font("Roman", Font.PLAIN, 18));
        scheduled_dep_time.setForeground(Color.white);
        add(scheduled_dep_time);
        scheduled_arr_time.setBounds(20, 130, 220, 70);
        scheduled_arr_time.setFont(new Font("Roman", Font.PLAIN, 18));
        scheduled_arr_time.setForeground(Color.white);
        add(scheduled_arr_time);
        flightnumber.setBounds(20, 170, 120, 70);
        flightnumber.setFont(new Font("Roman", Font.PLAIN, 18));
        flightnumber.setForeground(Color.white);
        add(flightnumber);
        airportcode.setBounds(20, 212, 120, 70);
        airportcode.setFont(new Font("Roman", Font.PLAIN, 18));
        airportcode.setForeground(Color.white);
        add(airportcode);
        tlegno.setText("");
        tlegno.setBounds(145,70,100,20);
        add(tlegno);
        tscheduled_dep_time.setText("");
        tscheduled_dep_time.setBounds(245, 115, 100, 20);
        add(tscheduled_dep_time);
        tscheduled_arr_time.setText("");
        tscheduled_arr_time.setBounds(245, 155, 100, 20);
        add(tscheduled_arr_time);
        tflightnumber.setText("");
        tflightnumber.setBounds(165, 235, 100, 20);
        add(tflightnumber);
        tairportcode.setText("");
        tairportcode.setBounds(165, 275, 100, 20); 
        add(tairportcode);
     flightleg_panel.setLayout(null);
     flightleg_panel.setBounds(15, 40, 400, 280);
     flightleg_panel.setBackground(new Color(0, 0, 0, 80));
     add(flightleg_panel);
     flightleg_panel.add(flightleg);
     flightleg_panel.add(legno);
     flightleg_panel.add(scheduled_dep_time);
     flightleg_panel.add(scheduled_arr_time);
     flightleg_panel.add(flightnumber);
     flightleg_panel.add(airportcode);
     flightleg_panel.add(tlegno);
     flightleg_panel.add(tscheduled_dep_time);
     flightleg_panel.add(tscheduled_arr_time);
     flightleg_panel.add(airportcode);
        
        ImageIcon bg = new ImageIcon(
                "C:\\Users\\pc\\Desktop\\flightlegv2.jpg"
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
            rs=stmt.executeQuery("select * from FlightLeg");
            
            while(rs.next()){
            model.addRow(new Object[] {rs.getString("LegNo"),rs.getString("Scheduled_Dep_Time"),rs.getString("Scheduled_Arr_Time"),rs.getString("Flightnumber"),rs.getString("AirportCode")});
            }
            
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
    }
    public void UpdateTable(){
          try{
              model.getDataVector().removeAllElements(); 
          
              String query=("select * from FlightLeg");
                
                PreparedStatement PS=con.prepareStatement(query);
                  rs=PS.executeQuery();
                   while(rs.next()){
                       model.addRow(new Object[] {rs.getString("LegNo"),rs.getString("Scheduled_Dep_Time"),rs.getString("Scheduled_Arr_Time"),rs.getString("Flightnumber"),rs.getString("AirportCode")});
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
            int legno1 =Integer.parseInt(tlegno.getText().toString());
            String scheduled_dep_time1 =tscheduled_dep_time.getText().toString();
            String scheduled_arr_time1 = tscheduled_arr_time.getText().toString();
            String flightnumber1 = tflightnumber.getText().toString();
            String airportcode1 = tairportcode.getText().toString();
           
            
            String query="insert into FlightLeg  values ( '" + legno1 + "' , '" + scheduled_dep_time1 + "' , '" + scheduled_arr_time1 + "', '" + flightnumber1 + "', '" + airportcode1 + "')";
           
            try{
                
                stmt.executeUpdate(query);
                model.addRow(new Object[]{legno1,scheduled_dep_time1,scheduled_arr_time1,flightnumber1,airportcode1});
                JOptionPane.showMessageDialog(null, "Added Successfully");
            }
            catch(SQLException ex){
             JOptionPane.showMessageDialog(null, ex.toString());
            }
   
            tlegno.setText(null);
            tscheduled_dep_time.setText(null);
            tscheduled_arr_time.setText(null);
            tflightnumber.setText(null);
            tairportcode.setText(null);
         }
     }
     if(e.getSource()==update){
          try{ 
            int legno1 =Integer.parseInt(tlegno.getText().toString());
            String scheduled_dep_time1 =tscheduled_dep_time.getText().toString();
            String scheduled_arr_time1 = tscheduled_arr_time.getText().toString();
            String flightnumber1 = tflightnumber.getText().toString();
            String airportcode1 = tairportcode.getText().toString();
           
            
            String query="update FlightLeg set LegNo='"+legno1+"', Scheduled_Dep_Time='"+scheduled_dep_time1+"', Scheduled_Arr_Time='"+scheduled_arr_time1+"', , Flightnumber='"+scheduled_arr_time1+"', , AirportCode='"+scheduled_arr_time1+"' where LegNo='"+legno1+"' ";
                
                PreparedStatement PS=con.prepareStatement(query);
                    PS.execute();
                    JOptionPane.showMessageDialog(null, "Updated Successfully");
                    UpdateTable();
               }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
               }
            tlegno.setText(null);
            tscheduled_dep_time.setText(null);
            tscheduled_arr_time.setText(null);
            tflightnumber.setText(null);
            tairportcode.setText(null);
         
     }
     if(e.getSource()==delete){
         String query="delete from FlightLeg where LegNo=?";
                try{
                 PreparedStatement PS=con.prepareStatement(query);
                 PS.setString(1, tlegno.getText());
                 PS.execute();
                 JOptionPane.showMessageDialog(null, "Deleted Successfully");
                 UpdateTable();
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
                tlegno.setText(null);
     }
     if(e.getSource()==search1)
     {
       String query="select * from FlightLeg where LegNo=?";
             try{
                    PreparedStatement PS=con.prepareStatement(query);
                    PS.setString(1, search.getText());
                    model.getDataVector().removeAllElements();
                    rs=PS.executeQuery();
                    while(rs.next()){
                        model.addRow(new Object[] {rs.getString("LegNo"),rs.getString("Scheduled_Dep_Time"),rs.getString("Scheduled_Arr_Time"),rs.getString("Flightnumber"),rs.getString("AirportCode")});
                                }
             }
             catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
             }
     }
          if(e.getSource()==Refresh){
    try {
        model.setRowCount(0);
        rs = stmt.executeQuery("select * from FlightLeg");
    while (rs.next()) {
        model.addRow(new Object[]{rs.getString("LegNo"),rs.getString("Scheduled_Dep_Time"),rs.getString("Scheduled_Arr_Time"),rs.getString("Flightnumber"),rs.getString("AirportCode")});
    }
            tlegno.setText("");
            tscheduled_dep_time.setText("");
            tscheduled_arr_time.setText("");
            tflightnumber.setText("");
            tairportcode.setText("");
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
        String AirportCode = table1.getModel().getValueAt(rowIndex, 0).toString();
        String scheduled_dep_time1 = table1.getModel().getValueAt(rowIndex, 1).toString();
        String  scheduled_arr_time1= table1.getModel().getValueAt(rowIndex, 2).toString();
        String flightnumber1 = table1.getModel().getValueAt(rowIndex, 3).toString();
        String airportcode1 = table1.getModel().getValueAt(rowIndex, 4).toString();
    tlegno.setText(AirportCode);
    tscheduled_dep_time.setText(scheduled_dep_time1);
    tscheduled_arr_time.setText(scheduled_arr_time1);
    tflightnumber.setText(flightnumber1);
    tairportcode.setText(airportcode1);
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
