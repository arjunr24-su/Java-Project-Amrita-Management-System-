package amrita.management.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable{
         Thread t;
	public Splash() {
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/1.jpg"));
                Image i2 =i1.getImage().getScaledInstance(1280, 720,Image.SCALE_DEFAULT);
                ImageIcon i3 =new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		add(image);
                t = new Thread(this);
                t.start();
                setVisible(true);
                
          
                for (int i =1 ;i <=400 ; i++){
                setLocation(350 - (i/2),250 -(i/2));
                setSize(1280,720);
                }
		

			
		}

	public static void main(String[] args) {
		 new Splash();

	}

    /**
     *
     */
    @Override
    public void run(){
        try{
            Thread.sleep(7000);
            setVisible(false);
            
            new Login();
            
        }catch(Exception e){
            
        }
    }

}
