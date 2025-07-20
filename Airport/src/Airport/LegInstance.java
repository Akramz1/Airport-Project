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

public class LegInstance extends Frame implements ActionListener, WindowListener, MouseListener{
    JTable table1;
    
    String[] columnNames = {"Date","No.AvailSeats","Dep_Time","Arr_Time","ID","LegNo.","AirportCode"};
    Object [][] rowData = {

};
    DefaultTableModel model = new DefaultTableModel(rowData,columnNames);
    
    JLabel background;
    JPanel LegInstance_panel = new JPanel();
    JButton exit = new JButton("Exit");
    JButton back = new JButton("Back To Main");
    JButton add = new JButton("Add");
    JButton update = new JButton("Update");
    JButton delete = new JButton("Delete");
    JButton search1 = new JButton("Search");
    JButton Refresh = new JButton("Refresh");
    JLabel leginstance = new JLabel("Leg Instance");
    JLabel date = new JLabel("Date:");
    JLabel noavailableseats = new JLabel("No.Of Available Seats:");
    JLabel dep_time = new JLabel("Departure Time:");
    JLabel arr_time = new JLabel("Arrive Time:");
    JLabel id = new JLabel("ID:");
    JLabel legno = new JLabel("Leg Number:");
    JLabel airportcode = new JLabel("Airport Code:");
    TextField tdate = new TextField();
    TextField tnoavailableseats = new TextField();
    TextField tdep_time = new TextField();
    TextField tarr_time = new TextField();
    TextField tid = new TextField();
    TextField tlegno = new TextField();
    TextField tairportcode = new TextField();
    TextField search = new TextField();
    Connection con;
    ResultSet rs;
    Statement stmt;

