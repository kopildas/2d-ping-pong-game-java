/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2d.ping.pong.game;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class WriterThread implements Runnable {
    private ObjectOutputStream oos;
    private String Name;

    WriterThread(ObjectOutputStream oos, String name){
        this.oos = oos;
        Name = name;
        new Thread(this).start();
    }
    
    public void run() {
        Scanner input = new Scanner(System.in);
        while (true){
            String message = input.nextLine();
            try {
                oos.writeObject(message);
                System.out.println("Sent...");
            } catch (IOException e) {
//                e.printStackTrace();
            }
        }
    }
}
