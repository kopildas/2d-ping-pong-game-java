/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2d.ping.pong.game;

import java.awt.*;
import java.awt.event.*;
import java.net.Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.*;
import javax.swing.*;

public abstract class GamePanel extends JPanel implements Runnable {

    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = 555;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball, ball2;
    Score score;
    public MainScreen menu;
    public easyhrd eh;
    public wlcm wc;
    levelShow lvl;
    bgmusic1 music1;
    server sv;

    client cl;

    GamePanel() throws IOException {
//        sv = new server();
//        cl=new client();
        //D:\Zooom\2D Ping pong game\musics
        wcScreen();
        // msc();
        initi();
        eshrd();
        newPaddles();
        newBall();
        newBall2();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        lvl = new levelShow(GAME_WIDTH, GAME_HEIGHT);

        if (menu.active) {
            this.addMouseListener(menu);
            this.addMouseMotionListener(menu);
        }

        if (eh.act) {
            this.addMouseListener(eh);
            this.addMouseMotionListener(eh);
        }

        if (wc.active) {
            this.addMouseListener(wc);
            this.addMouseMotionListener(wc);
        }

        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }
    //initialization

    public void wcScreen() {
        wc = new wlcm(this);
    }

    public void initi() {
        menu = new MainScreen(this);
        // eh = new easyhrd(this);

    }

    public void msc() {
        music1 = new bgmusic1(this);
        music1.plymusic("musics\\\\msc1.wav");
    }

    public void eshrd() {
        eh = new easyhrd(this);
    }

    public void newBall() {
        random = new Random();
        ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);

    }

    public void newBall2() {
        random = new Random();
        ball2 = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
    }

    public void newPaddles() {
        paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {

        //Toolkit.getDefaultToolkit().sync();
        if (wc.active) {
            wc.draw(g);
        }
        if (!wc.active) {
            if (menu.active) {
                menu.draw(g);
            }

        }
        if (!menu.active) {

            if (eh.act) {
                eh.draw(g);
            }
            // }

        }
        if (!eh.act) {
            validate();
            Toolkit.getDefaultToolkit().sync();
            paddle1.draw(g);
            //Color white = null;
            paddle2.draw(g);
            ball.draw(g);
            lvl.draw(g);
            if (score.player2 >= 5 || score.player1 >= 5) {
                ball2.draw(g);
            }
            score.draw(g);
        }
//       

//        if (!menu.active) {
//            
//            if (eh.act) {
//                eh.draw(g);
//            }
//            
//        }
//        
        Toolkit.getDefaultToolkit().sync();

    }

    public void move() {
        if (!eh.act) {
            paddle1.move();
            paddle2.move();
            ball.move();
            if (score.player2 >= 5 || score.player1 >= 5) {
                ball2.move();
            }
        }

    }

    public void checkCollision() {

        //bounce ball off top & bottom window edges
        //ball1
        if (ball.y <= 0) {
            ball.setYDirection(-ball.yVelocity);
        }
        if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
        }
        //ball2
        if (ball2.y <= 0) {
            ball2.setYDirection(-ball2.yVelocity);
        }
        if (ball2.y >= GAME_HEIGHT - BALL_DIAMETER) {
            ball2.setYDirection(-ball2.yVelocity);
        }

        //bounce ball off paddles
        //ball1
        if (ball.intersects(paddle1)) {
            ball.i = 1;

            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; //optional for more difficulty
            if (ball.yVelocity > 0) {
                ball.yVelocity++; //optional for more difficulty
            } else {
                ball.yVelocity--;
            }
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        //ball2
        if (ball2.intersects(paddle1)) {
            ball2.i = 3;
            ball2.xVelocity = Math.abs(ball2.xVelocity);
            ball2.xVelocity++; //optional for more difficulty
            if (ball2.yVelocity > 0) {
                ball2.yVelocity++; //optional for more difficulty
            } else {
                ball2.yVelocity--;
            }
            ball2.setXDirection(ball2.xVelocity);
            ball2.setYDirection(ball2.yVelocity);
        }

        //ball1
        if (ball.intersects(paddle2)) {
            ball.i = 2;
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; //optional for more difficulty
            if (ball.yVelocity > 0) {
                ball.yVelocity++; //optional for more difficulty
            } else {
                ball.yVelocity--;
            }
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        //ball2
        if (ball2.intersects(paddle2)) {
            ball2.i = 4;

            ball2.xVelocity = Math.abs(ball2.xVelocity);
            ball2.xVelocity++; //optional for more difficulty
            if (ball2.yVelocity > 0) {
                ball2.yVelocity++; //optional for more difficulty
            } else {
                ball2.yVelocity--;
            }
            ball2.setXDirection(-ball2.xVelocity);
            ball2.setYDirection(ball2.yVelocity);
        }

        //stops paddles at window edges
        if (paddle1.y <= 0) {
            paddle1.y = 0;
        }
        if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
            paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        }
        if (paddle2.y <= 0) {
            paddle2.y = 0;
        }
        if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
            paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
        }
        if (ball.x <= 0) {
            score.player2++;
            lvl.lvlno = Math.max(score.player1, score.player2);
            newBall();
        }
        if (ball2.x <= 0) {
            score.player2++;
            lvl.lvlno = Math.max(score.player1, score.player2);
            newBall2();
        }
        if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
            score.player1++;
            lvl.lvlno = Math.max(score.player1, score.player2);
            newBall();
        }
        if (ball2.x >= GAME_WIDTH - BALL_DIAMETER) {
            score.player1++;
            lvl.lvlno = Math.max(score.player1, score.player2);
            newBall2();
        }

    }

    public void run() {
        //game loop

        long lastTime = System.nanoTime();

        double ns = 1000000000 / 60.0;
        double delta = 0;
        while (true) {

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    public abstract void close();

    public class AL extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            try {
                paddle1.keyPressed(e);
                paddle2.keyPressed(e);
            } catch (NullPointerException kr) {
                System.out.println("NullPointerException thrown!");
            }
        }

        public void keyReleased(KeyEvent e) {
            try {
                paddle1.keyReleased(e);
                paddle2.keyReleased(e);
            } catch (NullPointerException kr) {
                System.out.println("NullPointerException thrown!");
            }
        }
    }
}
