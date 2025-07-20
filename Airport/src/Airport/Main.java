// Author name : Akram Tarek
package Airport;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;


public class Main extends Frame implements ActionListener, WindowListener{
    JLabel background1;
    JButton b2 = new JButton("Airport");
    JButton b3 = new JButton("Airplane Type");
    JButton b4 = new JButton("Airplane");
    JButton b5 = new JButton("Flight Leg");
    JButton b6 = new JButton("Flight");
    JButton b7 = new JButton("Leg Instance");
    JButton b8 = new JButton("Seat");
    JButton b10 = new JButton("Exit");
    JButton b11 = new JButton("Can Land");
    Icon backicon = new ImageIcon("C:\\Users\\pc\\Desktop\\back icon.png");
    JButton back = new JButton(backicon);
    /*Image animation;
    Image background3;
    Timer timer;
    int xVelocity = 1;
    int yVelocity = 1;
    int x = 0;
    int y = 0;*/
    
    Main(){
        setBounds(500, 300, 800, 500);
        setLayout(null);
        setVisible(false);
        setTitle("Airline");
        setLocationRelativeTo(null);
        /*animation = new ImageIcon("C:\\Users\\pc\\Desktop\\animationbackground.png").getImage();
        background3 = new ImageIcon("C:\\Users\\pc\\Desktop\\WhatsApp Image 2023-05-06 at 22.22.13v2.jpg").getImage();
        timer = new Timer(1000 , null);
        timer.start();*/
        b2.setBounds(100, 100, 120, 50);
        b2.addActionListener(this);
        b2.setFont(new Font("impact", Font.PLAIN, 15)); // airport
        add(b2);
        b3.setBounds(100, 200, 120, 50);
        b3.addActionListener(this);
        b3.setFont(new Font("impact", Font.PLAIN, 15)); // airplane type
        add(b3);
        b4.setBounds(100, 300, 120, 50);
        b4.addActionListener(this);
        b4.setFont(new Font("impact", Font.PLAIN, 15)); // airplane
        add(b4);
        b5.setBounds(340, 100, 120, 50);
        b5.addActionListener(this);
        b5.setFont(new Font("impact", Font.PLAIN,15)); // flight leg
        add(b5);
        b6.setBounds(600, 100, 120, 50);
        b6.addActionListener(this);
        b6.setFont(new Font("impact", Font.PLAIN,15)); // flight
        add(b6);
        b7.setBounds(600, 200, 120, 50);
        b7.addActionListener(this);
        b7.setFont(new Font("impact", Font.PLAIN,15)); // Leg Instance
        add(b7);
        b8.setBounds(600, 300, 120, 50);
        b8.addActionListener(this);
        b8.setFont(new Font("impact",Font.PLAIN,15)); // Seat
        add(b8);
        b10.setBounds(600, 400, 120, 50); // exit
        b10.setFont(new Font("impact", Font.PLAIN,15));
        b10.addActionListener(this);
        add(b10);
        b11.setBounds(100, 400, 120, 50); // Can Land
        b11.setFont(new Font("impact", Font.PLAIN,15));
        b11.addActionListener(this);
        add(b11);
        back.setBounds(15, 35, 30, 25);
        back.addActionListener(this);
        add(back);
        ImageIcon bg1 = new ImageIcon(
                "C:\\Users\\pc\\Desktop\\Main.jpg"
        );
        background1 = new JLabel ("", bg1 , JLabel.CENTER);
        background1.setBounds(0, 0, 800, 500);
        add(background1);
        addWindowListener(this);
    }
    /*public void paint(Graphics g)
    {
        Graphics2D g2D = (Graphics2D) g;
        
        g2D.drawImage(background3, 0, 0, null);
        g2D.drawImage(animation, x, y, null);
    }*/
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b2)
        {
        setVisible(false);
        Airport AirportFrame =new Airport();
        AirportFrame.setVisible(true);
        }
     if(e.getSource()==b3)
     {
        setVisible(false);
        AirplaneType AirplaneTFrame =new AirplaneType();
        AirplaneTFrame.setVisible(true);
     }
     if(e.getSource()==b4){
         setVisible(false);
        Airplane AirplaneFrame =new Airplane();
        AirplaneFrame.setVisible(true);
     }
     if(e.getSource()==b5){
         setVisible(false);
        FlightLeg FlightLegFrame =new FlightLeg();
        FlightLegFrame.setVisible(true);
     }
     if(e.getSource()==b6){
        setVisible(false);
        Flight FlightFrame =new Flight();
        FlightFrame.setVisible(true);
     }
     if(e.getSource()==b7)
     {
        setVisible(false);
        LegInstance LegInstanceFrame =new LegInstance();
        LegInstanceFrame.setVisible(true);
     }
     if(e.getSource()==b8)
     {
        setVisible(false);
        Seat SeatFrame =new Seat();
        SeatFrame.setVisible(true);
     }
     if(e.getSource()==b10){
         System.exit(0);
     }
     if(e.getSource()==b11)
     {
         setVisible(false);
         Canland CanLandFrame = new Canland();
         CanLandFrame.setVisible(true);
     }
     if(e.getSource()==back)
     {
         setVisible(false);
         Login n1 = new Login();
         n1.show();
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
    
}
