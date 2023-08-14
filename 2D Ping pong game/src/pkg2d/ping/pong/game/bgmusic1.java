/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2d.ping.pong.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

/**
 *
 * @author ASUS
 */
public class bgmusic1 {

    public String Filepath;

    bgmusic1(GamePanel aThis) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void plymusic(String filepath) {
        //InputStream msc;
        try {

            AudioInputStream audio= AudioSystem.getAudioInputStream(new File(filepath));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
            clip.loop(clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "eroor");
        }

    }

}
