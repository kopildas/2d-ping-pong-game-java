package pkg2d.ping.pong.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class wlcm  extends MouseAdapter{
    public boolean active;
    //Button play
    public Rectangle plybtn;
    public String plytext = "Welcom";
    public boolean pHighlight = false;
    //Another Button play
    public Rectangle quitbtn;
    public String quttext = "to the";
    public boolean qHighlight = false;
    //Another Button play
    public Rectangle pngbtn;
    public String pngtext = "Ping pong";
    public boolean pngHighlight = false;
    //Another Button play
    public Rectangle wrldtbtn;
    public String wrdtext = "world";
    public boolean wrHighlight = false;
    
    //Another Button play
    public Rectangle nxttbtn;
    public String nxttext = "Next";
    public boolean nxHighlight = false;
    
    // addMouseListener and addMouseMotionListener in Game() clsss;
    public Font font,fo;
    public wlcm(GamePanel game) {
        active =true;
       // game
        
        int w,h,x,y;
        w = 400;
        h = 100;
        y = game.GAME_HEIGHT / 400;
        x = game.GAME_WIDTH / 2-w / 2;
        plybtn = new Rectangle(x,y,w,h);
        
        //x = game.GAME_WIDTH * 3/4-w / 2;
        y=game.GAME_HEIGHT / 2-200;
        quitbtn = new Rectangle(x,y,w,h);
        
       // x = game.GAME_WIDTH * 3/4-w / 2;
       y=game.GAME_HEIGHT / 2-100;
        pngbtn = new Rectangle(x,y,w,h);
        
        //x = game.GAME_WIDTH * 3/4-w / 2;
        y=game.GAME_HEIGHT / 2;
        wrldtbtn = new Rectangle(x,y,w,h);
        
        y=game.GAME_HEIGHT-100;
        //x = game.GAME_WIDTH / 2-w;
        nxttbtn = new Rectangle(x,y,380,h);
        
        
        
        font = new Font("Roboto",font.PLAIN,100);
       // fo = new Font("Roboto",fo.PLAIN,40);
        
        
        
    }
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g.setFont(font);
        
        
        g.setColor(Color.BLACK);
//        if(pHighlight){
//            g.setColor(Color.white);
//        }
        g2d.fill(nxttbtn);
        
        g.setColor(Color.BLACK);
        if(nxHighlight){
            g.setColor(Color.white);
        }
       // g2d.fill(quitbtn);
        
       // g2d.fill(pngbtn);
        
        g2d.fill(nxttbtn);
        
        g.setColor(Color.white);
        g2d.draw(nxttbtn);
//        g2d.draw(quitbtn);
//        g2d.draw(pngbtn);
//        g2d.draw(wrldtbtn);
        
        int strWidth,strHeight;
        
        strWidth = g.getFontMetrics(font).stringWidth(plytext);
        strHeight = g.getFontMetrics(font).getHeight();
        
        g.setColor(Color.GREEN);
        g.drawString(plytext,(int) (plybtn.getX()+ plybtn.getWidth()/2-strWidth/2),
                (int)(plybtn.getY()+plybtn.getHeight()/2+strHeight/4));
        
        
        strWidth = g.getFontMetrics(font).stringWidth(plytext);
        strHeight = g.getFontMetrics(font).getHeight();
        
        g.setColor(Color.green);
        g.drawString(quttext,(int) (quitbtn.getX()+ quitbtn.getWidth()/2-strWidth/2),(int)(quitbtn.getY()+quitbtn.getHeight()/2+strHeight/4));
        
        g.setColor(Color.green);
        g.drawString(pngtext,(int) (pngbtn.getX()+ pngbtn.getWidth()/2-strWidth/2),
                (int)(pngbtn.getY()+pngbtn.getHeight()/2+strHeight/4));
        
        g.setColor(Color.green);
        g.drawString(wrdtext,(int) (wrldtbtn.getX()+ wrldtbtn.getWidth()/2-strWidth/2),
                (int)(wrldtbtn.getY()+wrldtbtn.getHeight()/2+strHeight/4));
        
        
//        strWidth = g.getFontMetrics(fo).stringWidth(plytext);
//        strHeight = g.getFontMetrics(fo).getHeight();
        g.setColor(Color.red);
        g.drawString(nxttext,(int) (nxttbtn.getX()+ nxttbtn.getWidth()/2-strWidth/2),
                (int)(nxttbtn.getY()+nxttbtn.getHeight()/2+strHeight/4));
    }
    
    
   
    public void mouseClicked(MouseEvent e){
        Point p = e.getPoint();
        
        if(nxttbtn.contains(p))
            active = false;
        else if(quitbtn.contains(p))
            System.exit(0);
            
    }
    
    public void mouseMoved(MouseEvent e){
        Point p = e.getPoint();
        nxHighlight = nxttbtn.contains(p);
        //qHighlight = quitbtn.contains(p);
        
        
    }
    
}
