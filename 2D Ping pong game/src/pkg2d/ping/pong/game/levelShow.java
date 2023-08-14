/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2d.ping.pong.game;

import java.awt.*;

public class levelShow extends Rectangle {

    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    public int lvlno;
    public Rectangle lvlbox;
    public String lvl="Level ";
    

    levelShow(int GAME_WIDTH, int GAME_HEIGHT) {
        levelShow.GAME_WIDTH = GAME_WIDTH;
        levelShow.GAME_HEIGHT = GAME_HEIGHT;
        //levelShow.lvno=sco;
        lvlbox=new Rectangle(390,500,235,60);
    }
    
    public void draw(Graphics g) {
                Graphics2D g2d=(Graphics2D)g;
                g.setFont(new Font("Consolas",Font.PLAIN,60));
             
		g.setColor(Color.black);
          
                g.setColor(Color.white);
                g2d.draw(lvlbox);
		
		g.setColor(Color.red);
		
                g.drawString(lvl+String.valueOf(lvlno/5), 390, 550);
	}

}
