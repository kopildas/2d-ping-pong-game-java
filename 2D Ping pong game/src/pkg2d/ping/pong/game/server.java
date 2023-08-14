/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2d.ping.pong.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server extends GamePanel {

    public ServerSocket serverSocket;
    public Socket socket;
    public BufferedReader in;
    public PrintWriter out;
    public int sid;
    Paddle pad;
    Paddle paddle2;

    public server() throws IOException {
        serverSocket = new ServerSocket(22222);
       
        System.out.println("server run");
       // this.addKeyListener(new ALsv());
        socket = serverSocket.accept();
        System.out.println("Client connected..");

        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        new WriterThread(oos, "Server");
        new ReaderThread(ois, "Servrt");
        
       // new Thread(this).start();
        
//            
//            while(true){
//                Scanner sc = new Scanner(System.in);
//                String message = sc.nextLine();
//                //sent to client...
//                if (message != null) {
//                    oos.writeObject(message);
//                }
//            
//            
//            try {
//                //read from client...
//                Object cMsg = ois.readObject();
//                System.out.println("From Client: " + (String) cMsg);
//
//                //String serverMsg = (String) cMsg;
//
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//            }
//        }
    }



    @Override
    public void close() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
