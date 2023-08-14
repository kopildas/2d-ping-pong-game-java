package pkg2d.ping.pong.game;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import javax.swing.*;

public class GameFrame extends JFrame{

	GamePanel panel;
	
	GameFrame() throws IOException{
		panel = new GamePanel() {
                    @Override
                    public void close() {
                        System.out.println("close");
                    }
                };
		this.add(panel);
		this.setTitle("2D Ping Pong Game");
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
                addWindowListener(new Listener());
		this.setVisible(true);
		this.setLocationRelativeTo(null);
                
	}

    class Listener extends WindowAdapter {

        public void windowClosing(WindowEvent e){
            panel.close();
        }
    }
}