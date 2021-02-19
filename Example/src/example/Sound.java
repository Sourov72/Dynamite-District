
package example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Sound {
   static private double i=0.5;
   static MediaPlayer media;
   static Media musicfile=new Media("file:///D:/mp31/2.mp3");
   static private int autotune=1;
    
    public void play()
    {
       media=new MediaPlayer(musicfile);
      
             media.setVolume(i);
             //media.play();
        if(autotune==1)
           media.setCycleCount(MediaPlayer.INDEFINITE);
            
       
    }
    
   
    static public void SoundOn()
    {
        media.play();
        autotune=1;
    }
   
   static public void SoundOff()
    {
        media.stop();
        autotune=0;
        
    }
   static public void SoundP()
    {
        i=i+0.1;
        if(i>1)
            i=1;
        media.setVolume(i);
       
    }
    static public void SoundM()
    {
        i=i-0.1;
        if(i<0)
            i=0;
        media.setVolume(i);
    }
    
    
}
