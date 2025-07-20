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

public class Airport extends Frame implements ActionListener, WindowListener, MouseListener{
      JTable table1;
    
    String[] columnNames = {"Airport Code","Airport Name","State","City"};
    Object [][] rowData = {

};
    
    DefaultTableModel model = new DefaultTableModel(rowData,columnNames);
    
    JLabel background2;
    JLabel airportcode = new JLabel("Airport Code");
    JPanel Airport_panel = new JPanel();
    JLabel airportname = new JLabel("Airport Name");
    JLabel airportstate = new JLabel("State");
    JLabel airportcity = new JLabel("City");
    JLabel l4 = new JLabel("Airport");
    TextField t3 = new TextField(); // airport code
    TextField t4 = new TextField(); // airport name
    TextField t5 = new TextField(); // airport state
    TextField t6 = new TextField(); // airport city
    TextField search = new TextField();
    JButton back = new JButton("Back To Main");
    JButton delete = new JButton("Delete");
    JButton update = new JButton("Update");
    JButton add = new JButton("Add");
    JButton exit = new JButton("Exit");
    JButton search1 = new JButton("Search");
    JButton Refresh = new JButton("Refresh");
    Connection con;
    Statement stmt;
    ResultSet rs;
    
    
    
    Airport(){
        ConToSql();
    table1 = new JTable(model);
    JScrollPane scrollpane1 = new JScrollPane(table1);
    scrollpane1.setBounds(510,40, 460, 270);
    add(scrollpane1);
        setBounds(500, 300, 990, 510); 
        setLayout(null);
        setVisible(false);
        setTitle("Airport");
        setLocationRelativeTo(null);
        back.setBounds(50,450,120,30); // Back To Main
        back.addActionListener(this);
        back.setFont(new Font("impact", Font.PLAIN , 15));
        add(back);
        delete.setBounds(370,330,80,30); //delete
        delete.addActionListener(this);
        delete.setFont(new Font("impact", Font.PLAIN, 15));
        add(delete);
        update.setBounds(220, 330, 80, 30);
        update.addActionListener(this);//update
        update.setFont(new Font ("impact", Font.PLAIN, 15));
        add(update);
        add.setBounds(80,330,80,30); //add
        add.addActionListener(this);
        add.setFont(new Font ("impact", Font.PLAIN, 15));
        add(add);
        exit.setBounds(880, 450, 80, 30); //exit
        exit.addActionListener(this);
        exit.setFont(new Font ("impact", Font.PLAIN, 15));
        add(exit);
        l4.setBounds(170, 5, 200, 70);
        l4.setFont(new Font("impact", Font.PLAIN, 30));
        l4.setForeground(Color.WHITE);
        add(l4);
        airportcode.setBounds(40, 60, 120, 70);
        airportcode.setFont(new Font("Roman", Font.PLAIN, 20));
        airportcode.setForeground(Color.white);
        add(airportcode);
        airportname.setBounds(40, 110, 120, 70);
        airportname.setFont(new Font("Roman", Font.PLAIN, 20));
        airportname.setForeground(Color.white);
        add(airportname);
        airportstate.setBounds(40, 150, 120, 70);
        airportstate.setFont(new Font("Roman", Font.PLAIN, 20));
        airportstate.setForeground(Color.white);
        add(airportstate);
        airportcity.setBounds(40, 192, 120, 70);
        airportcity.setFont(new Font("Roman", Font.PLAIN, 20));
        airportcity.setForeground(Color.white);
        add(airportcity);
        t3.setText("");
        t3.setBounds(235,90,200,20); // airport code
        add(t3);
        t4.setText("");
        t4.setBounds(235, 135, 200, 20); // airport name
        add(t4);
        t5.setText("");
        t5.setBounds(235, 175, 200, 20); // state
        add(t5);
        t6.setText("");
        t6.setBounds(235, 220, 200, 20); // city
        add(t6);
        search1.setBounds(780, 340, 180, 30);
        search1.addActionListener(this);
        search1.setFont(new Font ("impact", Font.PLAIN, 15));
        add(search1);
        search.setText("");
        search.setBounds(550, 345, 200 ,22);
        add(search);
        Refresh.setBounds(780, 390, 180, 30);
        Refresh.addActionListener(this);
        Refresh.setFont(new Font ("impact", Font.PLAIN, 15));
        add(Refresh);
     Airport_panel.setLayout(null);
     Airport_panel.setBounds(40, 40, 450, 270);
     Airport_panel.setBackground(new Color(0, 0, 0, 80));
     add(Airport_panel);
     Airport_panel.add(l4);
     Airport_panel.add(airportcode);
     Airport_panel.add(airportname);
     Airport_panel.add(airportstate);
     Airport_panel.add(airportcity);
     Airport_panel.add(t3);
     Airport_panel.add(t4);
     Airport_panel.add(t5);
     Airport_panel.add(t6);

        ImageIcon bg3 = new ImageIcon(
                "C:\\Users\\pc\\Desktop\\Airportttv2.jpg"
        );
        background2 = new JLabel ("", bg3, JLabel.CENTER);
        background2.setBounds(0, 0, 990, 510);
        add(background2);
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
            rs=stmt.executeQuery("select * from Airport");
            
            while(rs.next()){
            model.addRow(new Object[] {rs.getString("AirportCode"),rs.getString("AirportName"),rs.getString("AirportState"),rs.getString("City")});
            }
            
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
    }
    public void UpdateTable(){
          try{
              model.getDataVector().removeAllElements(); 
          
              String query=("select * from Airport");
                
                PreparedStatement PS=con.prepareStatement(query);
                  rs=PS.executeQuery();
                   while(rs.next()){
            
                       model.addRow(new Object[] {rs.getString("AirportCode"),rs.getString("AirportName"),rs.getString("AirportState"),rs.getString("City")});
            } 
                
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
             if(e.getSource()==add)
     {
            String AirportCode= t3.getText().toString();
            String AirportName=t4.getText().toString();
            String AirportState=t5.getText().toString();
            String City=t6.getText().toString();
           
            
            String query="insert into Airport  values ( '" + AirportCode + "' , '" + AirportName + "' , '" + AirportState + "' , '" + City + "' )";
           
            try{
                
                stmt.executeUpdate(query);
                model.addRow(new Object[]{AirportCode,AirportName,AirportState,City});
                JOptionPane.showMessageDialog(null, "Added Successfully");
            }
            catch(SQLException ex){
             JOptionPane.showMessageDialog(null, ex.toString());
            }
   
            t3.setText(null);
            t4.setText(null);
            t5.setText(null);
            t6.setText(null);

     }
     if(e.getSource()==update){
  try{ 
            String AirportCode= t3.getText().toString();
            String AirportName=t4.getText().toString();
            String AirportState=t5.getText().toString();
            String City=t6.getText().toString();
           
            
            String query="update Airport set AirportCode='"+AirportCode+"', AirportName='"+AirportName+"', AirportState='"+AirportState+"' , City='"+City+"'where AirportCode='"+AirportCode+"' ";
                
                PreparedStatement PS=con.prepareStatement(query);
                    PS.execute();
                    JOptionPane.showMessageDialog(null, "Updated Successfully");
                    UpdateTable();
               }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
               }
           t3.setText(null);
            t4.setText(null);
            t5.setText(null);
            t6.setText(null);
        }
     if(e.getSource()==delete){
         String query="delete from Airport where AirportCode=?";
                try{
                 PreparedStatement PS=con.prepareStatement(query);
                 PS.setString(1, t3.getText());
                 PS.execute();
                 JOptionPane.showMessageDialog(null, "Deleted Successfully");
                 UpdateTable();
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
                t3.setText(null);
     }
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
     if(e.getSource()==search1)
     {
       String query="select * from AirpORT where AirportCode=?";
             try{
                    PreparedStatement PS=con.prepareStatement(query);
                    PS.setString(1, search.getText());
                    model.getDataVector().removeAllElements();
                    rs=PS.executeQuery();
                    while(rs.next()){
                        model.addRow(new Object[] {rs.getString("AirportCode"),rs.getString("AirportName"),rs.getString("AirportState"),rs.getString("City")});
                                }
             }
             catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
             }
     }
     if(e.getSource()==Refresh){
    try {
        model.setRowCount(0);
        rs = stmt.executeQuery("select * from Airport");
    while (rs.next()) {
        model.addRow(new Object[]{rs.getString("AirportCode"), rs.getString("AirportName"), rs.getString("AirportState"), rs.getString("City")});
    }
            t3.setText("");
            t4.setText("");
            t5.setText("");
            t6.setText("");
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
        String AirportName = table1.getModel().getValueAt(rowIndex, 1).toString();
        String  AirportState= table1.getModel().getValueAt(rowIndex, 2).toString();
        String City = table1.getModel().getValueAt(rowIndex, 3).toString();
    t3.setText(AirportCode);
    t4.setText(AirportName);
    t5.setText(AirportState);
    t6.setText(City);
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
