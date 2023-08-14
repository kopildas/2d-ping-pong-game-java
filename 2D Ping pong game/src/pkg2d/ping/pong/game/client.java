/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2d.ping.pong.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class client extends GamePanel {

    public Socket socket;
    public int cid;
    Paddle pad;
    Paddle paddle2;

    public client() throws IOException {
        try {
            socket = new Socket("localhost", 22222);
  
            System.out.println("Client Connected..");
            cid=2;
           // this.addKeyListener(new ALclt());
        } catch (IOException e) {
            System.out.println("Client conn error..");
            e.printStackTrace();
        }

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        new WriterThread(oos, "Client1");
        new ReaderThread(ois, "Client1");
        
       // new Thread(this).start();
//        while (true) {
//            Scanner sc = new Scanner(System.in);
//            String message = sc.nextLine();
//            //sent to server...
//            if (message != null) {
//                oos.writeObject(message);
//            }
//
//            try {
//                //receive from server..
//                Object fromServer = ois.readObject();
//                System.out.println("From Server: " + (String) fromServer);
//
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }

    }

    
    
    @Override
    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
