/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2d.ping.pong.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.time.Clock.system;
public class easyhrd  extends MouseAdapter{
    public boolean act;
    //Button play
    public Rectangle plybtn;
    public String plytext = "Easy";
    public boolean pHighlight = false;
    //Another Button play
    public Rectangle quitbtn;
    public String quttext = "Hard";
    public boolean qHighlight = false;
    // addMouseListener and addMouseMotionListener in Game() clsss;
    public Font font;
    public easyhrd(GamePanel game) {
        act =true;
       // game
        
        int w,h,x,y,y2;
        w = 100;
        h = 50;
        y = game.GAME_HEIGHT / 2-120;
        x = game.GAME_WIDTH / 2-w / 2;
        plybtn = new Rectangle(x,y,w,h);
        
        //x = game.GAME_WIDTH * 3/4-w / 2;
        y2=game.GAME_HEIGHT / 2-50;
        quitbtn = new Rectangle(x,y2,w,h);
        
        font = new Font("Roboto",font.PLAIN,40);
        
        
        
    }
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g.setFont(font);
        
        
        g.setColor(Color.BLACK);
        if(pHighlight){
            g.setColor(Color.white);
        }
        g2d.fill(plybtn);
        
        g.setColor(Color.BLACK);
        if(qHighlight){
            g.setColor(Color.white);
        }
        g2d.fill(quitbtn);
        
        g.setColor(Color.white);
        g2d.draw(plybtn);
        g2d.draw(quitbtn);  
        
        int strWidth,strHeight;
        
        strWidth = g.getFontMetrics(font).stringWidth(plytext);
        strHeight = g.getFontMetrics(font).getHeight();
        
        g.setColor(Color.GREEN);
        g.drawString(plytext,(int) (plybtn.getX()+ plybtn.getWidth()/2-strWidth/2),
                (int)(plybtn.getY()+plybtn.getHeight()/2+strHeight/4));
        
        
        strWidth = g.getFontMetrics(font).stringWidth(plytext);
        strHeight = g.getFontMetrics(font).getHeight();
        
        g.setColor(Color.RED);
        g.drawString(quttext,(int) (quitbtn.getX()+ quitbtn.getWidth()/2-strWidth/2),(int)(quitbtn.getY()+quitbtn.getHeight()/2+strHeight/4));
        
        if(!act)
        {
           // validate();
        }
        
    }
    
    
   
    public void mouseClicked(MouseEvent e){
        Point p = e.getPoint();
        
        if(plybtn.contains(p))
            act = false;
        else if(quitbtn.contains(p))
            System.exit(0);
            
    }
    
    public void mouseMoved(MouseEvent e){
        Point p = e.getPoint();
        pHighlight = plybtn.contains(p);
        qHighlight = quitbtn.contains(p);
        
        
    }
    
}
