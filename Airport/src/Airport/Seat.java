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

public class Seat extends Frame implements ActionListener, WindowListener, MouseListener{
    JTable table1;
    
    String[] columnNames = {"Seat No.","Customer_Name","Customer_Phone","Date"};
    Object [][] rowData = {

};
    DefaultTableModel model = new DefaultTableModel(rowData,columnNames);
    
    JLabel background;
    JPanel seat_panel = new JPanel();
    JButton exit = new JButton("Exit");
    JButton back = new JButton("Back To Main");
    JButton add = new JButton("Add");
    JButton update = new JButton("Update");
    JButton delete = new JButton("Delete");
    JButton search1 = new JButton("Search");
    JButton Refresh = new JButton("Refresh");
    JLabel seat = new JLabel("Seat");
    JLabel seatno = new JLabel("Seat Number");
    JLabel customername = new JLabel("Customer Name");
    JLabel customerphone = new JLabel("Customer Phone");
    JLabel date = new JLabel("Date");
    TextField tseatno = new TextField();
    TextField tcustomername = new TextField();
    TextField tcustomerphone = new TextField();
    TextField tdate = new TextField();
    TextField search = new TextField();
    Connection con;
    ResultSet rs;
    Statement stmt;
    
    
    Seat(){
        ConToSql();
        setBounds(380, 280, 990, 510);
        setLayout(null);
        setVisible(false);
        setTitle("Seat");
                        table1 = new JTable(model);
    JScrollPane scrollpane1 = new JScrollPane(table1);
    scrollpane1.setBounds(510,40, 460, 230);
    add(scrollpane1);
        setBounds(380, 280, 990, 510);
        setLayout(null);
        setVisible(false);
        setTitle("Seat");
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
        seatno.setBounds(40, 53, 140, 70);
        seatno.setFont(new Font("Roman", Font.PLAIN, 20));
        seatno.setForeground(Color.white);
        add(seatno);
        customername.setBounds(40, 98, 170, 70);
        customername.setFont(new Font("Roman", Font.PLAIN, 20));
        customername.setForeground(Color.white);
        add(customername);
        customerphone.setBounds(40, 138, 170, 70);
        customerphone.setFont(new Font("Roman", Font.PLAIN, 20));
        customerphone.setForeground(Color.white);
        add(customerphone);
        date.setBounds(40, 177, 120, 70);
        date.setFont(new Font("Roman", Font.PLAIN, 20));
        date.setForeground(Color.white);
        add(date);
        seat.setBounds(190, 5, 200, 70);
        seat.setFont(new Font("impact", Font.PLAIN, 30));
        seat.setForeground(Color.WHITE);
        add(seat);
        tseatno.setText("");
        tseatno.setBounds(235,80,200,20); // airport code
        add(tseatno);
        tcustomername.setText("");
        tcustomername.setBounds(235, 125, 200, 20); // airport name
        add(tcustomername);
        tcustomerphone.setText("");
        tcustomerphone.setBounds(235, 165, 200, 20); // state
        add(tcustomerphone);
        tdate.setText("");
        tdate.setBounds(235, 205, 200, 20); // state
        add(tdate);
     seat_panel.setLayout(null);
     seat_panel.setBounds(40, 40, 450, 230);
     seat_panel.setBackground(new Color(0, 0, 0, 80));
     add(seat_panel);
     seat_panel.add(seat);
     seat_panel.add(seatno);
     seat_panel.add(customername);
     seat_panel.add(customerphone);
     seat_panel.add(date);
     seat_panel.add(tseatno);
     seat_panel.add(tcustomername);
     seat_panel.add(tcustomerphone);
     seat_panel.add(tdate);
        ImageIcon bg = new ImageIcon(
                "C:\\Users\\pc\\Desktop\\flightv2.jpg"
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
            rs=stmt.executeQuery("select * from Seat");
            
            while(rs.next()){
            model.addRow(new Object[] {rs.getString("Seatno"),rs.getString("Customer_name"),rs.getString("Customer_phone"),rs.getString("SeatDate")});
            }
            
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
    }
    public void UpdateTable(){
          try{
              model.getDataVector().removeAllElements(); 
          
              String query=("select * from Seat");
                
                PreparedStatement PS=con.prepareStatement(query);
                  rs=PS.executeQuery();
                   while(rs.next()){
                       model.addRow(new Object[] {rs.getString("Seatno"),rs.getString("Customer_name"),rs.getString("Customer_phone"),rs.getString("SeatDate")});
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
            int seatno1= Integer.parseInt(tseatno.getText().toString());
            String customername1=tcustomername.getText().toString();
            String customerphone1=tcustomerphone.getText().toString();
            String date1=tdate.getText().toString();
           
            
            String query="insert into Seat  values ( '" + seatno1 + "' , '" + customername1 + "' , '" + customerphone1 + "', '" + date1 + "')";
           
            try{
                
                stmt.executeUpdate(query);
                model.addRow(new Object[]{seatno1,customername1,customerphone1,date1});
                JOptionPane.showMessageDialog(null, "Added Successfully");
            }
            catch(SQLException ex){
             JOptionPane.showMessageDialog(null, ex.toString());
            }
   
            tseatno.setText(null);
            tcustomername.setText(null);
            tcustomerphone.setText(null);
            tdate.setText(null);
         }
     }
     if(e.getSource()==update){
          try{ 
            int seatno1= Integer.parseInt(tseatno.getText().toString());
            String customername1=tcustomername.getText().toString();
            String customerphone1=tcustomerphone.getText().toString();
            String date1=tdate.getText().toString();
           
            
            String query="update Seat set Seatno='"+seatno1+"', Customer_name='"+customername1+"', Customer_phone='"+customerphone1+"', SeatDate='"+date1+"' where Seatno='"+seatno1+"' ";
                
                PreparedStatement PS=con.prepareStatement(query);
                    PS.execute();
                    JOptionPane.showMessageDialog(null, "Updated Successfully");
                    UpdateTable();
               }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
               }
            tseatno.setText(null);
            tcustomername.setText(null);
            tcustomerphone.setText(null);
            tdate.setText(null);
         
     }
     if(e.getSource()==delete){
         String query="delete from Seat where Seatno=?";
                try{
                 PreparedStatement PS=con.prepareStatement(query);
                 PS.setString(1, tseatno.getText());
                 PS.execute();
                 JOptionPane.showMessageDialog(null, "Deleted Successfully");
                 UpdateTable();
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
                tseatno.setText(null);
     }
     if(e.getSource()==search1)
     {
       String query="select * from Seat where Seatno=?";
             try{
                    PreparedStatement PS=con.prepareStatement(query);
                    PS.setString(1, search.getText());
                    model.getDataVector().removeAllElements();
                    rs=PS.executeQuery();
                    while(rs.next()){
                        model.addRow(new Object[] {rs.getString("Seatno"),rs.getString("Customer_name"),rs.getString("Customer_phone"),rs.getString("SeatDate")});
                                }
             }
             catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
             }
     }
          if(e.getSource()==Refresh){
    try {
        model.setRowCount(0);
        rs = stmt.executeQuery("select * from Seat");
    while (rs.next()) {
        model.addRow(new Object[]{rs.getString("Seatno"),rs.getString("Customer_name"),rs.getString("Customer_phone"),rs.getString("SeatDate")});
    }
            tseatno.setText("");
            tcustomername.setText("");
            tcustomerphone.setText("");
            tdate.setText("");
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
        String seatno1 = table1.getModel().getValueAt(rowIndex, 0).toString();
        String customername1 = table1.getModel().getValueAt(rowIndex, 1).toString();
        String  customerphone1= table1.getModel().getValueAt(rowIndex, 2).toString();
        String date1 = table1.getModel().getValueAt(rowIndex, 3).toString();
    tseatno.setText(seatno1);
    tcustomername.setText(customername1);
    tcustomerphone.setText(customerphone1);
    tdate.setText(date1);
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
