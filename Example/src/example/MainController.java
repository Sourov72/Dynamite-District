
package example;

import static example.Shooting.objNum;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class MainController implements Initializable {
    
    
   @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        
    }
    public void start(MouseEvent event) throws IOException
    {
            Parent op=FXMLLoader.load(getClass().getResource("GameStart.fxml"));
            Scene opscene=new Scene(op);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(opscene);
            window.show();
    }
    
    
    public void lead(MouseEvent event) throws IOException
    {
       Parent op=FXMLLoader.load(getClass().getResource("Leaderboard.fxml"));
       Scene opscene=new Scene(op);
       Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(opscene);
       window.show();
    }
    public void options(MouseEvent event) throws IOException
    {
       Parent op=FXMLLoader.load(getClass().getResource("Options1.fxml"));
       Scene opscene=new Scene(op);
       Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(opscene);
       window.show();
    }
    public void credits(MouseEvent event) throws IOException
    {
       Parent op=FXMLLoader.load(getClass().getResource("Credits.fxml"));
       Scene opscene=new Scene(op);
       Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(opscene);
       window.show();
    }
    public void profile(MouseEvent event) throws IOException
    {
       Parent op=FXMLLoader.load(getClass().getResource("profile.fxml"));
       Scene opscene=new Scene(op);
       Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(opscene);
       window.show();
    }
    public void instruct(MouseEvent event) throws IOException
    {
       Parent op=FXMLLoader.load(getClass().getResource("instruct1.fxml"));
       Scene opscene=new Scene(op);
       Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(opscene);
       window.show();
    }
    
    public void exit(MouseEvent event) throws IOException
    {
       System.exit(0);
    }
    

}
