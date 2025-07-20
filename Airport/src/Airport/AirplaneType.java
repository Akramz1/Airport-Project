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

public class AirplaneType extends Frame implements ActionListener, WindowListener, MouseListener {
    JTable table1;
    
    String[] columnNames = {"TypeName","Manfacturing Comapny","Max Seats"};
    Object [][] rowData = {

};
    DefaultTableModel model = new DefaultTableModel(rowData,columnNames);
    JLabel background;
    JPanel Airplanetype_panel = new JPanel();
    JButton exit = new JButton("Exit");
    JButton back = new JButton("Back To Main");
    JButton add = new JButton("Add");
    JButton update = new JButton("Update");
    JButton delete = new JButton("Delete");
    JButton search1 = new JButton("Search");
    JButton Refresh = new JButton("Refresh");
    JLabel airplanetype = new JLabel("Airplane Type");
    JLabel typename = new JLabel("Type Name");
    JLabel manfacturingname = new JLabel("Manfacturing Name");
    JLabel maxseats = new JLabel("Max Seats");
    TextField ttypename = new TextField();
    TextField tmanfacturingname = new TextField();
    TextField tmaxseats = new TextField();
    TextField search = new TextField();
    Connection con;
    Statement stmt;
    ResultSet rs;

    AirplaneType(){
        ConToSql();
        table1 = new JTable(model);
    JScrollPane scrollpane1 = new JScrollPane(table1);
    scrollpane1.setBounds(510,40, 460, 230);
    add(scrollpane1);
        setBounds(380, 280, 990, 510);
        setLayout(null);
        setVisible(false);
        setTitle("AirplaneType");
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
        Refresh.setBounds(780, 350, 180, 30);
        Refresh.addActionListener(this);
        Refresh.setFont(new Font ("impact", Font.PLAIN, 15));
        add(Refresh);
        typename.setBounds(40, 60, 120, 70);
        typename.setFont(new Font("Roman", Font.PLAIN, 20));
        typename.setForeground(Color.white);
        add(typename);
        manfacturingname.setBounds(40, 110, 170, 70);
        manfacturingname.setFont(new Font("Roman", Font.PLAIN, 20));
        manfacturingname.setForeground(Color.white);
        add(manfacturingname);
        maxseats.setBounds(40, 150, 120, 70);
        maxseats.setFont(new Font("Roman", Font.PLAIN, 20));
        maxseats.setForeground(Color.white);
        add(maxseats);
        airplanetype.setBounds(140, 5, 200, 70);
        airplanetype.setFont(new Font("impact", Font.PLAIN, 30));
        airplanetype.setForeground(Color.WHITE);
        add(airplanetype);
        ttypename.setText("");
        ttypename.setBounds(235,90,200,20); // airport code
        add(ttypename);
        tmanfacturingname.setText("");
        tmanfacturingname.setBounds(235, 135, 200, 20); // airport name
        add(tmanfacturingname);
        tmaxseats.setText("");
        tmaxseats.setBounds(235, 175, 200, 20); // state
        add(tmaxseats);
     Airplanetype_panel.setLayout(null);
     Airplanetype_panel.setBounds(40, 40, 450, 230);
     Airplanetype_panel.setBackground(new Color(0, 0, 0, 80));
     add(Airplanetype_panel);
     Airplanetype_panel.add(airplanetype);
     Airplanetype_panel.add(typename);
     Airplanetype_panel.add(manfacturingname);
     Airplanetype_panel.add(maxseats);
     Airplanetype_panel.add(ttypename);
     Airplanetype_panel.add(tmanfacturingname);
     Airplanetype_panel.add(tmaxseats);
        search1.setBounds(780, 300, 180, 30);
        search1.addActionListener(this);
        search1.setFont(new Font ("impact", Font.PLAIN, 15));
        add(search1);
        search.setText("");
        search.setBounds(550, 305, 200 ,22);
        add(search);
        ImageIcon bg = new ImageIcon(
                "C:\\Users\\pc\\Desktop\\AirplaneType.jpg"
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
            rs=stmt.executeQuery("select * from AirplaneType");
            
            while(rs.next()){
            model.addRow(new Object[] {rs.getString("TypeName"),rs.getString("Manfacturing_Company"),rs.getString("Max_Seats")});
            }
            
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
    }
    public void UpdateTable(){
          try{
              model.getDataVector().removeAllElements(); 
          
              String query=("select * from AirplaneType");
                
                PreparedStatement PS=con.prepareStatement(query);
                  rs=PS.executeQuery();
                   while(rs.next()){
                       model.addRow(new Object[] {rs.getString("TypeName"),rs.getString("Manfacturing_Company"),rs.getString("Max_Seats")});
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
            String typename1 =ttypename.getText().toString();
            String manfacturingname1 =tmanfacturingname.getText().toString();
            int maxseats1 = Integer.parseInt(tmaxseats.getText().toString());
           
            
            String query="insert into AirplaneType  values ( '" + typename1 + "' , '" + manfacturingname1 + "' , '" + maxseats1 + "')";
           
            try{
                
                stmt.executeUpdate(query);
                model.addRow(new Object[]{typename1,manfacturingname1,maxseats1});
                JOptionPane.showMessageDialog(null, "Added Successfully");
            }
            catch(SQLException ex){
             JOptionPane.showMessageDialog(null, ex.toString());
            }
   
            ttypename.setText(null);
            tmanfacturingname.setText(null);
            tmaxseats.setText(null);
         }
     }
     if(e.getSource()==update){
          try{ 
            String Typename= ttypename.getText().toString();
            String Manfacturing_Company=tmanfacturingname.getText().toString();
            int Max_Seats = Integer.parseInt(tmaxseats.getText().toString());
           
            
            String query="update AirplaneType set Typename='"+Typename+"', Manfacturing_Company='"+Manfacturing_Company+"', Max_Seats='"+Max_Seats+"' where Typename='"+Typename+"' ";
                
                PreparedStatement PS=con.prepareStatement(query);
                    PS.execute();
                    JOptionPane.showMessageDialog(null, "Updated Successfully");
                    UpdateTable();
               }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
               }
            ttypename.setText(null);
            tmanfacturingname.setText(null);
            tmaxseats.setText(null);
         
     }
     if(e.getSource()==delete){
         String query="delete from AirplaneType where Typename=?";
                try{
                 PreparedStatement PS=con.prepareStatement(query);
                 PS.setString(1, ttypename.getText());
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
       String query="select * from AirplaneType where Typename=?";
             try{
                    PreparedStatement PS=con.prepareStatement(query);
                    PS.setString(1, search.getText());
                    model.getDataVector().removeAllElements();
                    rs=PS.executeQuery();
                    while(rs.next()){
                        model.addRow(new Object[] {rs.getString("Typename"),rs.getString("Manfacturing_Company"),rs.getString("Max_Seats")});
                                }
             }
             catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
             }
     }
          if(e.getSource()==Refresh){
    try {
        model.setRowCount(0);
        rs = stmt.executeQuery("select * from AirplaneType");
    while (rs.next()) {
        model.addRow(new Object[]{rs.getString("Typename"),rs.getString("Manfacturing_Company"),rs.getString("Max_Seats")});
    }
            ttypename.setText("");
            tmanfacturingname.setText("");
            tmaxseats.setText("");
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
        String typename1 = table1.getModel().getValueAt(rowIndex, 0).toString();
        String manfacturingname1 = table1.getModel().getValueAt(rowIndex, 1).toString();
        String  AirportState= table1.getModel().getValueAt(rowIndex, 2).toString();
    ttypename.setText(typename1);
    tmanfacturingname.setText(manfacturingname1);
    tmaxseats.setText(AirportState);
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
