/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2d.ping.pong.game;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ReaderThread implements Runnable {

    ObjectInputStream ois;
    String Name;

    ReaderThread(ObjectInputStream ois, String name){
        this.ois = ois;
        this.Name = name;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                //receive from server..
                Object received = ois.readObject();
//                if(received!=null)
                    System.out.println(Name + " Got: " + (String) received);

            } catch (ClassNotFoundException | IOException e) {
//                e.printStackTrace();
            }
        }
    }
}