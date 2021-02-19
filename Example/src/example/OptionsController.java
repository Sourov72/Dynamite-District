/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class OptionsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Sound sound;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
    @FXML
    public void On(MouseEvent e) throws IOException
    {
       sound.SoundOn();
    }
    @FXML
    public void Off(MouseEvent e) throws IOException
    {
        sound.SoundOff();
    }
    @FXML
    public void Splus(MouseEvent e) throws IOException
    {
        sound.SoundP();
    }
    @FXML
    public void Sminus(MouseEvent e) throws IOException
    {
        sound.SoundM();
    }
    @FXML
     public void back(MouseEvent event) throws IOException
    {
       Parent op=FXMLLoader.load(getClass().getResource("FXML.fxml"));
       Scene opscene=new Scene(op);
       Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(opscene);
       window.show();
    }
    
}
