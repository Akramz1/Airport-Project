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

public class Canland extends Frame implements ActionListener, WindowListener, MouseListener {
    JTable table1;
    
    String[] columnNames = {"AirportCode","TypeName"};
    Object [][] rowData = {

};
    DefaultTableModel model = new DefaultTableModel(rowData,columnNames);
    
    JLabel background;
    JPanel canland_panel = new JPanel();
    JButton exit = new JButton("Exit");
    JButton back = new JButton("Back To Main");
    JButton add = new JButton("Add");
    JButton update = new JButton("Update");
    JButton delete = new JButton("Delete");
    JButton search1 = new JButton("Search");
    JButton Refresh = new JButton("Refresh");
    JLabel airportcode = new JLabel("Airport Code");
    JLabel canland = new JLabel("Can Land");
    JLabel typename = new JLabel("Type Name");
    TextField tairportcode = new TextField();
    TextField ttypename = new TextField();
    TextField search = new TextField();
    Connection con;
    ResultSet rs;
    Statement stmt;
    
    Canland(){
        ConToSql();
        table1 = new JTable(model);
    JScrollPane scrollpane1 = new JScrollPane(table1);
    scrollpane1.setBounds(510,40, 460, 230);
    add(scrollpane1);
        setBounds(380, 280, 990, 510);
        setLayout(null);
        setVisible(false);
        setTitle("Can Land");
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
        typename.setBounds(40, 150, 120, 70);
        typename.setFont(new Font("Roman", Font.PLAIN, 20));
        typename.setForeground(Color.white);
        add(typename);
        airportcode.setBounds(40, 110, 170, 70);
        airportcode.setFont(new Font("Roman", Font.PLAIN, 20));
        airportcode.setForeground(Color.white);
        add(airportcode);
        canland.setBounds(160, 25, 200, 70);
        canland.setFont(new Font("impact", Font.PLAIN, 30));
        canland.setForeground(Color.WHITE);
        add(canland);
        tairportcode.setText("");
        tairportcode.setBounds(235, 135, 200, 20); // airport name
        add(tairportcode);
        ttypename.setText("");
        ttypename.setBounds(235, 175, 200, 20); // state
        add(ttypename);
        canland_panel.setLayout(null);
     canland_panel.setBounds(40, 40, 450, 230);
     canland_panel.setBackground(new Color(0, 0, 0, 80));
     add(canland_panel);
     canland_panel.add(airportcode);
     canland_panel.add(typename);
     canland_panel.add(ttypename);
     canland_panel.add(tairportcode);
     canland_panel.add(canland);
        ImageIcon bg = new ImageIcon(
                "C:\\Users\\pc\\Desktop\\canland.jpg"
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
            rs=stmt.executeQuery("select * from CanLand");
            
            while(rs.next()){
            model.addRow(new Object[] {rs.getString("AirportCode"),rs.getString("Typename")});
            }
            
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
    }
    public void UpdateTable(){
          try{
              model.getDataVector().removeAllElements(); 
          
              String query=("select * from CanLand");
                
                PreparedStatement PS=con.prepareStatement(query);
                  rs=PS.executeQuery();
                   while(rs.next()){
                       model.addRow(new Object[] {rs.getString("AirportCode"),rs.getString("Typename")});
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
            String AirportCode =tairportcode.getText().toString();
            String Typename =ttypename.getText().toString();
           
            
            String query="insert into CanLand  values ( '" + AirportCode + "' , '" + Typename + "')";
           
            try{
                
                stmt.executeUpdate(query);
                model.addRow(new Object[]{AirportCode,Typename});
                JOptionPane.showMessageDialog(null, "Added Successfully");
            }
            catch(SQLException ex){
             JOptionPane.showMessageDialog(null, ex.toString());
            }
   
            tairportcode.setText(null);
            ttypename.setText(null);
         }
     }
     if(e.getSource()==update){
          try{ 
            String AirportCode= tairportcode.getText().toString();
            String Typename=ttypename.getText().toString();
           
            
            String query="update CanLand set AirportCode='"+AirportCode+"', Typename='"+Typename+"' where AirportCode='"+AirportCode+"' ";
                
                PreparedStatement PS=con.prepareStatement(query);
                    PS.execute();
                    JOptionPane.showMessageDialog(null, "Updated Successfully");
                    UpdateTable();
               }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
               }
            tairportcode.setText(null);
            ttypename.setText(null);
         
     }
     if(e.getSource()==delete){
         String query="delete from CanLand where AirportCode=?";
                try{
                 PreparedStatement PS=con.prepareStatement(query);
                 PS.setString(1, tairportcode.getText());
                 PS.execute();
                 JOptionPane.showMessageDialog(null, "Deleted Successfully");
                 UpdateTable();
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
                ttypename.setText(null);
     }
     if(e.getSource()==search1)
     {
       String query="select * from CanLand where AirportCode=?";
             try{
                    PreparedStatement PS=con.prepareStatement(query);
                    PS.setString(1, search.getText());
                    model.getDataVector().removeAllElements();
                    rs=PS.executeQuery();
                    while(rs.next()){
                        model.addRow(new Object[] {rs.getString("AirportCode"),rs.getString("Typename")});
                                }
             }
             catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
             }
     }
          if(e.getSource()==Refresh){
    try {
        model.setRowCount(0);
        rs = stmt.executeQuery("select * from CanLand");
    while (rs.next()) {
        model.addRow(new Object[]{rs.getString("AirportCode"),rs.getString("Typename")});
    }
            tairportcode.setText("");
            ttypename.setText("");
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
        String airportcode1 = table1.getModel().getValueAt(rowIndex, 0).toString();
        String typename1 = table1.getModel().getValueAt(rowIndex, 1).toString();
    tairportcode.setText(airportcode1);
    ttypename.setText(typename1);
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
