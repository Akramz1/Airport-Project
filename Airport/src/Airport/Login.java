// Author name : Akram Tarek
package Airport;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;


public class Login extends Frame implements ActionListener, WindowListener{
    JLabel background;
    JLabel l1 = new JLabel("Login");
    JLabel l2 = new JLabel("Username");
    JLabel l3 = new JLabel("Password");
    JCheckBox c1 = new JCheckBox("Show Password");
    JButton b1 = new JButton("Log in");
    TextField t1 = new TextField();
    JPasswordField t2 = new JPasswordField();
    // JPanel Login_panel = new JPanel();
    
    
    
    Login (){
        this.setBounds(700, 400, 400, 420);
        this.setLayout(null);
        this.setVisible(true);
        this.setTitle("Login");
        setLocationRelativeTo(null);
        /*Login_panel.setLayout(null);
        Login_panel.setBounds(35, 50, 320, 320);
        Login_panel.setBackground(new Color(0, 0, 0, 80));
        add(Login_panel);
        Login_panel.add(l1);
     Login_panel.add(l2);
     Login_panel.add(l3);
     Login_panel.add(c1);
     Login_panel.add(b1);
     Login_panel.add(t1);
     Login_panel.add(t2);*/
        t1.setBounds(40, 130, 160, 24);
        t1.setText("");
        this.add(t1);
        t2.setText("");
        t2.setBounds(40, 190, 160, 24);
        this.add(t2);
        l1.setBounds(160, 40, 70, 50);
        l1.setFont(new Font("impact", Font.PLAIN, 30));
        l1.setForeground(Color.white);
        this.add(l1);
        l2.setBounds(40, 88, 150, 50);
        l2.setFont(new Font("import",Font.PLAIN, 22));
        l2.setForeground(Color.white);
        this.add(l2);
        c1.setBounds(230, 185, 120, 30);
        c1.addActionListener(this);
        this.add(c1);
        l3.setBounds(40, 147, 150, 50);
        l3.setFont(new Font("import", Font.PLAIN, 22));
        l3.setForeground(Color.white);
        this.add(l3);
        b1.setBounds(110, 280, 170, 50);
        b1.addActionListener(this);
        b1.setFont(new Font("import", Font.BOLD, 22));
        b1.setForeground(Color.black);
        this.add(b1);
        ImageIcon bg = new ImageIcon(
                "C:\\Users\\pc\\Desktop\\login.jpg"
        );
        background = new JLabel ("", bg, JLabel.CENTER);
        background.setBounds(0, 0, 400, 420);
        this.add(background);
        addWindowListener(this);
    }
    public static void main(String[] args) {
        Login n1 = new Login();
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
            String username=t1.getText();
            String password=t2.getText();
            if(username.equals("Akram")&&password.equals("Akram")){ 
                System.out.println("Login Succeded");
                JOptionPane.showMessageDialog(null, "Welcome, "+username , "Login status",1);
                   
        this.hide();
        Main mainfram =new Main();
        mainfram.show();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Error, Please check your username and password");
            }
        }
        if (c1.isSelected()) {
      t2.setEchoChar((char)0);
   } else {
      t2.setEchoChar('*');
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