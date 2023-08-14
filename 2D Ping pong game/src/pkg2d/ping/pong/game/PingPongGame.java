package pkg2d.ping.pong.game;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;


public class PingPongGame {

    public static void main(String[] args) throws IOException {
        int choice= Integer.parseInt(JOptionPane.showInputDialog("1 for server | 2 for client"));
        
        if(choice==1)
            new server();
        else
            new client();
        GameFrame frame=new GameFrame();
        
    }
    
}