    LegInstance(){
    ConToSql();
    table1 = new JTable(model);
    JScrollPane scrollpane1 = new JScrollPane(table1);
    scrollpane1.setBounds(420,40, 555, 230);
    add(scrollpane1);
        setBounds(380, 280, 990, 510);
        setLayout(null);
        setVisible(false);
        setTitle("Leg Instance");
        setLocationRelativeTo(null);
        back.setBounds(50,450,120,30);// Back To Main
        back.addActionListener(this); 
        back.setFont(new Font("impact", Font.PLAIN , 15));
        add(back);
        delete.setBounds(320,380,80,30); //delete
        delete.addActionListener(this);
        delete.setFont(new Font("impact", Font.PLAIN, 15));
        add(delete);
        update.setBounds(170, 380, 80, 30); //update
        update.addActionListener(this); 
        update.setFont(new Font ("impact", Font.PLAIN, 15));
        add(update);
        add.setBounds(30,380,80,30); //add
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
        leginstance.setBounds(130, 0, 140, 70);
        leginstance.setFont(new Font("impact", Font.PLAIN, 25));
        leginstance.setForeground(Color.WHITE);
        add(leginstance);
        date.setBounds(20, 45, 50, 70);
        date.setFont(new Font("Roman", Font.PLAIN, 18));
        date.setForeground(Color.white);
        add(date);
        noavailableseats.setBounds(20, 90, 180, 70);
        noavailableseats.setFont(new Font("Roman", Font.PLAIN, 18));
        noavailableseats.setForeground(Color.white);
        add(noavailableseats);
        dep_time.setBounds(20, 130, 130, 70);
        dep_time.setFont(new Font("Roman", Font.PLAIN, 18));
        dep_time.setForeground(Color.white);
        add(dep_time);
        arr_time.setBounds(20, 170, 120, 70);
        arr_time.setFont(new Font("Roman", Font.PLAIN, 18));
        arr_time.setForeground(Color.white);
        add(arr_time);
        id.setBounds(20, 209, 25, 70);
        id.setFont(new Font("Roman", Font.PLAIN, 18));
        id.setForeground(Color.white);
        add(id);
        legno.setBounds(20, 250, 120, 70);
        legno.setFont(new Font("Roman", Font.PLAIN, 18));
        legno.setForeground(Color.white);
        add(legno);
        airportcode.setBounds(20, 280, 120, 70);
        airportcode.setFont(new Font("Roman", Font.PLAIN, 18));
        airportcode.setForeground(Color.white);
        add(airportcode);
        tdate.setText("");
        tdate.setBounds(75,70,100,20);
        add(tdate);
        tnoavailableseats.setText("");
        tnoavailableseats.setBounds(205, 115, 100, 20);
        add(tnoavailableseats);
        tdep_time.setText("");
        tdep_time.setBounds(205, 155, 100, 20);
        add(tdep_time);
        tarr_time.setText("");
        tarr_time.setBounds(150, 195, 100, 20);
        add(tarr_time);
        tid.setText("");
        tid.setBounds(145, 235, 100, 20); 
        add(tid);
        tlegno.setText("");
        tlegno.setBounds(165, 275, 100, 20); 
        add(tlegno);
        tairportcode.setText("");
        tairportcode.setBounds(165, 305, 100, 20); 
        add(tairportcode);
     LegInstance_panel.setLayout(null);
     LegInstance_panel.setBounds(15, 40, 400, 330);
     LegInstance_panel.setBackground(new Color(0, 0, 0, 80));
     add(LegInstance_panel);
     LegInstance_panel.add(leginstance);
     LegInstance_panel.add(date);
     LegInstance_panel.add(noavailableseats);
     LegInstance_panel.add(dep_time);
     LegInstance_panel.add(arr_time);
     LegInstance_panel.add(id);
     LegInstance_panel.add(legno);
     LegInstance_panel.add(airportcode);
     LegInstance_panel.add(tdate);
     LegInstance_panel.add(tnoavailableseats);
     LegInstance_panel.add(tdep_time);
     LegInstance_panel.add(tarr_time);
     LegInstance_panel.add(tid);
     LegInstance_panel.add(tlegno);
     LegInstance_panel.add(tairportcode);
        ImageIcon bg = new ImageIcon(
                "C:\\Users\\pc\\Desktop\\leginstancev2.png"
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
            rs=stmt.executeQuery("select * from LegInstance");
            
            while(rs.next()){
            model.addRow(new Object[] {rs.getString("Date1"),rs.getString("NoOfAvailableSeats"),rs.getString("Dep_Time"),rs.getString("Arr_Time"),rs.getString("ID"),rs.getString("LegNo"),rs.getString("AirportCode")});
            }
            
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
    }
    public void UpdateTable(){
          try{
              model.getDataVector().removeAllElements(); 
          
              String query=("select * from LegInstance");
                
                PreparedStatement PS=con.prepareStatement(query);
                  rs=PS.executeQuery();
                   while(rs.next()){
                       model.addRow(new Object[] {rs.getString("Date1"),rs.getString("NoOfAvailableSeats"),rs.getString("Dep_Time"),rs.getString("Arr_Time"),rs.getString("ID"),rs.getString("LegNo"),rs.getString("AirportCode")});
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
            String date1 =tdate.getText().toString();
            int noavailableseats1 =Integer.parseInt(tnoavailableseats.getText().toString());
            String dep_time1 =tdep_time.getText().toString();
            String arr_time1 = tarr_time.getText().toString();
            int id1 =Integer.parseInt(tid.getText().toString());
            int legno1 =Integer.parseInt(tlegno.getText().toString());
            String airportcode1 = tairportcode.getText().toString();
           
            
            String query="insert into LegInstance  values ( '" + date1 + "' , '" + noavailableseats1 + "' , '" + dep_time1 + "', '" + arr_time1 + "', '" + id1 + "', '" + legno1 + "', '" + airportcode1 + "')";
           
            try{
                
                stmt.executeUpdate(query);
                model.addRow(new Object[]{date1,noavailableseats1,dep_time1,arr_time1,id1,legno1,airportcode1});
                JOptionPane.showMessageDialog(null, "Added Successfully");
            }
            catch(SQLException ex){
             JOptionPane.showMessageDialog(null, ex.toString());
            }
   
            tdate.setText(null);
            tnoavailableseats.setText(null);
            tdep_time.setText(null);
            tarr_time.setText(null);
            tid.setText(null);
            tlegno.setText(null);
            tairportcode.setText(null);
         }
     }
     if(e.getSource()==update){
          try{ 
            String date1 =tdate.getText().toString();
            int noavailableseats1 =Integer.parseInt(tnoavailableseats.getText().toString());
            String dep_time1 =tdep_time.getText().toString();
            String arr_time1 = tarr_time.getText().toString();
            int id1 =Integer.parseInt(tid.getText().toString());
            int legno1 =Integer.parseInt(tlegno.getText().toString());
            String airportcode1 = tairportcode.getText().toString();
           
            
            String query="update LegInstance set Date1='"+date1+"', NoOfAvailableSeats='"+noavailableseats1+"', Dep_Time='"+dep_time1+"', , Arr_Time='"+arr_time1+"', , ID='"+id1+"', LegNo='"+legno1+"', AirportCode='"+airportcode1+"' where Date1='"+date1+"' ";
                
                PreparedStatement PS=con.prepareStatement(query);
                    PS.execute();
                    JOptionPane.showMessageDialog(null, "Updated Successfully");
                    UpdateTable();
               }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
               }
            tdate.setText(null);
            tnoavailableseats.setText(null);
            tdep_time.setText(null);
            tarr_time.setText(null);
            tid.setText(null);
            tlegno.setText(null);
            tairportcode.setText(null);
         
     }
     if(e.getSource()==delete){
         String query="delete from LegInstance where Date1=?";
                try{
                 PreparedStatement PS=con.prepareStatement(query);
                 PS.setString(1, tdate.getText());
                 PS.execute();
                 JOptionPane.showMessageDialog(null, "Deleted Successfully");
                 UpdateTable();
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
                tdate.setText(null);
     }
     if(e.getSource()==search1)
     {
       String query="select * from LegInstance where Date1=?";
             try{
                    PreparedStatement PS=con.prepareStatement(query);
                    PS.setString(1, search.getText());
                    model.getDataVector().removeAllElements();
                    rs=PS.executeQuery();
                    while(rs.next()){
                        model.addRow(new Object[] {rs.getString("Date1"),rs.getString("NoOfAvailableSeats"),rs.getString("Dep_Time"),rs.getString("Arr_Time"),rs.getString("ID"),rs.getString("LegNo"),rs.getString("AirportCode")});
                                }
             }
             catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
             }
     }
          if(e.getSource()==Refresh){
    try {
        model.setRowCount(0);
        rs = stmt.executeQuery("select * from LegInstance");
    while (rs.next()) {
        model.addRow(new Object[]{rs.getString("Date1"),rs.getString("NoOfAvailableSeats"),rs.getString("Dep_Time"),rs.getString("Arr_Time"),rs.getString("ID"),rs.getString("LegNo"),rs.getString("AirportCode")});
    }
            tdate.setText("");
            tnoavailableseats.setText("");
            tdep_time.setText("");
            tarr_time.setText("");
            tid.setText("");
            tlegno.setText("");
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
        String date1 = table1.getModel().getValueAt(rowIndex, 0).toString();
        String noavailableseats1 = table1.getModel().getValueAt(rowIndex, 1).toString();
        String  dep_time1= table1.getModel().getValueAt(rowIndex, 2).toString();
        String arr_time1 = table1.getModel().getValueAt(rowIndex, 3).toString();
        String id1 = table1.getModel().getValueAt(rowIndex, 4).toString();
        String legno1 = table1.getModel().getValueAt(rowIndex, 5).toString();
        String airportcode1 = table1.getModel().getValueAt(rowIndex, 6).toString();
    tdate.setText(date1);
    tnoavailableseats.setText(noavailableseats1);
    tdep_time.setText(dep_time1);
    tarr_time.setText(arr_time1);
    tid.setText(id1);
    tlegno.setText(legno1);
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
